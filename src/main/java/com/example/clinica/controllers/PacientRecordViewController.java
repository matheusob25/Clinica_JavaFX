package com.example.clinica.controllers;

import com.example.clinica.model.entities.Pacient;
import com.example.clinica.model.services.PacientService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ConstraintsBase;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PacientRecordViewController implements Initializable {

    private PacientService pacientService;
    private Pacient entity;

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
    private Button formPacientAnamnesisFinish;
    @FXML
    private Button formPacientAnamnesisReturn;



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
        }else if(event.getSource() == formPacientAnamnesisFinish){
            System.out.println("Registry pacient");
        }
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


    public void setPacientService(PacientService pacientService) {
        this.pacientService = pacientService;
    }

    public void setEntity(Pacient entity) {
        this.entity = entity;
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

    }

}
