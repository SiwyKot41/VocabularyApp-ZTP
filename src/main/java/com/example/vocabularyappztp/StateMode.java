package com.example.vocabularyappztp;

import com.example.vocabularyappztp.controllers.iterator.Container;
import com.example.vocabularyappztp.model.Question;
import javafx.scene.control.Label;

public abstract class StateMode {
    public abstract void setTitle(Label label);
    public abstract Question chooseQuestion(Container container);
}
