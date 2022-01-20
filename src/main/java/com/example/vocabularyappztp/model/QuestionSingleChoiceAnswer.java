package com.example.vocabularyappztp.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionSingleChoiceAnswer extends Question {
    public Word correctWord;
    public List<Word> allWordsToChoice = new ArrayList<>();

    public QuestionSingleChoiceAnswer(Word correctWord, List<Word> allWordsToChoice) {
        this.correctWord = correctWord;
        this.allWordsToChoice = allWordsToChoice;
    }
}
