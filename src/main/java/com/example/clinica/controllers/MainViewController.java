package com.example.clinica.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class MainViewController {

    protected void buttonEntry(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.show();
    }
}