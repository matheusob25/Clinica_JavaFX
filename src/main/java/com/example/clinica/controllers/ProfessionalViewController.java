package com.example.clinica.controllers;

import com.example.clinica.MainApplication;
import com.example.clinica.alerts.AlertMessage;
import com.example.clinica.controllers.listeners.DataChangeListener;
import com.example.clinica.model.entities.Pacient;
import com.example.clinica.model.entities.Professional;
import com.example.clinica.model.services.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.fxml.LoadException;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfessionalViewController implements Initializable, DataChangeListener {
    private ProfessionalService professionalService;

    @FXML
    private TableView<Professional> tableProfessionals;
    @FXML
    private Button addNewProfessionalBttn;

    @FXML
    private TableColumn<Professional, Professional> columnAction;

    @FXML
    private TableColumn<Professional, String> columnDescription;

    @FXML
    private TableColumn<Professional, String> columnName;

    @FXML
    private TableColumn<Professional, String> columnNumber;

    @FXML
    private TextField searchProfessionalField;
    private FilteredList<Professional>  tableListProfessionals;

    public void setService(ProfessionalService service) {
        this.professionalService = service;
    }
    public void dynamicSearch () {
        searchProfessionalField.textProperty().addListener((observable, oldValue, newValue) -> {
            tableListProfessionals.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                return item.getName().toLowerCase().contains(lowerCaseFilter); // Filtragem case-insensitive
            });
        });
    }


    @FXML
    void onAddNewProfessionalBttnAction(ActionEvent event) {
        Professional newProfessional = new Professional();
        loadView(event,newProfessional);
    }
    private synchronized void loadView(ActionEvent event,Professional professional){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("professional_record_view.fxml"));
            AnchorPane pane = fxmlLoader.load();
            ProfessionalRecordViewController controller = fxmlLoader.getController();
            controller.setProfessionalService(professionalService);
            controller.subscribeDataChangeListener(this);
            controller.setProfessional(professional);
            controller.updateFormData();

            Stage stage = new Stage();
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(pane));
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
        dynamicSearch();
    }
    private void initializeNodes(){
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
    public void updateTableViewProfessionals(){
        if(professionalService == null){
            throw new IllegalStateException("Professional service is null");
        }
        List<Professional> professionals = professionalService.findAll();
        ObservableList<Professional> professionalObservableList = FXCollections.observableList(professionals);
        tableListProfessionals = new FilteredList<>(professionalObservableList);
        tableProfessionals.setItems(tableListProfessionals);
        initActionButtons();
    }


    private void initActionButtons(){
        columnAction.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        columnAction.setCellFactory(param -> new TableCell<Professional,Professional>() {
                    private final Button updateButton = new Button("Atualizar");
                    private final Button deleteButton = new Button("X");
                    HBox hBox = new HBox(5, updateButton, deleteButton);



                    @Override
                    protected void updateItem(Professional professional, boolean empty){
                        super.updateItem(professional, empty);
                        if (professional == null) {
                            setGraphic(null);
                            return;
                        }else{
                            setGraphic(hBox);
                        }
                        updateButton.setOnAction(event -> {
                            loadView(event,professionalService.findById(professional.getId()));
                        });
                        deleteButton.setOnAction(event -> {
                            if(AlertMessage.confirmationMessage("Tem certeza que deseja excluir esse paciente?")){
                                professionalService.delete(professional.getId());
                                updateTableViewProfessionals();
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


    @Override
    public void onDataChanged() {
        updateTableViewProfessionals();
    }
}
