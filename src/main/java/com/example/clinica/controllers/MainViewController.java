package com.example.clinica.controllers;

import com.example.clinica.MainApplication;
import com.example.clinica.alerts.AlertMessage;
import com.example.clinica.model.services.PacientService;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

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
    @FXML
    private Button mainViewPacientsBttn;
    @FXML
    private Label mainViewGeneralInfo;

    @FXML
    private Button mainViewGeneralInfoBttn;

    @FXML
    private AnchorPane mainViewCenterAnchorPane;
    @FXML
    private AnchorPane mainViewGeneralInfoAnchorPane;



    @FXML
    void onMainViewGeneralInfoBttnAction() {
        returnToMain();
        mainViewGeneralInfo.setVisible(true);

    }

    @FXML
    void onMainViewPacientsBttnAction() {
        loadView("pacient-view.fxml", (PacientViewController pacientViewController) -> {
            pacientViewController.setPacientService(new PacientService());
            pacientViewController.updateTableViewPacients();


        });
        mainViewGeneralInfo.setVisible(false);
    }
    private synchronized <T> void loadView(String absolutePath, Consumer<T> action) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(absolutePath));
            AnchorPane newAnchorPane = fxmlLoader.load();

            mainViewCenterAnchorPane.getChildren().clear();
            mainViewCenterAnchorPane.getChildren().add(newAnchorPane);

            AnchorPane.setTopAnchor(newAnchorPane, 0.0);
            AnchorPane.setBottomAnchor(newAnchorPane, 0.0);
            AnchorPane.setLeftAnchor(newAnchorPane, 0.0);
            AnchorPane.setRightAnchor(newAnchorPane, 0.0);

            T controller = fxmlLoader.getController();
            action.accept(controller);

        }catch (IOException e){
            AlertMessage.errorMessage(e.getMessage());
        }
    }
    private synchronized void returnToMain() {
        mainViewCenterAnchorPane.getChildren().clear();
        mainViewCenterAnchorPane.getChildren().add(mainViewGeneralInfoAnchorPane);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMainLabelDate();
        setMainLabelHour();
        onClickLogout();
    }


    private void onClickLogout(){
        mainLabelLogout.setOnMouseClicked(this::handleClick);
        mainIconLogout.setOnMouseClicked(this::handleClick);
    }

    private void handleClick(MouseEvent event){
        if(AlertMessage.confirmationMessage("Deseja sair?")) {
            Scene loginScene = MainApplication.getScene();
            Stage stage = new Stage();
            stage.setScene(loginScene);
            stage.show();
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
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
