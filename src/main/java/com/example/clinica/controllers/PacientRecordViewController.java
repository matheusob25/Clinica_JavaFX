package com.example.clinica.controllers;

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

            // Aplica a formatação (62) 99903-1509
            if (text.length() > 11) {
                text = text.substring(0, 11); // Limita a 11 dígitos
            }
            String formatted = text;
            if (text.length() > 2) {
                formatted = "(" + text.substring(0, 2) + ") " + text.substring(2);
            }
            if (text.length() > 7) {
                formatted = formatted.substring(0, 9) + "-" + formatted.substring(9);
            }

            change.setText(formatted);
            change.setRange(0, change.getControlText().length());
            return change;
        }));
        formPacientCPF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\d]*")) {
                formPacientCPF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }

}
