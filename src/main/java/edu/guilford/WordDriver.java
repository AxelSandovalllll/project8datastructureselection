package edu.guilford;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Hello world!
 *
 */
public class WordDriver {
    public static void main(String[] args) {
        // Read the input text file
        List<String> words = readTextFile("worldcup.txt");
        // print the words

        // Create an alphabetical list of all words
        List<String> wordList = createWordList(words);

        // Write the word list to a different file
        writeWordListToFile(wordList, "sortedWordList.txt");

        // Create a list of words with no duplicates and their occurrences
        List<WordCount> wordCounts = countWords(wordList);

        // Sort the list by the number of occurrences in descending order
        Collections.sort(wordCounts, Collections.reverseOrder());

        // Prompt the user for a word and report its occurrence count
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String userInput = scanner.nextLine().toLowerCase();
        int occurrenceCount = getOccurrenceCount(wordCounts, userInput);
        System.out.println("The word '" + userInput + "' occurs " + occurrenceCount + " time(s).");
    }

    // Read the text file and return a list of words
    private static List<String> readTextFile(String fileName) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineWords = line.replaceAll("[^a-zA-Z0-9\\s]", "").replaceAll("[0-9]", "").toLowerCase()
                        .split("\\s+");
                words.addAll(Arrays.asList(lineWords));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    // Create an alphabetical list of all words
    private static List<String> createWordList(List<String> words) {
        Set<String> wordSet = new TreeSet<>(words);
        return new ArrayList<>(wordSet);
    }

    // Write the word list to a file
    private static void writeWordListToFile(List<String> wordList, String fileName) {
        try (PrintWriter writer = new PrintWriter("src/main/resources/" + fileName)) {
            for (String word : wordList) {
                writer.println(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Create a list of words with no duplicates and their occurrences
    private static List<WordCount> countWords(List<String> wordList) {
        List<WordCount> wordCounts = new ArrayList<>();
        Map<String, Integer> countMap = new HashMap<>();

        for (String word : wordList) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            wordCounts.add(new WordCount(entry.getKey(), entry.getValue()));
        }

        return wordCounts;
    }

    // Get the occurrence count of a word from the list
    private static int getOccurrenceCount(List<WordCount> wordCounts, String word) {
        for (WordCount wc : wordCounts) {
            if (wc.getWord().equals(word)) {
                return wc.getOccurrences();
            }
        }
        return 0;
    }
}