module com.example.clinica {
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires de.jensd.fx.glyphs.materialdesignicons;
    requires javafx.controls;
    requires java.desktop;


    opens com.example.clinica to javafx.fxml;
    exports com.example.clinica;
    exports com.example.clinica.controllers;
    opens com.example.clinica.controllers to javafx.fxml;
}