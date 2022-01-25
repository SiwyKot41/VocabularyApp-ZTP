package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseModeController {

    private Mode mode;
    private Stage stage;
    private StateLanguage language;

    public void onTestButtonClick(ActionEvent actionEvent) throws Exception {
        mode = new Mode(new TestStateMode());
        startGame();
    }

    public void onLearnButtonClick(ActionEvent actionEvent) throws Exception {
        mode = new Mode(new LearnStateMode());
        startGame();
    }

    public void startGame() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(VocabularyApplication.class.getResource("quiz-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        QuizController quizController = fxmlLoader.<QuizController>getController();
        quizController.initialize(stage, mode, language);

        stage.setScene(scene);
        stage.show();
    }

    public void initialize(Stage stage, StateLanguage language) {
        this.stage = stage;
        this.language = language;
    }

    public void onClickBackButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VocabularyApplication.class.getResource("choose-language-mode-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        ChooseLanguageModeController chooseLanguageModeController = fxmlLoader.<ChooseLanguageModeController>getController();
        chooseLanguageModeController.initialize(stage);

        stage.setScene(scene);
        stage.setHeight(436.0);
        stage.setWidth(655.0);
        stage.show();
    }
}
