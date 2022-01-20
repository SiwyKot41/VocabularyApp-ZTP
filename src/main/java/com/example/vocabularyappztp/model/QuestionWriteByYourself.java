package com.example.vocabularyappztp.model;

public class QuestionWriteByYourself extends Question {
    private Word correctWord;

    public QuestionWriteByYourself(Word correctWord) {
        super();
        this.correctWord = correctWord;
    }

    @Override
    public void getPolishWord() {
        System.out.println("Wpisz sam po polsku" + correctWord.getPolishWord());
    }

    @Override
    public void getEnglishWord() {
        System.out.println("Wpisz sam po angielsku" + correctWord.getEnglishWord());
    }
}
