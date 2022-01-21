package com.example.vocabularyappztp.model;

public class LastWord {
    private Word lastWord;
    private Category typeOfQuestion;

    public Word getLastWord() {
        return lastWord;
    }

    public void setLastWord(Word lastWord) {
        this.lastWord = lastWord;
    }

    public Category getTypeOfQuestion() {
        return typeOfQuestion;
    }

    public void setTypeOfQuestion(Category typeOfQuestion) {
        this.typeOfQuestion = typeOfQuestion;
    }
}
