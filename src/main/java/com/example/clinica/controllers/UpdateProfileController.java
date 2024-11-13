package com.example.clinica.controllers;

import com.example.clinica.alerts.AlertMessage;
import com.example.clinica.model.services.AuthenticateService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProfileController implements Initializable{
    private  AuthenticateService authenticateService;
    @FXML
    private CheckBox checkBoxVisiblePassword;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button saveUpdateBttn;

    @FXML
    private TextField visiblePasswordField;

    public void setAuthenticateService(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @FXML
    void onCheckBoxVisiblePasswordAction(ActionEvent event) {
        if(checkBoxVisiblePassword.isSelected()){
            visiblePasswordField.setText(passwordField.getText());
            visiblePasswordField.setVisible(true);
            visiblePasswordField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
        }else{
            passwordField.setText(visiblePasswordField.getText());
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            visiblePasswordField.setVisible(false);
            visiblePasswordField.setManaged(false);
        }
    }
    private void passwordVisibleOrInvisible() {
        if (visiblePasswordField.isVisible()) {
            if(!passwordField.getText().equals(visiblePasswordField.getText())) {
                passwordField.setText(visiblePasswordField.getText());
            }
        }else{
            if(!passwordField.getText().equals(visiblePasswordField.getText())) {
                visiblePasswordField.setText(passwordField.getText());
            }
        }
    }

    @FXML
    void onSaveUpdateBttnAction(ActionEvent event) {
        passwordVisibleOrInvisible();
        if(!passwordField.getText().isEmpty() || !nameField.getText().isEmpty()){
            if(AlertMessage.confirmationMessage("Tem certeza que deseja atualizar o usuário?")){

            }
        }else{
            AlertMessage.errorMessage("Preencha todos os campos");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordField.setVisible(true);
        passwordField.setManaged(true);
        visiblePasswordField.setVisible(false);
        visiblePasswordField.setManaged(false);
    }
}
