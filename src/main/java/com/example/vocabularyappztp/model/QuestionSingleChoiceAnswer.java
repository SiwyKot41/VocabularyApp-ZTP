package com.example.vocabularyappztp.model;

import java.util.HashSet;
import java.util.Set;

    public class QuestionSingleChoiceAnswer extends Question {
    public Set<Word> allWordsToChoice;

    public QuestionSingleChoiceAnswer(Word correctWord, Set<Word> allWordsToChoice) {
        super(correctWord);
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

    public Set<Word> getAllWordsToChoice() {
        return allWordsToChoice;
    }
}
