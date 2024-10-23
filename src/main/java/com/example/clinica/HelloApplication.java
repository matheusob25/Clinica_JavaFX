package com.example.clinica;

import com.example.clinica.controllers.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setMinWidth(400);
        stage.setMinHeight(600);
        stage.setTitle("Sistema Clinica");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            // Encerrar o ScheduledExecutorService quando a janela for fechada
            if (MainViewController.getScheduler() != null && !MainViewController.getScheduler().isShutdown()) {
                MainViewController.getScheduler().shutdown();
            }
        });
    }

    public static void main(String[] args) {
//      Admin admin = new Admin("dono", PasswordEncryptor.hash("12345678"));
//      adminDaoJDBC.insert(admin);
//      System.out.println(admin.getName()+ " => "+ admin.getPassword());

        launch();

    }
}