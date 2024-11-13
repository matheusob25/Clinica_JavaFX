package com.example.clinica.controllers;

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
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordField.setVisible(true);
        passwordField.setManaged(true);
        visiblePasswordField.setVisible(false);
        visiblePasswordField.setManaged(false);
    }
}
