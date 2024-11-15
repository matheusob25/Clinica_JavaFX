package com.example.clinica.controllers;

import com.example.clinica.MainApplication;
import com.example.clinica.alerts.AlertMessage;
import com.example.clinica.controllers.listeners.DataChangeListener;
import com.example.clinica.model.entities.*;
import com.example.clinica.model.services.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PacientViewController implements Initializable, DataChangeListener {
    private PacientService pacientService;
    @FXML
    private TableView<Pacient> pacientViewTable;

    @FXML
    private TableColumn<Pacient, Pacient> pacientViewColumnAction;

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
    void onPacientViewAddNewBttn() {
        Pacient pacient = new Pacient();
        loadView(pacient);
    }

    private synchronized void loadView(Pacient pacient){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("pacient_record_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            PacientRecordViewController controller = fxmlLoader.getController();
            controller.setEntity(pacient);
            controller.setPacientService(new PacientService());
            controller.setServices(new AnamneseService(),new NeighborHoodService(), new CityService(), new AddressService());
            controller.subscribeDataChangeListener(this);
            controller.updateFormData();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();



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
    public void updateTableViewPacients(){
        if(pacientService == null){
            throw new IllegalStateException("Service was not instantiated");
        }
        List<Pacient> pacients = pacientService.findAll();
        obsPacients = FXCollections.observableArrayList(pacients);
        pacientViewTable.setItems(obsPacients);
        initActionButtons();
    }


    @Override
    public void onDataChanged() {
        updateTableViewPacients();
    }
    private void initActionButtons(){
        pacientViewColumnAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        pacientViewColumnAction.setCellFactory(param -> new TableCell<Pacient,Pacient>() {
            private final Button updateButton = new Button("Atualizar");
            private final Button deleteButton = new Button("X");
            HBox hBox = new HBox(5, updateButton, deleteButton);



            @Override
            protected void updateItem(Pacient pacient, boolean empty){
                super.updateItem(pacient, empty);
                if (pacient == null) {
                    setGraphic(null);
                    return;
                }else{
                    setGraphic(hBox);
                }
                updateButton.setOnAction(event -> {
                    loadView(pacientService.findById(pacient.getId()));
                });
                deleteButton.setOnAction(event -> {
                    if(AlertMessage.confirmationMessage("Tem certeza que deseja excluir esse paciente?")){
                        pacientService.delete(pacient.getId());
                        updateTableViewPacients();
                    }

                });
            }
            {
                hBox.setMaxWidth(Double.MAX_VALUE);
                hBox.setSpacing(10);
                hBox.setAlignment(Pos.CENTER);
                updateButton.setMaxWidth(Double.MAX_VALUE);
                deleteButton.setMaxWidth(Double.MAX_VALUE);
                updateButton.getStyleClass().add("bg-color");
                deleteButton.getStyleClass().add("bg-delete-color");
                HBox.setHgrow(updateButton, Priority.ALWAYS);


            }

        }
        );
    }
}
