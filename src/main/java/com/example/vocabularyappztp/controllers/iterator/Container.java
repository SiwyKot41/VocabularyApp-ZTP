package com.example.vocabularyappztp.controllers.iterator;

import com.example.vocabularyappztp.model.Question;

import java.util.Collections;
import java.util.List;
public class Container {

    private final List<Question> questions;

    public Container(List<Question> questions) {
        this.questions = questions;
        Collections.shuffle(this.questions);
    }

    public Iterator randomIterator() {
        return new Iterator() {
            private int pointer = 0;
            @Override
            public boolean hasNext() {
                return pointer < questions.size();
            }

            @Override
            public Question next() {
                return questions.get(pointer ++);
            }
        };
    }

}
