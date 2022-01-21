package com.example.vocabularyappztp;

import com.example.vocabularyappztp.model.*;
import com.example.vocabularyappztp.model.singleton.Progress;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LearnStateMode extends StateMode {

    public Category typeOfQuestion = Category.SINGLE_CHOICE;
    public List<Category> typesOfQuestion = new ArrayList<>(Arrays.asList(Category.SINGLE_CHOICE, Category.TRANSLATE_BY_YOURSELF));

    @Override
    public void setTitle(Label label) {
        label.setText("Learn");
    }

    @Override
    public Question chooseQuestion(Iterator<Question> questionIterator, Question firstQuestion) {
        int theLeastLevelOfKnowingWord = 999999;
        Question selectedQuestion = null;

        while (questionIterator.hasNext()) {
            Question currentQuestion = questionIterator.next();
            if (theLeastLevelOfKnowingWord > Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord())) {
                theLeastLevelOfKnowingWord = Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord());

                if (typeOfQuestion == Category.SINGLE_CHOICE) selectedQuestion = currentQuestion;
                else if (typeOfQuestion == Category.TRANSLATE_BY_YOURSELF) selectedQuestion = questionIterator.next();
            }
        }

        typeOfQuestion = typesOfQuestion.get((typesOfQuestion.indexOf(typeOfQuestion) + 1) % 2);

        return selectedQuestion;
    }
}