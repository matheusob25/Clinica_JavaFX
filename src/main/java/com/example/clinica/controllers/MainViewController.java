package com.example.clinica.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainViewController implements Initializable {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @FXML
    private Label mainLabelDate;
    @FXML
    private Label mainLabelHour;

    private void setMainLabelDate(){
        Runnable task = () -> {
            Platform.runLater(() -> {
                mainLabelDate.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now()));
            });
        };
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }
   private void setMainLabelHour(){
       Runnable task = () -> {
          Platform.runLater(() -> mainLabelHour.setText(DateTimeFormatter.ofPattern("HH:mm").format(LocalTime.now())));
       };
       scheduler.scheduleAtFixedRate(task,0,1, TimeUnit.SECONDS);


   }
   public static ScheduledExecutorService getScheduler() {
        return scheduler;
   }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMainLabelDate();
        setMainLabelHour();


    }
}
