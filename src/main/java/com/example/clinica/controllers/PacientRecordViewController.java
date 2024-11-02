package com.example.clinica.controllers;

import com.example.clinica.model.services.PacientService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PacientRecordViewController implements Initializable {
    private PacientService pacientService;

    @FXML
    private BorderPane formPacientAddress;

    @FXML
    private BorderPane formPacientAnamnesis;
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
    private TextField formPacientProfession;

    @FXML
    private DatePicker formPacientStartTreat;

    @FXML
    private Button formPacientRegistryProceed;
    @FXML
    private Button formPacientAnamnesisFinish;

    @FXML
    private Button formPacientAnamnesisReturn;
    @FXML
    private Button formPacientAddressProceed;

    @FXML
    private Button formPacientAddressReturn;

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


    private void setInitialState(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFormPacientMaritalStatus();
        setInitialState(new ActionEvent());


    }

}
