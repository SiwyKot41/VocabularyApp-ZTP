package com.example.vocabularyappztp.controllers.builder;

import com.example.vocabularyappztp.model.Question;
import com.example.vocabularyappztp.model.QuestionSingleChoiceAnswer;
import com.example.vocabularyappztp.model.Word;

import java.util.List;

public class AnswerSingleChoiceBuilder implements AnswersBuilder {
    private Word correctWord;
    private List<Word> allWordsToChoice;

    @Override
    public void createSingleChoiceQuestion(Word word, List<Word> words) {
        correctWord = word;
        int i = words.indexOf(word);

        while (allWordsToChoice.size() < 3) {
            int index = (int) (Math.random() * (words.size()));
            while (index == i) {
                index = (int) (Math.random() * (words.size()));
            }

            allWordsToChoice.add(words.get(index));
        }

        allWordsToChoice.add(word);
    }

    @Override
    public void createBlankSpacesQuestion() {

    }

    @Override
    public void createWriteByYourselfQuestion(Word word) {

    }

    @Override
    public Question build() {

        return new QuestionSingleChoiceAnswer(correctWord, allWordsToChoice);
    }
}
