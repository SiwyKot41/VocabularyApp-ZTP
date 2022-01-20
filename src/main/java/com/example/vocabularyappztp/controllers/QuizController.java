package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.Mode;
import com.example.vocabularyappztp.model.singleton.Progress;
import com.example.vocabularyappztp.controllers.builder.AnswerSingleChoiceBuilder;
import com.example.vocabularyappztp.controllers.builder.AnswerWriteByYourselfBuilder;
import com.example.vocabularyappztp.controllers.builder.AnswersBuilder;
import com.example.vocabularyappztp.model.Question;
import com.example.vocabularyappztp.model.Quiz;
import com.example.vocabularyappztp.model.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public class QuizController {

    private Quiz quiz;
    private Stage stage;
    private Mode mode;
    private ArrayList<Word> words = new ArrayList<Word>();
    private ArrayList<Question> questions = new ArrayList<Question>();
    private Iterator<Question> questionIterator;
    private Question selectedQuestion;

    @FXML
    private Label label;

    public void initialize(Stage stage, Mode mode) throws Exception {
        this.stage = stage;
        this.mode = mode;
//        mode.setTitle(label);

        words = Utils.readWords();
        questions = createQuestions();
        quiz = new Quiz(questions);

        if (!Progress.isExisting()) {
            for (Word word : words) {
                Progress.getInstance().putKnownWord(word, 0);
            }
        }

        questionIterator = questions.iterator();
        chooseQuestion();
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

    public void chooseQuestion() {
        int theLeastLevelOfKnowingWord = 0;

        if (questionIterator.hasNext()) {
            Question currentQuestion = questionIterator.next();
            theLeastLevelOfKnowingWord = Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord());
            selectedQuestion = currentQuestion;
        }

        while (questionIterator.hasNext()) {
            Question currentQuestion = questionIterator.next();
            if (theLeastLevelOfKnowingWord > Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord())) {
                theLeastLevelOfKnowingWord = Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord());
                selectedQuestion = currentQuestion;
            }
        }
    }
}
