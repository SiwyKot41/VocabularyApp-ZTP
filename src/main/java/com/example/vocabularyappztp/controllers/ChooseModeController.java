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
        quizController.initialize(stage, mode);

        stage.setScene(scene);
        stage.show();
    }

    public void initialize(Stage stage) {
        this.stage = stage;
    }
}
