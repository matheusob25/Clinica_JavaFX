package com.example.clinica.controllers;

import javafx.event.ActionEvent;
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
    @FXML
    private TextField login_visible_password;
    @FXML
    public void initialize() {
        // Inicialmente, o campo login_visible_password deve estar invisível e não gerenciado
        login_visible_password.setVisible(false);
        login_visible_password.setManaged(false);
    }

    @FXML
    public void onLoginCheckBoxAction(ActionEvent event) {
        if (login_checkBox.isSelected()) {
            login_visible_password.setText(login_password.getText());
            login_visible_password.setVisible(true);
            login_visible_password.setManaged(true);
            login_password.setVisible(false);
            login_password.setManaged(false);
        }else{
            login_password.setText(login_visible_password.getText());
            login_password.setVisible(true);
            login_password.setManaged(true);
            login_visible_password.setVisible(false);
            login_visible_password.setManaged(false);

        }
    }
    @FXML
    protected void buttonEntry(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.show();
    }
}
