package com.example.vocabularyappztp;

import com.example.vocabularyappztp.controllers.QuizController;
import com.example.vocabularyappztp.controllers.iterator.Container;
import com.example.vocabularyappztp.controllers.iterator.Iterator;
import com.example.vocabularyappztp.model.*;
import com.example.vocabularyappztp.model.singleton.Progress;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LearnStateMode extends StateMode {

    private Category typeOfQuestion = Category.SINGLE_CHOICE;
    private List<Category> typesOfQuestion = new ArrayList<>(Arrays.asList(Category.SINGLE_CHOICE, Category.TRANSLATE_BY_YOURSELF));

    @Override
    public void setTitle(Label label) {
        label.setText("Learn");
    }

    @Override
    public Question chooseQuestion(Container questionContainer) {
        int theLeastLevelOfKnowingWord = 99999;
        Question selectedQuestion = null;

        Iterator iterator = questionContainer.randomIterator();

        if (QuizController.lastAnswerWasCorrect) typeOfQuestion = typesOfQuestion.get((typesOfQuestion.indexOf(typeOfQuestion) + 1) % 2);

        while (iterator.hasNext()) {
            Question currentQuestion = iterator.next();
            if (theLeastLevelOfKnowingWord > Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord())) {
                theLeastLevelOfKnowingWord = Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord());

                if (typeOfQuestion == Category.SINGLE_CHOICE) selectedQuestion = currentQuestion;
                else if (typeOfQuestion == Category.TRANSLATE_BY_YOURSELF) selectedQuestion = iterator.next();
            }selectedQuestion = iterator.next();
        }

        return selectedQuestion;
    }
}