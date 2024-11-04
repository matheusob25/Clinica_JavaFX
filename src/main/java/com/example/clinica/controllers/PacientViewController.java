package com.example.clinica.controllers;

import com.example.clinica.MainApplication;
import com.example.clinica.alerts.AlertMessage;
import com.example.clinica.model.entities.Pacient;
import com.example.clinica.model.services.PacientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;

public class PacientViewController implements Initializable {
    private PacientService pacientService;
    @FXML
    private TableView<Pacient> pacientViewTable;

    @FXML
    private TableColumn<?,?> pacientViewColumnAction;

    @FXML
    private TableColumn<Pacient, Integer> pacientViewColumnAge;

    @FXML
    private TableColumn<Pacient, String> pacientViewColumnCPF;

    @FXML
    private TableColumn<Pacient, String> pacientViewColumnEmail;

    @FXML
    private TableColumn<Pacient, String> pacientViewColumnName;

    @FXML
    private TableColumn<Pacient, String> pacientViewColumnNumber;

    private ObservableList<Pacient> obsPacients;


    @FXML
    private Button pacientViewAddNewBttn;

    public void setPacientService(PacientService pacientService) {
        this.pacientService = pacientService;
    }
    @FXML
    void onpacientViewAddNewBttn() {
        loadView();
    }

    private synchronized void loadView(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("pacient_record_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        }catch (LoadException e){
            AlertMessage.errorMessage("erro ao carregar pagina: "+ e.getMessage());

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        pacientViewColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        pacientViewColumnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        pacientViewColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        pacientViewColumnNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        pacientViewColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

    }
    public void initializePacientsTable(){
        if(pacientService == null){
            throw new IllegalStateException("Service was not instantiated");
        }
        List<Pacient> pacients = pacientService.findAll();
        obsPacients = FXCollections.observableArrayList(pacients);
        pacientViewTable.setItems(obsPacients);
    }



}
