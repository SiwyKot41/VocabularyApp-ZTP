package com.example.vocabularyappztp;

import com.example.vocabularyappztp.controllers.QuizController;
import com.example.vocabularyappztp.controllers.iterator.Container;
import com.example.vocabularyappztp.controllers.iterator.Iterator;
import com.example.vocabularyappztp.model.Category;
import com.example.vocabularyappztp.model.Question;
import com.example.vocabularyappztp.model.singleton.Progress;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStateMode extends StateMode {

    private Category typeOfQuestion = Category.SINGLE_CHOICE;
    private List<Category> typesOfQuestion = new ArrayList<>(Arrays.asList(Category.SINGLE_CHOICE, Category.TRANSLATE_BY_YOURSELF));
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
        System.out.println("YY tu coś jest XD");
        int i = 0;
        Question firstQuestion = null;

        while (iterator.hasNext() && lastQuestion != null) {
                Question currentQuestion = iterator.next();
                if (i++ == 0) firstQuestion = currentQuestion;
                if (lastQuestion.getCorrectWord().equals(currentQuestion.getCorrectWord())) { //jeśli ostatnio wybrane słówko jest takie samo jak aktualne to chcemy wziąć następne
                    wasLastQuestion = true; //więc skipujemy
                    if (iterator.hasNext()) continue; //ten warunek jest dla przypadku kiedy ostatnio wzięte słówko jest naszym ostatnim z lisy
                }

                System.out.println("ostatnie pytanko " + lastQuestion.getCorrectWord().getPolishWord() + lastQuestion);
                System.out.println("aktualne pytanko " + currentQuestion.getCorrectWord().getPolishWord() + currentQuestion);
                System.out.println(wasLastQuestion);
                if (wasLastQuestion && !lastQuestion.getCorrectWord().equals(currentQuestion.getCorrectWord())) { //gdy już było ostatnio wybrane pytanie chcemy pójść
                    if (typeOfQuestion == Category.SINGLE_CHOICE) selectedQuestion = currentQuestion;               //  o słówko dalej
                    else if (typeOfQuestion == Category.TRANSLATE_BY_YOURSELF) selectedQuestion = iterator.next();
                    break;
                } else if (lastQuestion.getCorrectWord().equals(currentQuestion.getCorrectWord()) && !iterator.hasNext()) { //gdy ostatnio wzięte pytanie
                    lastQuestion = null;                                                                                            //jest ostatnim z listy
                    System.out.println("a to jest pierwsze pytanie" + firstQuestion);
                    selectedQuestion = firstQuestion;
                }
        }

        if (lastQuestion == null && iterator.hasNext()) {
            selectedQuestion = iterator.next();
            if (typeOfQuestion == Category.TRANSLATE_BY_YOURSELF) selectedQuestion = iterator.next();
        }
        System.out.println("wybrane pytanko" + selectedQuestion.getCorrectWord().getPolishWord());
        lastQuestion = selectedQuestion;
        System.out.println(lastQuestion.getCorrectWord().getEnglishWord());
        return selectedQuestion;
    }
}
