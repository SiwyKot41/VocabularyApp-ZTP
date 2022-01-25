package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseLanguageModeController {

    private Stage stage;
    private StateLanguage language;

    public void onEnglishButtonClick(ActionEvent actionEvent) throws IOException {
        language = new EnglishStateLanguage();

        FXMLLoader fxmlLoader = new FXMLLoader(VocabularyApplication.class.getResource("choose-mode-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        ChooseModeController chooseModeController = fxmlLoader.<ChooseModeController>getController();
        chooseModeController.initialize(stage, language);

        stage.setScene(scene);
        stage.setHeight(436.0);
        stage.setWidth(655.0);
        stage.show();
    }

    public void onPolishButtonClick(ActionEvent actionEvent) throws IOException {

        language = new PolishStateLanguage();

        FXMLLoader fxmlLoader = new FXMLLoader(VocabularyApplication.class.getResource("choose-mode-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        ChooseModeController chooseModeController = fxmlLoader.<ChooseModeController>getController();
        chooseModeController.initialize(stage, language);

        stage.setScene(scene);
        stage.setHeight(436.0);
        stage.setWidth(655.0);
        stage.show();
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

    public void initialize(Stage stage) {
        this.stage = stage;
    }
}
