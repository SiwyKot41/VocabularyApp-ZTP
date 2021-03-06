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
        FXMLLoader fxmlLoader = new FXMLLoader(VocabularyApplication.class.
                getResource("choose-language-mode-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        ChooseLanguageModeController chooseLanguageModeController = fxmlLoader.<ChooseLanguageModeController>getController();
        chooseLanguageModeController.initialize(stage);

        stage.setScene(scene);
        stage.setHeight(436.0);
        stage.setWidth(655.0);
        stage.show();
    }

    @FXML
    protected void onSettingsButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VocabularyApplication.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        SettingsController settingsController = fxmlLoader.<SettingsController>getController();
        settingsController.initialize(stage);

        stage.setScene(scene);
        stage.setHeight(436.0);
        stage.setWidth(655.0);
        stage.show();
    }

    @FXML
    protected void onWordsListButtonClick() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(VocabularyApplication.class.getResource("words-list-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        WordsListController wordsListController = fxmlLoader.<WordsListController>getController();
        wordsListController.initialize(stage);

        stage.setTitle("Lista słówek");
        stage.setScene(scene);
        stage.setHeight(436.0);
        stage.setWidth(655.0);
        stage.show();
    }
    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
        System.exit(0);
    }

    public void initialize(Stage stage) {
        welcomeText.setText("Welcome to Vocabulary Application!");
        this.stage = stage;
    }
}