package com.example.clinica.controllers;

import com.example.clinica.alerts.AlertMessage;

import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.services.AuthenticateService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginViewController {

    private final AlertMessage alertMessage = new AlertMessage();
    private AuthenticateService authService;
    @FXML
    private TextField loginName;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private CheckBox loginCheckBox;
    @FXML
    private Button loginButton;
    @FXML
    private TextField loginVisiblePassword;
    @FXML
    public void initialize() {
        // Inicialmente, o campo loginVisiblePassword deve estar invisível e não gerenciado
        loginVisiblePassword.setVisible(false);
        loginVisiblePassword.setManaged(false);
        authService = new AuthenticateService(DaoFactory.createAdminDao());
    }

    @FXML
    public void onLoginCheckBoxAction() {
        if (loginCheckBox.isSelected()) {
            loginVisiblePassword.setText(loginPassword.getText());
            loginVisiblePassword.setVisible(true);
            loginVisiblePassword.setManaged(true);
            loginPassword.setVisible(false);
            loginPassword.setManaged(false);
        }else{
            loginPassword.setText(loginVisiblePassword.getText());
            loginPassword.setVisible(true);
            loginPassword.setManaged(true);
            loginVisiblePassword.setVisible(false);
            loginVisiblePassword.setManaged(false);

        }
    }
    private void passwordVisibleOrInvisible() {
        if (loginVisiblePassword.isVisible()) {
            if(!loginPassword.getText().equals(loginVisiblePassword.getText())) {
                loginPassword.setText(loginVisiblePassword.getText());
            }
        }else{
            if(!loginVisiblePassword.getText().equals(loginPassword.getText())) {
                loginVisiblePassword.setText(loginPassword.getText());
            }
        }
    }

    @FXML
    protected void onLoginButtonAction() {
        passwordVisibleOrInvisible();
        boolean login = false;
        if(loginPassword.getText().isEmpty() || loginName.getText().isEmpty()) {
            alertMessage.errorMessage("Por favor preencha todos os campos");
        }
        login = authService.login(loginName.getText(), loginPassword.getText());

        if (!login) {
            alertMessage.errorMessage("nome ou senha não coincidem");
        }else{
            alertMessage.successMessage("Login efetuado com sucesso");
        }
    }
}
