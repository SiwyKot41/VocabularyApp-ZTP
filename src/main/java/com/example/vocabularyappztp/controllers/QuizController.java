package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.Mode;
import com.example.vocabularyappztp.StateLanguage;
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
    public ArrayList<Button> buttons = new ArrayList<>();
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
    private StateLanguage language;

    private ArrayList<Word> words = new ArrayList<>();
    private ArrayList<Question> questions = new ArrayList<>();
    private Container container;
    private Question selectedQuestion;
    private HashMap<Button, String> chosenAnswer = new HashMap<>();
    public static boolean lastAnswerWasCorrect = true;

    @FXML
    private Label label;
    @FXML
    private Label languageLabel;

    public void initialize(Stage stage, Mode mode, StateLanguage language) throws Exception {
        this.stage = stage;
        this.mode = mode;
        this.language = language;
        mode.setTitle(label);
        language.setTitle(languageLabel);

        words = Utils.readWords();
        questions = createQuestions();
        System.out.println(SettingsController.difficulty);
        System.out.println(questions.get(0).getCorrectWord().getLevel().toString());
        System.out.println(questions.size());
        System.out.println(questions.stream().filter(question -> question.getCorrectWord().getLevel().toString()
                .equals(SettingsController.difficulty)).count());
        questions.stream().filter(question -> question.getCorrectWord().getLevel().toString().
                equals(SettingsController.difficulty));
        if (questions.stream().filter(question -> question.getCorrectWord().getLevel().toString()
                .equals(SettingsController.difficulty)).count() != 0) {
            quiz = new Quiz(questions);

            for (Word word : words) {
                Progress.getInstance().putKnownWord(word, 0);
            }
            container = new Container(questions);
            prepareQuestion();

        } else {
            questionText.setText("Brak pyta?? w tym poziomie trudno??ci");
            buttonA.setVisible(false);
            buttonB.setVisible(false);
            buttonC.setVisible(false);
            buttonD.setVisible(false);
            choiceBonusButton.setVisible(false);
            writeBonusButton.setVisible(false);
            buttonNext.setVisible(false);
            typedWord.setVisible(false);
        }

    }


    private ArrayList<Question> createQuestions() {
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

    private void prepareQuestion() {
        pointObject = new PointsImpl();

        selectedQuestion = mode.chooseQuestion(container);
        writeBonusText.setText(writeBonusLetters);
        writeBonusText.setDisable(true);

        if (selectedQuestion instanceof QuestionSingleChoiceAnswer) {
            if (questionCount<10 && label.getText() == "Test" || label.getText() == "Learn" ) prepareSingleChoiceQuestion();
            else printResult();
        } else if (selectedQuestion instanceof QuestionWriteByYourself) {
            if (questionCount<10 && label.getText() == "Test" || label.getText() == "Learn" ) prepareWriteByYourselfQuestion();
            else printResult();
        }
    }

    private void prepareWriteByYourselfQuestion() {

        writeBonusButton.setVisible(false);
        writeBonusButton.setText("Bonus");

        if (label.getText() == "Learn") {
            pointObject = new WritePointDecorator(pointObject);
            pointsText.setText("Points: " + points);

            writeBonusButton.setVisible(true);
            writeBonusButton.setDisable(true);

            if (points >= writeBonusPrice) {
                writeBonusButton.setDisable(false);
            }
        } else {
            pointObject = new TestPointDecorator(pointObject);
        }

        questionText.setText("Wpisz t??umaczenie s??owa " + selectedQuestion.getCorrectWord().getEnglishWord());

        buttonA.setVisible(false);
        buttonB.setVisible(false);
        buttonC.setVisible(false);
        buttonD.setVisible(false);
        choiceBonusButton.setVisible(false);

        typedWord.setText("");
        typedWord.setVisible(true);
        buttonNext.setVisible(true);
    }

    private void prepareSingleChoiceQuestion() {

        buttons.add(buttonA);
        buttons.add(buttonB);
        buttons.add(buttonC);
        buttons.add(buttonD);

        choiceBonusButton.setVisible(false);
        choiceBonusButton.setText("Bonus");

        if (label.getText() == "Learn") {
            pointObject = new SingleChoicePointDecorator(pointObject);
            pointsText.setText("Points: " + points);

            choiceBonusButton.setVisible(true);
            choiceBonusButton.setDisable(true);

            if (points >= choiceBonusPrice) {
                choiceBonusButton.setDisable(false);
            }
        } else {
            pointObject = new TestPointDecorator(pointObject);
        }

        questionText.setText("Wybierz prawid??owe t??umaczenie dla " +selectedQuestion.getCorrectWord().getEnglishWord());
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

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getText() == selectedQuestion.getCorrectWord().getPolishWord()) {
                buttons.remove(i);
            }
        }

        writeBonusButton.setVisible(false);
        typedWord.setVisible(false);
        buttonNext.setVisible(false);
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
        buttons.clear();
        writeBonusLetters = "";
        writeBonusText.setText(writeBonusLetters);
        writeBonusText.setDisable(false);
        chosenAnswer.put(buttonNext, typedWord.getText());
        updateProgress(buttonNext);
        prepareQuestion();
    }

    public void onClickWriteBonus(ActionEvent actionEvent) {
        if (writeBonusLetters.length() != selectedQuestion.getCorrectWord().getPolishWord().length()) {
            writeBonusLetters = selectedQuestion.getCorrectWord().
                    getPolishWord().substring(0, writeBonusLetters.length() + 1);
            points -= writeBonusPrice;
            pointsText.setText("Points: " + points);
            writeBonusText.setText(writeBonusLetters);
            writeBonusText.setDisable(false);
            if (points < writeBonusPrice) {
                writeBonusButton.setDisable(true);
            }
        }

        System.out.println(writeBonusLetters);
    }

    public void onClickChoiceBonus(ActionEvent actionEvent) {
        System.out.println(buttons.size());
        if (buttons.size() > 1) {
            int index = (int) (Math.random() * buttons.size());
            System.out.println(index);
            buttons.get(index).setVisible(false);
            buttons.remove(index);
            points -= choiceBonusPrice;
            pointsText.setText("Points: " + points);
        }
        if (buttons.size() == 1 || points <choiceBonusPrice) {
            choiceBonusButton.setDisable(true);
        }
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


    public void printResult(){
        questionText.setText("Test zako??czony");
        pointsText.setText("Points: " + points);
        buttonA.setVisible(false);
        buttonB.setVisible(false);
        buttonC.setVisible(false);
        buttonD.setVisible(false);
        choiceBonusButton.setVisible(false);
        writeBonusButton.setVisible(false);
        buttonNext.setVisible(false);
        typedWord.setVisible(false);
    }
    public void updateProgress(Button button) {

        if (chosenAnswer.get(button).equals(selectedQuestion.getCorrectWord().getPolishWord())) {
            Progress.getInstance().putKnownWord(selectedQuestion.getCorrectWord(),
                    Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) + 1);
            QuizController.lastAnswerWasCorrect = true;
            points += pointObject.getPoints();
        } else if (!chosenAnswer.get(button).equals(selectedQuestion.getCorrectWord().getPolishWord()) &&
                    Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) > 0) {
            Progress.getInstance().putKnownWord(selectedQuestion.getCorrectWord(),
                    Progress.getInstance().getKnownWords().get(selectedQuestion.getCorrectWord()) - 1);
            QuizController.lastAnswerWasCorrect = false;
        } else {
            QuizController.lastAnswerWasCorrect = false;
        }
        questionCount += 1;

    }
}