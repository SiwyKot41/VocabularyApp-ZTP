package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.model.Word;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Utils {
    public static ArrayList<Word> readWords() throws Exception {
        File file = new File("src/main/java/words.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<Word> words = new ArrayList<>();

        String currentLine;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            String[] word = currentLine.split(" ");
            words.add(new Word(word[0], word[1]));
        }

        Collections.shuffle(words);

        return words;
    }


}