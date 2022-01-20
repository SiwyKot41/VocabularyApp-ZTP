package com.example.vocabularyappztp.controllers.builder;

import com.example.vocabularyappztp.model.Question;
import com.example.vocabularyappztp.model.Word;

import java.util.List;

public class AnswerBlankSpacesBuilder implements AnswersBuilder {

    @Override
    public void createSingleChoiceQuestion(Word word, List<Word> words) {

    }

    @Override
    public void createBlankSpacesQuestion() {

    }

    @Override
    public void createWriteByYourselfQuestion(Word word) {

    }

    @Override
    public Question build() {
        return null;
    }
}
