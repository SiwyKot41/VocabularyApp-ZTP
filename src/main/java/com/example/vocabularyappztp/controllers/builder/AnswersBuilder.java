package com.example.vocabularyappztp.controllers.builder;

import com.example.vocabularyappztp.model.Question;
import com.example.vocabularyappztp.model.Word;

import java.util.List;

public interface AnswersBuilder {
    public void createSingleChoiceQuestion(Word word, List<Word> words);
    public void createBlankSpacesQuestion();
    public void createWriteByYourselfQuestion(Word word);
    public Question build();
}
