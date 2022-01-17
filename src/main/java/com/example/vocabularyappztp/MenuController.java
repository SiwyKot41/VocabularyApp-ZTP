package com.example.vocabularyappztp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onStartButtonClick() {

    }

    @FXML
    protected void onSettingsButtonClick() {

    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
        System.exit(0);
    }

    public void initialize() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}