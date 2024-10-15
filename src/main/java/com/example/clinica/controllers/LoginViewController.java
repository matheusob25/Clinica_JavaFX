package com.example.clinica.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginViewController {

    @FXML
    private TextField login_name;
    @FXML
    private PasswordField login_password;
    @FXML
    private CheckBox login_checkBox;
    @FXML
    private Button login_button_signUp;
    public void checkBoxOnEntry() {
        if(login_checkBox.isSelected()) {
            login_password.isHover();
        }
    }
    @FXML
    protected void buttonEntry(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.show();
    }
}
