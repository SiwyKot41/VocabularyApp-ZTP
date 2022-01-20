package com.example.vocabularyappztp.model.singleton;

import com.example.vocabularyappztp.model.Word;

import java.util.HashMap;

public class Progress {
    private static Progress progress;
    private HashMap<Word, Integer> knownWords = new HashMap<>();
//    private HashMap<Word, Integer> unknownWords = new HashMap<>();

    private Progress() {

    }

    public static boolean isExisting() {
        return progress != null;
    }

    public static Progress getInstance() {
        if (progress == null) progress = new Progress();
        return progress;
    }

    public HashMap<Word, Integer> getKnownWords() {
        return knownWords;
    }

//    public HashMap<Word, Integer> getUnknownWords() {
//        return unknownWords;
//    }

    public void putKnownWord(Word word, Integer isKnown) {
        knownWords.put(word, isKnown);
    }

//    public void putUnknownWord(Word word, Integer isKnown) {
//        unknownWords.put(word, isKnown);
//    }


}
