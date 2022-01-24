package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.Mode;
import com.example.vocabularyappztp.controllers.iterator.Container;
import com.example.vocabularyappztp.model.*;
import com.example.vocabularyappztp.model.singleton.Progress;
import com.example.vocabularyappztp.controllers.builder.AnswerSingleChoiceBuilder;
import com.example.vocabularyappztp.controllers.builder.AnswerWriteByYourselfBuilder;
import com.example.vocabularyappztp.controllers.builder.AnswersBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.*;

public class QuizController {

    @FXML
    public Label questionText;
    public Button buttonA;
    public Button buttonB;
    public Button buttonC;
    public Button buttonD;
    public TextField typedWord;
    public Button buttonNext;

    private Quiz quiz;
    private Stage stage;
    private Mode mode;

    private ArrayList<Word> words = new ArrayList<Word>();
    private ArrayList<Question> questions = new ArrayList<Question>();
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
        selectedQuestion = mode.chooseQuestion(container);

        if (selectedQuestion instanceof QuestionSingleChoiceAnswer) {
            prepareSingleChoiceQuestion();
        } else if (selectedQuestion instanceof QuestionWriteByYourself) {
            prepareWriteByYourselfQuestion();
        }
    }

    public void prepareSingleChoiceQuestion() {
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

        typedWord.setVisible(false);
        buttonNext.setVisible(false);
    }

    public void prepareWriteByYourselfQuestion() {
        questionText.setText("Wpisz tłumaczenie słowa " + selectedQuestion.getCorrectWord().getEnglishWord());

        buttonA.setVisible(false);
        buttonB.setVisible(false);
        buttonC.setVisible(false);
        buttonD.setVisible(false);

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
        chosenAnswer.put(buttonNext, typedWord.getText());
        updateProgress(buttonNext);
        prepareQuestion();
    }

    public void updateProgress(Button button) {
        if (chosenAnswer.get(button).equals(selectedQuestion.getCorrectWord().getPolishWord())) {
            Progress.getInstance().putKnownWord(selectedQuestion.getCorrectWord(), Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) + 1);
            QuizController.lastAnswerWasCorrect = true;
        } else if (!chosenAnswer.get(button).equals(selectedQuestion.getCorrectWord().getPolishWord()) && Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) > 0) {
            Progress.getInstance().putKnownWord(selectedQuestion.getCorrectWord(), Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) - 1);
            QuizController.lastAnswerWasCorrect = false;
        } else {
            QuizController.lastAnswerWasCorrect = false;
        }
    }
}