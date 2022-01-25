package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.Mode;
import com.example.vocabularyappztp.VocabularyApplication;
import com.example.vocabularyappztp.controllers.iterator.Container;

import com.example.vocabularyappztp.model.Question;
import com.example.vocabularyappztp.model.QuestionSingleChoiceAnswer;
import com.example.vocabularyappztp.model.QuestionWriteByYourself;
import com.example.vocabularyappztp.model.Quiz;
import com.example.vocabularyappztp.model.Word;
import com.example.vocabularyappztp.model.decorator.*;
import com.example.vocabularyappztp.model.singleton.Progress;
import com.example.vocabularyappztp.controllers.builder.AnswerSingleChoiceBuilder;
import com.example.vocabularyappztp.controllers.builder.AnswerWriteByYourselfBuilder;
import com.example.vocabularyappztp.controllers.builder.AnswersBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class QuizController {

    @FXML
    public Label questionText;
    public Label pointsText;
    public Label writeBonusText;
    public Button buttonA;
    public Button buttonB;
    public Button buttonC;
    public Button buttonD;
    public Button choiceBonusButton;
    public Button writeBonusButton;
    public Button backButton;
    public TextField typedWord;
    public Button buttonNext;


    public int points = 0;
    public int questionCount = 0;
    Points pointObject;

    public int choiceBonusPrice = 6;
    public int writeBonusPrice = 3;
    public String writeBonusLetters = "";

    private Quiz quiz;
    private Stage stage;
    private Mode mode;

    private ArrayList<Word> words = new ArrayList<>();
    private ArrayList<Question> questions = new ArrayList<>();
    private Container container;
    private Question selectedQuestion;
    private HashMap<Button, String> chosenAnswer = new HashMap<>();
    public static boolean lastAnswerWasCorrect = true;


    @FXML
    private Label label;

    public void initialize(Stage stage, Mode mode) throws Exception {
        this.stage = stage;
        this.mode = mode;
        mode.setTitle(label);

        words = Utils.readWords();
        questions = createQuestions();
        quiz = new Quiz(questions);

        if (!Progress.isExisting()) {
            for (Word word : words) {
                Progress.getInstance().putKnownWord(word, 0);
            }
        }

        container = new Container(questions);
        prepareQuestion();
    }

    public ArrayList<Question> createQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        for (Word word : words) {
            AnswersBuilder answersBuilder = new AnswerSingleChoiceBuilder();
            answersBuilder.createSingleChoiceQuestion(word, words);
            questions.add(answersBuilder.build());

            answersBuilder = new AnswerWriteByYourselfBuilder();
            answersBuilder.createWriteByYourselfQuestion(word);
            questions.add(answersBuilder.build());
        }

        return questions;
    }

    public void prepareQuestion() {
        pointObject = new PointsImpl();

        selectedQuestion = mode.chooseQuestion(container);
        writeBonusText.setText(writeBonusLetters);
        writeBonusText.setDisable(true);

        if (selectedQuestion instanceof QuestionSingleChoiceAnswer) {
            prepareSingleChoiceQuestion();
        } else if (selectedQuestion instanceof QuestionWriteByYourself) {
            prepareWriteByYourselfQuestion();
        }
    }

    public void prepareSingleChoiceQuestion() {

        choiceBonusButton.setVisible(false);
        choiceBonusButton.setText("Bonus");

        if(label.getText() == "Learn") {
            pointObject = new SingleChoicePointDecorator(pointObject);
            pointsText.setText("Points: " + points);

            choiceBonusButton.setVisible(true);
            choiceBonusButton.setDisable(true);

            if (points >= choiceBonusPrice){
                choiceBonusButton.setDisable(false);
            }
        }
        else{
            pointObject = new TestPointDecorator(pointObject);
        }

        questionText.setText("Wybierz prawidłowe tłumaczenie dla " + selectedQuestion.getCorrectWord().getEnglishWord());
        Set<Word> wordsToChoice = ((QuestionSingleChoiceAnswer) selectedQuestion).getAllWordsToChoice();
        List<Word> allWordsToChoice = new ArrayList<>(wordsToChoice);

        buttonA.setVisible(true);
        buttonA.setText(String.valueOf(allWordsToChoice.get(0).getPolishWord()));
        chosenAnswer.put(buttonA, allWordsToChoice.get(0).getPolishWord());

        buttonB.setVisible(true);
        buttonB.setText(String.valueOf(allWordsToChoice.get(1).getPolishWord()));
        chosenAnswer.put(buttonB, allWordsToChoice.get(1).getPolishWord());

        buttonC.setVisible(true);
        buttonC.setText(String.valueOf(allWordsToChoice.get(2).getPolishWord()));
        chosenAnswer.put(buttonC, allWordsToChoice.get(2).getPolishWord());

        buttonD.setVisible(true);
        buttonD.setText(String.valueOf(allWordsToChoice.get(3).getPolishWord()));
        chosenAnswer.put(buttonD, allWordsToChoice.get(3).getPolishWord());

        writeBonusButton.setVisible(false);
        typedWord.setVisible(false);
        buttonNext.setVisible(false);
    }

    public void prepareWriteByYourselfQuestion() {

        writeBonusButton.setVisible(false);
        writeBonusButton.setText("Bonus");

        if(label.getText() == "Learn") {
            pointObject = new WritePointDecorator(pointObject);
            pointsText.setText("Points: " + points);

            writeBonusButton.setVisible(true);
            writeBonusButton.setDisable(true);

            if(points >= writeBonusPrice){
                writeBonusButton.setDisable(false);
            }
        }
        else{
            pointObject = new TestPointDecorator(pointObject);
        }

        questionText.setText("Wpisz tłumaczenie słowa " + selectedQuestion.getCorrectWord().getEnglishWord());

        buttonA.setVisible(false);
        buttonB.setVisible(false);
        buttonC.setVisible(false);
        buttonD.setVisible(false);
        choiceBonusButton.setVisible(false);

        typedWord.setText("");
        typedWord.setVisible(true);
        buttonNext.setVisible(true);
    }

    public void onClickAnswerA(ActionEvent actionEvent) {
        updateProgress(buttonA);
        prepareQuestion();
    }

    public void onClickAnswerB(ActionEvent actionEvent) {
        updateProgress(buttonB);
        prepareQuestion();
    }

    public void onClickAnswerC(ActionEvent actionEvent) {
        updateProgress(buttonC);
        prepareQuestion();
    }

    public void onClickAnswerD(ActionEvent actionEvent) {
        updateProgress(buttonD);
        prepareQuestion();
    }

    public void onClickNext(ActionEvent actionEvent) {
        writeBonusLetters = "";
        writeBonusText.setText(writeBonusLetters);
        writeBonusText.setDisable(false);
        chosenAnswer.put(buttonNext, typedWord.getText());
        updateProgress(buttonNext);
        prepareQuestion();
    }

    public void onClickWriteBonus(ActionEvent actionEvent) {
        if(writeBonusLetters.length() != selectedQuestion.getCorrectWord().getPolishWord().length()){
            writeBonusLetters = selectedQuestion.getCorrectWord().getPolishWord().substring(0, writeBonusLetters.length()+1);
            points -= writeBonusPrice;
            pointsText.setText("Points: " + points);
            writeBonusText.setText(writeBonusLetters);
            writeBonusText.setDisable(false);
            if (points<writeBonusPrice){
                writeBonusButton.setDisable(true);
            }
        }

        System.out.println(writeBonusLetters);
    }

    public void onClickChoiceBonus(ActionEvent actionEvent) {

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


    public void updateProgress(Button button) {

        if (chosenAnswer.get(button).equals(selectedQuestion.getCorrectWord().getPolishWord())) {
            Progress.getInstance().putKnownWord(selectedQuestion.getCorrectWord(), Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) + 1);
            QuizController.lastAnswerWasCorrect = true;
            points += pointObject.getPoints();
            System.out.println(points);
        } else if (!chosenAnswer.get(button).equals(selectedQuestion.getCorrectWord().getPolishWord()) && Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) > 0) {
            Progress.getInstance().putKnownWord(selectedQuestion.getCorrectWord(), Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) - 1);
            QuizController.lastAnswerWasCorrect = false;
        } else {
            QuizController.lastAnswerWasCorrect = false;
        }
        questionCount += 1;
        if(label.getText() == "Test" && questionCount == 10){
            pointsText.setText("Points: " + points);
        }
    }
}