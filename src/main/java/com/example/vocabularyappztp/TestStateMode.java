package com.example.vocabularyappztp;

import com.example.vocabularyappztp.controllers.iterator.Container;
import com.example.vocabularyappztp.controllers.iterator.Iterator;
import com.example.vocabularyappztp.model.Category;
import com.example.vocabularyappztp.model.Question;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStateMode extends StateMode {

    private Category typeOfQuestion = Category.SINGLE_CHOICE;
    private List<Category> typesOfQuestion = new ArrayList<>
            (Arrays.asList(Category.SINGLE_CHOICE, Category.TRANSLATE_BY_YOURSELF));
    private Question lastQuestion;

    @Override
    public void setTitle(Label label) {
        label.setText("Test");
    }

    @Override
    public Question chooseQuestion(Container container) {
        Question selectedQuestion = null;

        Iterator iterator = container.randomIterator();

        typeOfQuestion = typesOfQuestion.get((typesOfQuestion.indexOf(typeOfQuestion) + 1) % 2);
        boolean wasLastQuestion = false;
        int i = 0;
        Question firstQuestion = null;

        while (iterator.hasNext() && lastQuestion != null) {
                Question currentQuestion = iterator.next();
                if (i++ == 0) firstQuestion = currentQuestion;
            //jeśli ostatnio wybrane słówko jest takie samo jak aktualne to chcemy wziąć następne
            if (lastQuestion.getCorrectWord().equals(currentQuestion.getCorrectWord())) {
                    wasLastQuestion = true; //więc skipujemy
                    //ten warunek jest dla przypadku kiedy ostatnio wzięte słówko jest naszym ostatnim z lisy
                    if (iterator.hasNext()) continue;
                }

                //gdy już było ostatnio wybrane pytanie chcemy pójść o słówko dalej
                if (wasLastQuestion && !lastQuestion.getCorrectWord().equals(currentQuestion.getCorrectWord())) {
                    if (typeOfQuestion == Category.SINGLE_CHOICE) selectedQuestion = currentQuestion;
                    else if (typeOfQuestion == Category.TRANSLATE_BY_YOURSELF) selectedQuestion = iterator.next();
                    break;
                    //gdy ostatnio wzięte pytanie jest ostatnim z listy
                } else if (lastQuestion.getCorrectWord().equals(currentQuestion.getCorrectWord()) &&
                        !iterator.hasNext()) {
                    lastQuestion = null;
                    selectedQuestion = firstQuestion;
                }
        }

        if (lastQuestion == null && iterator.hasNext()) {
            selectedQuestion = iterator.next();
            if (typeOfQuestion == Category.TRANSLATE_BY_YOURSELF) selectedQuestion = iterator.next();
        }
        lastQuestion = selectedQuestion;
        return selectedQuestion;
    }
}
