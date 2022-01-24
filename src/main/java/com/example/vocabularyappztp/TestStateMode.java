package com.example.vocabularyappztp;

import com.example.vocabularyappztp.controllers.iterator.Container;
import com.example.vocabularyappztp.model.Question;
import javafx.scene.control.Label;

public class TestStateMode extends StateMode {

    @Override
    public void setTitle(Label label) {
        label.setText("Test");
    }

    @Override
    public Question chooseQuestion(Container questionContainer) {
        return null;
    }
}
