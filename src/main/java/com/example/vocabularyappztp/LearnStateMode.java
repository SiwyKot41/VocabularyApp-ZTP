package com.example.vocabularyappztp;

import com.example.vocabularyappztp.controllers.QuizController;
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
    private LastQuestion lastQuestion = new LastQuestion();

    @Override
    public void setTitle(Label label) {
        label.setText("Learn");
    }

    @Override
    public Question chooseQuestion(Iterator<Question> questionIterator) {
        int theLeastLevelOfKnowingWord = 99999;
        Question selectedQuestion = null;

        if (QuizController.lastAnswerWasCorrect) typeOfQuestion = typesOfQuestion.get((typesOfQuestion.indexOf(typeOfQuestion) + 1) % 2);

        while (questionIterator.hasNext()) {
            Question currentQuestion = questionIterator.next();
            if (theLeastLevelOfKnowingWord > Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord())) {
                theLeastLevelOfKnowingWord = Progress.getInstance().getKnownWords().get(currentQuestion.getCorrectWord());

//                if (lastQuestion.getLastWord().equals(currentQuestion.getCorrectWord())) typeOfQuestion = lastQuestion.getTypeOfQuestion();
                if (typeOfQuestion == Category.SINGLE_CHOICE) selectedQuestion = currentQuestion;
                else if (typeOfQuestion == Category.TRANSLATE_BY_YOURSELF) selectedQuestion = questionIterator.next();
            }
        }

//        lastQuestion.setLastWord(selectedQuestion.getCorrectWord());
//        lastQuestion.setTypeOfQuestion(typeOfQuestion);
        System.out.println(typeOfQuestion);
        System.out.println(QuizController.lastAnswerWasCorrect);
        System.out.println(((typesOfQuestion.indexOf(typeOfQuestion) + 1) % 2));

        return selectedQuestion;
    }
}