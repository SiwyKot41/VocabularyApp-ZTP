module com.example.vocabularyappztp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.vocabularyappztp to javafx.fxml;
    exports com.example.vocabularyappztp;
    exports com.example.vocabularyappztp.controllers;
    opens com.example.vocabularyappztp.controllers to javafx.fxml;
    exports com.example.vocabularyappztp.model;
    opens com.example.vocabularyappztp.model to javafx.fxml;
    exports com.example.vocabularyappztp.controllers.builder;
    opens com.example.vocabularyappztp.controllers.builder to javafx.fxml;
    exports com.example.vocabularyappztp.model.singleton;
    opens com.example.vocabularyappztp.model.singleton to javafx.fxml;
}