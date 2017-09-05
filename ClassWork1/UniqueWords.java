/*
 * This program prompts a user for a sentence and then outputs the number of
 * unique words in that sentence (i.e., the number of words that have no duplicate).
 */

import java.util.Scanner;
import java.util.HashSet;

public class UniqueWords {

    public static void main (String[] args) {

        Scanner scanny = new Scanner(System.in);
        String sentence = scanny.nextLine();
        String[] words = sentence.split(" ");

        HashSet<String> repeatedWords = new HashSet<String>();
        HashSet<String> uniqueWords = new HashSet<String>();

        for (int i = 0; i < words.length; i++ ) {

            if (uniqueWords.contains(words[i]) ) {
                repeatedWords.add(words[i]);
            } else {
                uniqueWords.add(words[i]); // uniqueWords will end up having all words
            }
        }
        //Remove repeated words to get unique words
        for (String word: repeatedWords) {
            uniqueWords.remove(word);
        }
        System.out.println(uniqueWords.size());

    }
}
