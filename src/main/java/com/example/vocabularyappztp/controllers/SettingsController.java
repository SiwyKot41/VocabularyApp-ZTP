package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.VocabularyApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    public static String difficulty = "EASY";
    private Stage stage;
    @FXML
    private Button easyButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button hardButton;


    public void initialize(Stage stage) {

        System.out.println(easyButton.getText().toUpperCase().equals(difficulty));
        this.stage = stage;
        if (easyButton.getText().toUpperCase().equals(difficulty)) easyButton.setStyle("-fx-background-color: #23A9D6;");
        if (mediumButton.getText().toUpperCase().equals(difficulty)) mediumButton.setStyle("-fx-background-color: #23A9D6;");
        if (hardButton.getText().toUpperCase().equals(difficulty)) hardButton.setStyle("-fx-background-color: #23A9D6;");

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

    public void onClickEasyButton(ActionEvent actionEvent) {
        difficulty = "EASY";
        easyButton.setStyle("-fx-background-color: #23A9D6; ");
        mediumButton.setStyle("-fx-background-color: #E7E7E7;");
        hardButton.setStyle("-fx-background-color: #E7E7E7;");
    }

    public void onClickMediumButton(ActionEvent actionEvent) {
        difficulty = "MEDIUM";
        mediumButton.setStyle("-fx-background-color: #23A9D6; ");
        easyButton.setStyle("-fx-background-color: #E7E7E7;");
        hardButton.setStyle("-fx-background-color: #E7E7E7;");
    }

    public void onClickHardButton(ActionEvent actionEvent) {
        difficulty = "HARD";
        hardButton.setStyle("-fx-background-color: #23A9D6;");
        easyButton.setStyle("-fx-background-color: #E7E7E7;");
        mediumButton.setStyle("-fx-background-color: #E7E7E7;");
    }
}
