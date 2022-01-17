package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.Mode;
import com.example.vocabularyappztp.StateMode;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class QuizController {

    private Stage stage;
    private Mode mode;

    @FXML
    private Label label;

    public void initialize(Stage stage, Mode mode) {
        this.stage = stage;
        this.mode = mode;

        mode.setTitle(label);
    }

}
