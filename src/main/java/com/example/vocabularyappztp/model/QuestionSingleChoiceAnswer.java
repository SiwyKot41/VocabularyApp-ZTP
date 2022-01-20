package com.example.vocabularyappztp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionSingleChoiceAnswer extends Question {
    public Word correctWord;
    public Set<Word> allWordsToChoice = new HashSet<>();

    public QuestionSingleChoiceAnswer(Word correctWord, Set<Word> allWordsToChoice) {
        this.correctWord = correctWord;
        this.allWordsToChoice = allWordsToChoice;
    }

    @Override
    public void getPolishWord() {
        for (Word word : allWordsToChoice) {
            System.out.println("Single choice polish" + word.getPolishWord());
        }
    }

    @Override
    public void getEnglishWord() {
        for (Word word : allWordsToChoice) {
            System.out.println("Single choice english" + word.getEnglishWord());
        }

    }
}
