package com.example.vocabularyappztp.model;

public abstract class Question {
    private Word correctWord;
    public Question(Word correctWord) {
        this.correctWord = correctWord;
    }

    public abstract void getPolishWord();
    public abstract void getEnglishWord();
    public Word getCorrectWord() {
        return correctWord;
    }
}
