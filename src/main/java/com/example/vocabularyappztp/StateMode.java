package com.example.vocabularyappztp;

import com.example.vocabularyappztp.model.Question;
import javafx.scene.control.Label;

import java.util.Iterator;

public abstract class StateMode {
    public abstract void setTitle(Label label);
    public abstract Question chooseQuestion(Iterator<Question> questionIterator, Question firstQuestions);
}
