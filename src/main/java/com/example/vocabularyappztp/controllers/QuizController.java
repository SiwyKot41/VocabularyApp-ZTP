package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.Mode;
import com.example.vocabularyappztp.model.*;
import com.example.vocabularyappztp.model.singleton.Progress;
import com.example.vocabularyappztp.controllers.builder.AnswerSingleChoiceBuilder;
import com.example.vocabularyappztp.controllers.builder.AnswerWriteByYourselfBuilder;
import com.example.vocabularyappztp.controllers.builder.AnswersBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.*;

public class QuizController {

    @FXML
    public Label questionText;
    public Button buttonA;
    public Button buttonB;
    public Button buttonC;
    public Button buttonD;

    private Quiz quiz;
    private Stage stage;
    private Mode mode;

    private ArrayList<Word> words = new ArrayList<Word>();
    private ArrayList<Question> questions = new ArrayList<Question>();
    private Iterator<Question> questionIterator;
    private Question selectedQuestion;
    private HashMap<Button, Word> chosenAnswer = new HashMap<>();

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

        questionIterator = questions.iterator();
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
        questionIterator = questions.iterator();
        selectedQuestion = mode.chooseQuestion(questionIterator);

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
        chosenAnswer.put(buttonA, allWordsToChoice.get(0));

        buttonB.setVisible(true);
        buttonB.setText(String.valueOf(allWordsToChoice.get(1).getPolishWord()));
        chosenAnswer.put(buttonB, allWordsToChoice.get(1));

        buttonC.setVisible(true);
        buttonC.setText(String.valueOf(allWordsToChoice.get(2).getPolishWord()));
        chosenAnswer.put(buttonC, allWordsToChoice.get(2));

        buttonD.setVisible(true);
        buttonD.setText(String.valueOf(allWordsToChoice.get(3).getPolishWord()));
        chosenAnswer.put(buttonD, allWordsToChoice.get(3));
    }

    public void prepareWriteByYourselfQuestion() {
        questionText.setText("Wpisz tłumaczenie słowa " + selectedQuestion.getCorrectWord().getEnglishWord());

        buttonA.setVisible(false);
        buttonB.setVisible(false);
        buttonC.setVisible(false);
        buttonD.setVisible(false);
    }

    public void onClickAnswerA(ActionEvent actionEvent) {
        updateProgress(buttonA);
        prepareQuestion();
        System.out.println("jestem tutaj przycisk a");
    }

    public void onClickAnswerB(ActionEvent actionEvent) {
        updateProgress(buttonB);
        prepareQuestion();
        System.out.println("jestem tutaj przycisk b");
    }

    public void onClickAnswerC(ActionEvent actionEvent) {
        updateProgress(buttonC);
        prepareQuestion();
        System.out.println("jestem tutaj przycisk c");
    }

    public void onClickAnswerD(ActionEvent actionEvent) {
        updateProgress(buttonD);
        prepareQuestion();
        System.out.println("jestem tutaj przycisk d");
    }

    public void updateProgress(Button button) {
        if (chosenAnswer.get(button).equals(selectedQuestion.getCorrectWord())) {
            Progress.getInstance().putKnownWord(selectedQuestion.getCorrectWord(), Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) + 1);
        } else if (!chosenAnswer.get(button).equals(selectedQuestion.getCorrectWord()) && Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) > 0) {
            Progress.getInstance().putKnownWord(selectedQuestion.getCorrectWord(), Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) - 1);
        }
    }
}
