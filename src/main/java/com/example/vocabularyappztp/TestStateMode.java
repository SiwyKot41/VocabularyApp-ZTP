package com.example.vocabularyappztp;

import com.example.vocabularyappztp.controllers.iterator.Container;

import com.example.vocabularyappztp.model.Question;
import com.example.vocabularyappztp.model.singleton.Progress;
import javafx.scene.control.Label;

public class TestStateMode extends StateMode {

    private Category typeOfQuestion = Category.SINGLE_CHOICE;
    private List<Category> typesOfQuestion = new ArrayList<>(Arrays.asList(Category.SINGLE_CHOICE, Category.TRANSLATE_BY_YOURSELF));
    private Question lastQuestion;

    @Override
    public void setTitle(Label label) {
        label.setText("Test");
    }

    @Override
    public Question chooseQuestion(Container questionContainer) {
        return null;
    }
}
