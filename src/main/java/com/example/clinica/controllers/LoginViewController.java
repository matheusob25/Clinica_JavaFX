package com.example.clinica.controllers;

import com.example.clinica.alerts.AlertMessage;

import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.services.AuthenticateService;
import javafx.event.ActionEvent;
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
    @FXML
    protected void onLoginButtonAction(ActionEvent event) {
        boolean login = false;
        if (loginCheckBox.isSelected() && loginPassword != null && loginName != null) {
            login = authService.login(loginName.getText(), loginVisiblePassword.getText());
        }else if((!loginCheckBox.isSelected()) && loginPassword != null && loginName != null){
            login = authService.login(loginName.getText(), loginPassword.getText());
        }else{
            alertMessage.errorMessage("Senha ou nome vazios");
        }
        if (login) {
            alertMessage.successMessage("Login efetuado com sucesso");
        }else{
            alertMessage.errorMessage("senha ou nome inválidos");
        }
    }
}
