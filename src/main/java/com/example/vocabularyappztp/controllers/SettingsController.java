package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.VocabularyApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
    }
    public void onClickBackButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VocabularyApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        MenuController menuController = fxmlLoader.<MenuController>getController();
        menuController.initialize(stage);

        stage.setScene(scene);
        stage.setHeight(436.0);
        stage.setWidth(655.0);
        stage.show();
    }
}
