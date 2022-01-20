package com.example.vocabularyappztp.controllers.builder;

import com.example.vocabularyappztp.model.Question;
import com.example.vocabularyappztp.model.QuestionWriteByYourself;
import com.example.vocabularyappztp.model.Word;

import java.util.List;

public class AnswerWriteByYourselfBuilder implements AnswersBuilder {
    private Word correctWord;

    @Override
    public void createSingleChoiceQuestion(Word word, List<Word> words) {

    }

    @Override
    public void createBlankSpacesQuestion() {

    }

    @Override
    public void createWriteByYourselfQuestion(Word word) {
        this.correctWord = word;
    }

    @Override
    public Question build() {
        return new QuestionWriteByYourself(correctWord);
    }
}
