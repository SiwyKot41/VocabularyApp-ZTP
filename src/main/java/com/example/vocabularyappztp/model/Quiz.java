package com.example.vocabularyappztp.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private List<Question> questionList = new ArrayList<>();

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
