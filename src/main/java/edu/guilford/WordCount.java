package edu.guilford;

// WordCount class to store word and occurrence count
public class WordCount implements Comparable<WordCount> {
    private final String word;
    private final int occurrences;

    public WordCount(String word, int occurrences) {
        this.word = word;
        this.occurrences = occurrences;
    }

    public String getWord() {
        return word;
    }

    public int getOccurrences() {
        return occurrences;
    }

    @Override
    public int compareTo(WordCount other) {
        // Sort in descending order based on occurrence count
        return Integer.compare(other.occurrences, this.occurrences);
    }
}
