package com.example.clinica.controllers;

import com.example.clinica.model.entities.Appointment;
import com.example.clinica.model.entities.Pacient;
import com.example.clinica.model.entities.Professional;
import com.example.clinica.model.services.AppointmentService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentViewController {
    private AppointmentService appointmentService;
    @FXML
    private TableColumn<Appointment, Appointment> actionColumn;

    @FXML
    private TableView<Appointment> appointmentTableView;

    @FXML
    private TableColumn<Appointment, LocalDateTime> dateTimeColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> durationColumn;

    @FXML
    private Button newAppointmentBttn;

    @FXML
    private TableColumn<Appointment, Pacient> pacientColumn;

    @FXML
    private TableColumn<Appointment, Professional> professionalColumn;

    @FXML
    private TextField sarchAppointment;

    @FXML
    private TableColumn<Appointment, Boolean> statusColumn;

    private ObservableList<Appointment> obsAppointments;


    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @FXML
    void onNewAppointmentBttnAction(ActionEvent event) {

    }

    @FXML
    void onSearchAppointmentAction(ActionEvent event) {

    }
}
