package com.example.vocabularyappztp.model;

public class Word {
    private String englishWord;
    private String polishWord;
    private Level level;
    private String learnState;

    public Word(String englishWord, String polishWord, String learnState) throws Exception {
        this.englishWord = englishWord;
        this.polishWord = polishWord;
        this.learnState = learnState;
        selectLevel();
    }

    private void selectLevel() throws Exception {
        if (englishWord.length() <= 4 && englishWord.length() > 0) level = Level.EASY;
        else if (englishWord.length() > 4 && englishWord.length() < 8) level = Level.MEDIUM;
        else if (englishWord.length() >= 8) level = Level.HARD;
        else throw new Exception("Incorrect word");
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getPolishWord() {
        return polishWord;
    }

    public void setPolishWord(String polishWord) {
        this.polishWord = polishWord;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getLearnState() { return learnState;}

    public void setLearnState(String learnState) { this.learnState = learnState;}

    public String toString(){
        return (this.englishWord + " " + this.polishWord + " " + this.learnState);
    }
}
