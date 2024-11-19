package com.example.clinica.controllers;

import com.example.clinica.alerts.AlertMessage;
import com.example.clinica.controllers.listeners.DataChangeListener;
import com.example.clinica.model.entities.Professional;
import com.example.clinica.model.exceptions.ValidationException;
import com.example.clinica.model.services.ProfessionalService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class ProfessionalRecordViewController implements Initializable {
    private ProfessionalService professionalService;
    private Professional professional;
    @FXML
    private Button cancelBttn;

    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Label labelErrorName;

    @FXML
    private Label labelErrorNumber;

    @FXML
    private Label labelNumberError;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberField;

    @FXML
    private Button saveBttn;

    public void setProfessionalService(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    public ProfessionalService getProfessionalService() {
        return professionalService;
    }

    @FXML
    void onCancelBttnAction(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void onSaveBttnAction(ActionEvent event) {
        try {

            professional = getProfessionalData();
            professionalService.saveOrUpdate(professional);
            notifyDataListeners();
            AlertMessage.successMessage("Profissional salvo com sucesso!");
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }catch (ValidationException e) {
            AlertMessage.errorMessage("Preencha os campos obrigatórios vazios!");
            setErrorMessages(e.getErrors());
        }catch (Exception e) {
            AlertMessage.errorMessage("Erro ao tentar salvar profissional! ", e.getMessage());
        }
    }

    private void notifyDataListeners() {
        for (DataChangeListener dataChangeListener : dataChangeListeners) {
            dataChangeListener.onDataChanged();
        }
    }
    public void setErrorMessages(Map<String,String> errors){
        Set<String> fields = errors.keySet();
        if (fields.contains("name")){
            labelErrorName.setText(errors.get("name"));
            nameField.setPromptText("");
        }
        if (fields.contains("number")){
            labelErrorNumber.setText(errors.get("number"));
            numberField.setPromptText("");
        }
    }
    private void reloadTextFields(){
        nameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                labelErrorName.setText("");
            }
        });
        numberField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                labelErrorNumber.setText("");
            }
        });
    }

    private Professional getProfessionalData(){
        ValidationException validationException = new ValidationException("Validation error");
        if(nameField.getText().isEmpty() || nameField.getText() == null){
            validationException.addError("name", "campo de nome vazio!");
        }
        if(numberField.getText().isEmpty() || numberField.getText() == null){
            validationException.addError("number", "campo de numero vazio!");
        }
        if (validationException.getErrors().size() > 0){
            throw validationException;
        }
        professional.setName(nameField.getText());
        professional.setNumber(numberField.getText());
        professional.setDescription(descriptionTextArea.getText());
        return professional;
    }
    public void subscribeDataChangeListener(DataChangeListener dataChangeListener) {
        dataChangeListeners.add(dataChangeListener);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }
    public void updateFormData(){
        if(professional == null){
            throw new IllegalStateException("Professional is null");
        }
        nameField.setText(professional.getName());
        numberField.setText(professional.getNumber());
        descriptionTextArea.setText(professional.getDescription());
    }

    private void initializeNodes(){
        numberField.setTextFormatter(new TextFormatter<String>( change -> {
            String text = change.getControlNewText();

            text = text.replaceAll("[^\\d]", "");

            if (text.length() > 11){
                text = text.substring(0, 11);
            }
            change.setText(text);
            change.setRange(0, change.getControlText().length());
            return change;
        }));
    }
}
