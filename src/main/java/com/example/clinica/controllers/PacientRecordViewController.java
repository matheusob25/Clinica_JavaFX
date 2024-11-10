package com.example.clinica.controllers;

import com.example.clinica.alerts.AlertMessage;
import com.example.clinica.controllers.listeners.DataChangeListener;
import com.example.clinica.db.DbException;
import com.example.clinica.model.entities.*;
import com.example.clinica.model.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

    @FXML
    private Button formPacientSave;

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
            AlertMessage.successMessage("Paciente cadastrado com sucesso!");
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }catch (DbException e){
            AlertMessage.errorMessage("Error saving pacient",e.getMessage());
        }

    }

    private void notifyDataChangeListeners() {
        for(DataChangeListener listener: dataChangeListeners){
            listener.onDataChanged();
        }
    }

    private Pacient getFormData() {
        Pacient p = new Pacient();
        p.setName(formPacientName.getText());
        p.setBirthDate(formPacientBirthDate.getValue());
        p.setCpf(formPacientCPF.getText());
        p.setNumber(formPacientNumber.getText());
        p.setNumberTwo(formPacientSecondNumber.getText());
        p.setEmail(formPacientEmail.getText());
        p.setDlne(formPacientDLNE.getText());
        p.setProfession(formPacientProfession.getText());
        p.setMaritalStatus(formPacientMaritalStatus.getValue());
        p.setStartTreatment(formPacientStartTreat.getValue());
        p.setEndTreatment(formPacientEndTreat.getValue());
        getAddressData(p);
        getAnamnesisData(p);
        return p;
    }
    private Address getAddressData(Pacient p) {
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

        Address address = new Address();
        address.setDescription(formPacientAddressInfo.getText());
        address.setReference(formPacientReference.getText());
        address.setNeighborhood(neighborhood);

        addressService.insert(address);

        p.setAddress(address);
        return address;
    }
    private Anamnese getAnamnesisData(Pacient p) {
        if(anamneseService == null) {
            throw new IllegalStateException("Services were not initialized correctly");
        }
        Anamnese a = new Anamnese();
        a.setSensitivityAnesthesia(formPacientAnesthesia.getText());
        a.setSensitivityAntibiotics(formPacientAntibiotics.getText());
        a.setMedicationUse(formPacientMedicationUse.getText());
        a.setSeriousIllness(formPacientSeriousIllness.getText());
        a.setSensitiveTooth(formPacientSensitiveTooth.isSelected());
        a.setDiabetes(formPacientDiabetes.isSelected());
        a.setPregnancy(formPacientPregnancy.isSelected());
        anamneseService.insert(a);
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

}
