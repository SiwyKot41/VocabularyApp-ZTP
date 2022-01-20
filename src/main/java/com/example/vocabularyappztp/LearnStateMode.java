package com.example.vocabularyappztp;

import com.example.vocabularyappztp.model.Question;
import com.example.vocabularyappztp.model.singleton.Progress;
import javafx.scene.control.Label;

import java.util.Iterator;

public class LearnStateMode extends StateMode {

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
                selectedQuestion = currentQuestion;
            }
        }
        
        return selectedQuestion;
    }
}