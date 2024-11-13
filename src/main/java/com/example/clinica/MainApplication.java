package com.example.clinica;

import com.example.clinica.controllers.MainViewController;
import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.dao.impl.AdminDaoJDBC;
import com.example.clinica.model.entities.Admin;
import com.example.clinica.model.services.CityService;
import com.example.clinica.utils.PasswordEncryptor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setMinWidth(400);
        stage.setMinHeight(600);
        stage.setTitle("Sistema CSI");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    @Override
    public void stop() throws Exception {
        MainViewController.getScheduler().shutdownNow();
        super.stop();
    }
    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();

    }
}