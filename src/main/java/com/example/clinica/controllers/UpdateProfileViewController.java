package com.example.clinica.controllers;

import com.example.clinica.alerts.AlertMessage;
import com.example.clinica.db.DbException;
import com.example.clinica.model.entities.Admin;
import com.example.clinica.model.services.AuthenticateService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProfileViewController implements Initializable {
    private AuthenticateService authenticateService;
    private Admin admin;
    @FXML
    private PasswordField confirmationPasswordField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button saveUpdateBttn;
    @FXML
    private Button cancelUpdateBttn;

    public void setAuthenticateService(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    public void updateData(Admin admin) {
        this.admin = admin;
        nameField.setText(admin.getName());
    }

    @FXML
    void onSaveUpdateBttnAction(ActionEvent event) {
        if(authenticateService == null){
            throw new IllegalStateException("authenticateService is null");
        }
        if (!nameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !confirmationPasswordField.getText().isEmpty() ) {
            if (passwordField.getText().equals(confirmationPasswordField.getText())) {
                admin.setName(nameField.getText());
                admin.setPassword(passwordField.getText());
                try {
                    authenticateService.update(admin);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                AlertMessage.successMessage("Usuário atualizado com sucesso!");
                ((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
            } else {
                AlertMessage.errorMessage("As senhas não coincidem");
            }
        }else{
            AlertMessage.errorMessage("Preencha todos os campos");
        }
    }
    @FXML
    public void onCancelUpdateBttnAction(ActionEvent event) {
        ((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
