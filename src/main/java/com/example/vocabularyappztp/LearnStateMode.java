package com.example.vocabularyappztp;

import com.example.vocabularyappztp.model.*;
import com.example.vocabularyappztp.model.singleton.Progress;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LearnStateMode extends StateMode {

    private Category typeOfQuestion = Category.SINGLE_CHOICE;
    private List<Category> typesOfQuestion = new ArrayList<>(Arrays.asList(Category.SINGLE_CHOICE, Category.TRANSLATE_BY_YOURSELF));
    private LastWord lastWord = new LastWord();

    @Override
    public void setTitle(Label label) {
        label.setText("Learn");
    }

    @Override
    public Question chooseQuestion(Iterator<Question> questionIterator) {
        int theLeastLevelOfKnowingWord = 0;
        Question selectedQuestion = null;

        if (questionIterator.hasNext()) {
            Question currentQuestion = questionIterator.next();
            theLeastLevelOfKnowingWord = Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord());
            selectedQuestion = currentQuestion;
        }

        while (questionIterator.hasNext()) {
            Question currentQuestion = questionIterator.next();
            if (theLeastLevelOfKnowingWord > Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord())) {
                theLeastLevelOfKnowingWord = Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord());

                if (lastWord.getLastWord().equals(currentQuestion.getCorrectWord())) typeOfQuestion = lastWord.getTypeOfQuestion();
                if (typeOfQuestion == Category.SINGLE_CHOICE) selectedQuestion = currentQuestion;
                else if (typeOfQuestion == Category.TRANSLATE_BY_YOURSELF) selectedQuestion = questionIterator.next();
            }
        }

        typeOfQuestion = typesOfQuestion.get((typesOfQuestion.indexOf(typeOfQuestion) + 1) % 2);
        lastWord.setLastWord(selectedQuestion.getCorrectWord());
        lastWord.setTypeOfQuestion(typeOfQuestion);

        return selectedQuestion;
    }
}