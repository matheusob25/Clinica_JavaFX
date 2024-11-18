package com.example.clinica.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProfessionalRecordViewController {
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

    @FXML
    void onCancelBttnAction(ActionEvent event) {

    }

    @FXML
    void onSaveBttnAction(ActionEvent event) {

    }
}
