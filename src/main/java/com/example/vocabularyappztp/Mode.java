package com.example.vocabularyappztp;

import javafx.scene.control.Label;

public class Mode {
    private StateMode stateMode;

    public Mode(StateMode stateMode) {
        this.stateMode = stateMode;
    }

    public void setTitle(Label label) {
        stateMode.setTitle(label);
    }
}
