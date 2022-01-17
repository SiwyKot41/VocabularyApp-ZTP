package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.VocabularyApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Label welcomeText;

    private Stage stage;

    @FXML
    protected void onStartButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VocabularyApplication.class.getResource("choose-mode-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        ChooseModeController chooseModeController = fxmlLoader.<ChooseModeController>getController();
        chooseModeController.initialize(stage);

        stage.setScene(scene);
        stage.setHeight(436.0);
        stage.setWidth(655.0);
        stage.show();
    }

    @FXML
    protected void onSettingsButtonClick() {

    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
        System.exit(0);
    }

    public void initialize(Stage stage) {
        welcomeText.setText("Welcome to JavaFX Application!");
        this.stage = stage;
    }
}