module com.example.clinica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.clinica to javafx.fxml;
    exports com.example.clinica;
    exports com.example.clinica.controllers;
    opens com.example.clinica.controllers to javafx.fxml;
}