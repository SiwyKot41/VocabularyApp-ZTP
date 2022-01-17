package com.example.vocabularyappztp.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private Word correctAnswer;
    private List<Word> answers = new ArrayList<>();

    public Word getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Word correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Word> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Word> answers) {
        this.answers = answers;
    }
}
