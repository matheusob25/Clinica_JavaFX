package com.example.clinica;

import com.example.clinica.controllers.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setMinWidth(400);
        stage.setMinHeight(600);
        stage.setTitle("Sistema CSI");
        stage.setScene(scene);
        stage.show();

    }
    @Override
    public void stop() throws Exception {
        MainViewController controller = new MainViewController();
        controller.shutdownScheduler();
        super.stop();
    }

    public static void main(String[] args) {
//      Admin admin = new Admin("dono", PasswordEncryptor.hash("12345678"));
//      adminDaoJDBC.insert(admin);
//      System.out.println(admin.getName()+ " => "+ admin.getPassword());

        launch();

    }
}