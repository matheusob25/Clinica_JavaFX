package com.example.clinica.controllers;

import com.example.clinica.MainApplication;
import com.example.clinica.alerts.AlertMessage;
import com.example.clinica.model.entities.Admin;
import com.example.clinica.model.services.AuthenticateService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable{
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
            boolean login  = authenticateService.login(nameField.getText(), passwordField.getText());
            if(login){
                Admin admin = authenticateService.findByName(nameField.getText());
                createDialogView(event, admin);
                nameField.clear();
                passwordField.clear();
                visiblePasswordField.clear();

            }else{
                AlertMessage.errorMessage("Nome ou senha atuais incorretos, atualização de perfil cancelada");
            }
        }else{
            AlertMessage.errorMessage("Preencha todos os campos");
        }
    }
    private void createDialogView(ActionEvent event, Admin admin){
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("update-profile-view.fxml"));
            Pane dialogPane = loader.load();
            Stage stage = new Stage();
            UpdateProfileViewController controller = loader.getController();
            controller.setAuthenticateService(authenticateService);
            controller.updateData(admin);
            stage.setTitle("Digite os novos dados do usuário");
            stage.setResizable(false);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(dialogPane));
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
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
