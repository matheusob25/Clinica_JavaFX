package com.example.clinica.controllers;

import com.example.clinica.alerts.AlertMessage;
import com.example.clinica.controllers.listeners.DataChangeListener;
import com.example.clinica.db.DbException;
import com.example.clinica.model.entities.*;
import com.example.clinica.model.exceptions.ValidationException;
import com.example.clinica.model.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class PacientRecordViewController implements Initializable {

    private PacientService pacientService;
    private AddressService addressService;
    private CityService cityService;
    private NeighborHoodService neighborHoodService;
    private AnamneseService anamneseService;
    private Pacient entity;
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private BorderPane formPacientRegistry;
    @FXML
    private DatePicker formPacientBirthDate;
    @FXML
    private TextField formPacientCPF;
    @FXML
    private TextField formPacientDLNE;
    @FXML
    private TextField formPacientEmail;
    @FXML
    private DatePicker formPacientEndTreat;
    @FXML
    private ComboBox<String> formPacientMaritalStatus;
    @FXML
    private TextField formPacientName;
    @FXML
    private TextField formPacientNumber;
    @FXML
    private TextField formPacientSecondNumber;
    @FXML
    private TextField formPacientProfession;
    @FXML
    private DatePicker formPacientStartTreat;
    @FXML
    private Button formPacientRegistryProceed;



    // address fields
    @FXML
    private BorderPane formPacientAddress;
    @FXML
    private TextField formPacientAddressInfo;
    @FXML
    private TextField formPacientNeighborhood;
    @FXML
    private TextArea formPacientReference;
    @FXML
    private TextField formPacientCity;
    @FXML
    private Button formPacientAddressProceed;
    @FXML
    private Button formPacientAddressReturn;


    //anamnesis fields
    @FXML
    private BorderPane formPacientAnamnesis;
    @FXML
    private CheckBox formPacientSensitiveTooth;
    @FXML
    private TextField formPacientSeriousIllness;
    @FXML
    private CheckBox formPacientPregnancy;
    @FXML
    private TextField formPacientMedicationUse;
    @FXML
    private CheckBox formPacientDiabetes;
    @FXML
    private TextField formPacientAnesthesia;
    @FXML
    private TextField formPacientAntibiotics;
    @FXML
    private TextArea formPacientAnnotations;
    @FXML
    private Button formPacientAnamnesisReturn;

    // label errors
    @FXML
    private Label labelBirthDateError;
    @FXML
    private Label labelCPFError;
    @FXML
    private Label labelNameError;
    @FXML
    private Label labelNumberError;
    @FXML
    private Label labelAddressError;
    @FXML
    private Label labelCityError;
    @FXML
    private Label labelNeighborhoodError;

    @FXML
    private Button formPacientSave;

    private void configErrorLabels(){
        labelNameError.setMouseTransparent(true);
        labelBirthDateError.setMouseTransparent(true);
        labelCPFError.setMouseTransparent(true);
        labelNumberError.setMouseTransparent(true);
        labelAddressError.setMouseTransparent(true);
        labelCityError.setMouseTransparent(true);
        labelNeighborhoodError.setMouseTransparent(true);
    }
    public void setPacientService(PacientService pacientService) {
        this.pacientService = pacientService;
    }

    public void setServices(AnamneseService anamneseService, NeighborHoodService neighborHoodService, CityService cityService, AddressService addressService) {
        this.anamneseService = anamneseService;
        this.neighborHoodService = neighborHoodService;
        this.cityService = cityService;
        this.addressService = addressService;
    }

    public void setEntity(Pacient entity) {
        this.entity = entity;
    }

    public void subscribeDataChangeListener(DataChangeListener dataChangeListener) {
        dataChangeListeners.add(dataChangeListener);
    }


    @FXML
    public void switchForms(ActionEvent event) {
        if(event.getSource() == formPacientRegistryProceed) {
            formPacientRegistry.setVisible(false);
            formPacientAnamnesis.setVisible(false);
            formPacientAddress.setVisible(true);
        }else if(event.getSource() == formPacientAddressReturn) {
            formPacientAddress.setVisible(false);
            formPacientAnamnesis.setVisible(false);
            formPacientRegistry.setVisible(true);
        }else if(event.getSource() == formPacientAddressProceed) {
            formPacientAddress.setVisible(false);
            formPacientRegistry.setVisible(false);
            formPacientAnamnesis.setVisible(true);
        }else if(event.getSource() == formPacientAnamnesisReturn) {
            formPacientAnamnesis.setVisible(false);
            formPacientRegistry.setVisible(false);
            formPacientAddress.setVisible(true);
        }
    }
    @FXML
    public void onFormPacientSaveAction(ActionEvent event){
        try {
            entity = getFormData();
            pacientService.saveOrUpdate(entity);
            notifyDataChangeListeners();
            AlertMessage.successMessage("Paciente salvo com sucesso!");
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }catch (ValidationException e) {
            AlertMessage.errorMessage("Preencha os campos obrigatórios vazios!!");
            setErrorMessages(e.getErrors());

        }catch (DbException e){
            AlertMessage.errorMessage("Erro ao salvar paciente!", e.getMessage());
        }

    }

    private void notifyDataChangeListeners() {
        for(DataChangeListener listener: dataChangeListeners){
            listener.onDataChanged();
        }
    }

    private Pacient getFormData() {
        ValidationException validationException = new ValidationException("Validation error");
        if(formPacientName.getText().trim().isEmpty() || formPacientName.getText() == null){
            validationException.addError("name","campo de nome vazio!");
        }
        if(formPacientBirthDate.getValue() == null){
            validationException.addError("birthDate", "campo de data de nascimento vazio!");
        }
        if(formPacientNumber.getText().trim().isEmpty() || formPacientNumber.getText() == null){
            validationException.addError("number", "campo de número de celular vazio!");
        }
        if(formPacientCPF.getText().trim().isEmpty() || formPacientCPF.getText() == null){
            validationException.addError("CPF", "campo de CPF vazio!");
        }
        if (formPacientAddressInfo.getText().trim().isEmpty() || formPacientAddressInfo.getText() == null){
            validationException.addError("address", "campo de endereço vazio!");
        }
        if (formPacientNeighborhood.getText().trim().isEmpty() || formPacientNeighborhood.getText() == null){
            validationException.addError("neighborhood", "campo de bairro vazio!");
        }
        if (formPacientCity.getText().trim().isEmpty() || formPacientCity.getText() == null){
            validationException.addError("city", "campo de cidade vazio!");
        }
        if(validationException.getErrors().size() > 0){
            throw validationException;
        }
        entity.setName(formPacientName.getText());
        entity.setBirthDate(formPacientBirthDate.getValue());
        entity.setCpf(formPacientCPF.getText());
        entity.setNumber(formPacientNumber.getText());
        entity.setNumberTwo(formPacientSecondNumber.getText());
        entity.setEmail(formPacientEmail.getText());
        entity.setDlne(formPacientDLNE.getText());
        entity.setProfession(formPacientProfession.getText());
        entity.setMaritalStatus(formPacientMaritalStatus.getValue());
        entity.setStartTreatment(formPacientStartTreat.getValue());
        entity.setEndTreatment(formPacientEndTreat.getValue());

        getAddressData(entity);
        getAnamnesisData(entity);

        return entity;
    }
    private Address getAddressData(Pacient entity) {
        if(cityService == null || addressService == null || neighborHoodService == null) {
            throw new IllegalStateException("Services were not initialized correctly");
        }
        City city = cityService.findByName(formPacientCity.getText());
        if(city == null) {
            city = new City();
            city.setName(formPacientCity.getText());
            cityService.insert(city);
        }

        Neighborhood neighborhood = neighborHoodService.findByName(formPacientNeighborhood.getText());
        if(neighborhood == null) {
            neighborhood = new Neighborhood();
            neighborhood.setName(formPacientNeighborhood.getText());
            neighborhood.setCity(city);
            neighborHoodService.insert(neighborhood);
        }
        Address address;
        if(entity.getAddress() == null) {
             address = new Address();
        }else{
            address = entity.getAddress();
        }
        address.setDescription(formPacientAddressInfo.getText());
        address.setReference(formPacientReference.getText());
        address.setNeighborhood(neighborhood);

        addressService.insertOrUpdate(address);

        entity.setAddress(address);
        return address;
    }
    private Anamnese getAnamnesisData(Pacient p) {
        if(anamneseService == null) {
            throw new IllegalStateException("Services were not initialized correctly");
        }
        Anamnese a;
        if(p.getAnamnese() == null) {
            a = new Anamnese();
        }else {
            a = p.getAnamnese();
        }
        a.setSensitivityAnesthesia(formPacientAnesthesia.getText());
        a.setSensitivityAntibiotics(formPacientAntibiotics.getText());
        a.setMedicationUse(formPacientMedicationUse.getText());
        a.setSeriousIllness(formPacientSeriousIllness.getText());
        a.setSensitiveTooth(formPacientSensitiveTooth.isSelected());
        a.setDiabetes(formPacientDiabetes.isSelected());
        a.setPregnancy(formPacientPregnancy.isSelected());
        anamneseService.insertOrUpdate(a);
        p.setAnamnese(a);
        return a;
    }


    private void setInitialState() {
        formPacientAddress.setVisible(false);
        formPacientAnamnesis.setVisible(false);
        formPacientRegistry.setVisible(true);
    }


    private void setFormPacientMaritalStatus(){
        formPacientMaritalStatus.getItems().addAll(
        "Selecione",
            "Nenhum",
            "Solteiro(a)",
            "Casado(a)",
            "Divorciado(a)",
            "Viúvo(a)"
        );
        formPacientMaritalStatus.getSelectionModel().select(0);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFormPacientMaritalStatus();
        setInitialState();
        initializeNodes();
        reloadTextFields();
        configErrorLabels();

    }
    private void initializeNodes(){
        TextField dateEditor = formPacientBirthDate.getEditor();
        dateEditor.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\d/]*")) {
                dateEditor.setText(newValue.replaceAll("[^\\d/]", ""));
            }
        });
        TextField dateEditor2 = formPacientStartTreat.getEditor();
        dateEditor2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\d/]*")) {
                dateEditor2.setText(newValue.replaceAll("[^\\d/]", ""));
            }
        });
        TextField dateEditor3 = formPacientEndTreat.getEditor();
        dateEditor3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\d/]*")) {
                dateEditor3.setText(newValue.replaceAll("[^\\d/]", ""));
            }
        });
        formPacientNumber.setTextFormatter(new TextFormatter<String>(change -> {
            String text = change.getControlNewText();

            // Remove todos os caracteres que não são dígitos
            text = text.replaceAll("[^\\d]", "");

            if (text.length() > 11) {
                text = text.substring(0, 11); // Limita a 11 dígitos
            }

            change.setText(text);
            change.setRange(0, change.getControlText().length());
            return change;
        }));
        formPacientSecondNumber.setTextFormatter(new TextFormatter<String>(change -> {
            String text = change.getControlNewText();

            // Remove todos os caracteres que não são dígitos
            text = text.replaceAll("[^\\d]", "");

            if (text.length() > 11) {
                text = text.substring(0, 11); // Limita a 11 dígitos
            }

            change.setText(text);
            change.setRange(0, change.getControlText().length());
            return change;
        }));
        formPacientCPF.setTextFormatter(new TextFormatter<String>(change -> {
            String text = change.getControlNewText();

            // Remove todos os caracteres que não são dígitos
            text = text.replaceAll("[^\\d]", "");

            if (text.length() > 11) {
                text = text.substring(0, 11); // Limita a 14 dígitos
            }


            change.setText(text);
            change.setRange(0, change.getControlText().length());
            return change;
        }));

    }
    public void updateFormData(){
        if(entity == null || entity.getAnamnese() == null || entity.getAddress() == null
        || entity.getAddress().getNeighborhood() == null || entity.getAddress().getNeighborhood().getCity() == null){
            return;
        }
        // pacient data
        formPacientName.setText(entity.getName());
        formPacientBirthDate.setValue(entity.getBirthDate());
        formPacientCPF.setText(entity.getCpf());
        formPacientNumber.setText(entity.getNumber());
        formPacientSecondNumber.setText(entity.getNumberTwo());
        formPacientEmail.setText(entity.getEmail());
        formPacientDLNE.setText(entity.getDlne());
        formPacientProfession.setText(entity.getProfession());
        formPacientMaritalStatus.getSelectionModel().select(entity.getMaritalStatus());
        formPacientStartTreat.setValue(entity.getStartTreatment());
        formPacientEndTreat.setValue(entity.getEndTreatment());

        //pacient address data
        formPacientAddressInfo.setText(entity.getAddress().getDescription());
        formPacientNeighborhood.setText(entity.getAddress().getNeighborhood().getName());
        formPacientCity.setText(entity.getAddress().getNeighborhood().getCity().getName());
        formPacientReference.setText(entity.getAddress().getReference());

        //pacient anamnesis data
        formPacientAnesthesia.setText(entity.getAnamnese().getSensitivityAnesthesia());
        formPacientMedicationUse.setText(entity.getAnamnese().getMedicationUse());
        formPacientAntibiotics.setText(entity.getAnamnese().getSensitivityAntibiotics());
        formPacientSensitiveTooth.setSelected(entity.getAnamnese().getSensitiveTooth());
        formPacientSeriousIllness.setText(entity.getAnamnese().getSeriousIllness());
        formPacientDiabetes.setSelected(entity.getAnamnese().getDiabetes());
        formPacientPregnancy.setSelected(entity.getAnamnese().getPregnancy());
        formPacientAnnotations.setText(entity.getAnamnese().getAdditionalAnnotations());
    }
    public void setErrorMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();
        if(fields.contains("name")){
            labelNameError.setText(errors.get("name"));

            formPacientName.setPromptText("");
        }
        if(fields.contains("birthDate")){
            labelBirthDateError.setText(errors.get("birthDate"));

            formPacientBirthDate.setPromptText("");
        }
        if(fields.contains("cpf")){
            labelCPFError.setText(errors.get("cpf"));
            formPacientCPF.setPromptText("");
        }
        if(fields.contains("number")){
            labelNumberError.setText(errors.get("number"));
            formPacientNumber.setPromptText("");

        }
        if(fields.contains("address")){
            labelAddressError.setText(errors.get("address"));
        }
        if(fields.contains("city")){
            labelCityError.setText(errors.get("city"));
        }
        if(fields.contains("neighborhood")){
            labelNeighborhoodError.setText(errors.get("neighborhood"));
        }
    }
    private void reloadTextFields(){
        formPacientName.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // Se o TextField ganha foco
                labelNameError.setText(""); // Limpa o texto do Label
            }
        });
        formPacientBirthDate.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // Se o TextField ganha foco
                labelBirthDateError.setText(""); // Limpa o texto do Label
            }
        });
        formPacientCPF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // Se o TextField ganha foco
                labelCPFError.setText(""); // Limpa o texto do Label
            }
        });
        formPacientNumber.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // Se o TextField ganha foco
                labelNumberError.setText(""); // Limpa o texto do Label
            }
        });
        formPacientAddressInfo.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // Se o TextField ganha foco
                labelAddressError.setText(""); // Limpa o texto do Label
            }
        });
        formPacientNeighborhood.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // Se o TextField ganha foco
                labelNeighborhoodError.setText(""); // Limpa o texto do Label
            }
        });
        formPacientCity.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // Se o TextField ganha foco
                labelCityError.setText(""); // Limpa o texto do Label
            }
        });
    }

}
