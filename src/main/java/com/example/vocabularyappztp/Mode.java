package com.example.vocabularyappztp;

import com.example.vocabularyappztp.model.Question;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Iterator;

public class Mode {
    private StateMode stateMode;

    public Mode(StateMode stateMode) {
        this.stateMode = stateMode;
    }

    public void setTitle(Label label) {
        stateMode.setTitle(label);
    }


    public Question chooseQuestion(Iterator<Question> questionIterator) {
        return stateMode.chooseQuestion(questionIterator);
    }
}
