package com.example.clinica.controllers;

import com.example.clinica.alerts.AlertMessage;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.LocalDate;
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
    @FXML
    private Label mainLabelLogout;
    @FXML
    private MaterialDesignIconView mainIconLogout;

    @FXML
    private BorderPane mainBorderPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMainLabelDate();
        setMainLabelHour();
        onClickLogout();
    }
    public void shutdownScheduler() {
        getScheduler().shutdownNow();
    }

    private void onClickLogout(){
        mainLabelLogout.setOnMouseClicked(event -> {
            handleClick(mainLabelDate, mainIconLogout);
        });
        mainIconLogout.setOnMouseClicked(event -> {
            handleClick(mainLabelDate, mainIconLogout);
        });
    }
    private void handleClick(Label label, MaterialDesignIconView iconView){
        AlertMessage.confirmationMessage("Logout");
    }


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

}
