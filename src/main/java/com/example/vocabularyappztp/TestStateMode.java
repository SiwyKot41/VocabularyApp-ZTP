package com.example.vocabularyappztp;

import com.example.vocabularyappztp.model.Question;
import javafx.scene.control.Label;

import java.util.Iterator;

public class TestStateMode extends StateMode {

    @Override
    public void setTitle(Label label) {
        label.setText("Test");
    }

    @Override
    public Question chooseQuestion(Iterator<Question> questionIterator, Question questions) {
        return null;
    }
}
