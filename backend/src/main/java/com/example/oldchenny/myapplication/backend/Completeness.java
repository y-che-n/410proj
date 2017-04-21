package com.example.oldchenny.myapplication.backend;
import java.util.*;

/**
 * Created by shivalipatel on 4/16/17.
 */

public class Completeness {

    /* GLOBAL VARIABLES */
    public static ArrayList<Integer> indices = new ArrayList<Integer>();

    //score completeness and return missing information

    /**
     * Compares two docs (array of sentences, which are arrays of words).
     *
     * @param docA  student doc
     * @param docB  reference doc
     * @return percentage of topics mentioned in docB but not docA
     */
    public double compare(String[][] docA, String[][] docB) {
        HashSet<String> map = new HashSet<String>();

        // convert docB to hash map
        for (String[] s : docB)
            for (String s1 : s)
                if (!map.contains(s1))
                    map.add(s1);

        int totalTopics = map.size();
        int notThere = 0;

        for (String[] s: docA)
            for (String s1 : s)
                if (!map.contains(s1)) {
                    int index = getFirstOccurrence(docB, s1);
                    indices.add(index);

                    /* OR:
                      ArrayList<Integer> occurrences =
                              getAllOccurrences(docB, s1);
                      indices.addAll(occurrences);
                    */

                    notThere++;
                }

        double percentage = ((double) notThere / (double) totalTopics) * 100.0;
        return percentage;
    }

    /**
     * Takes two docs (array of sentences, which are arrays of words).
     * Returns an ArrayList of topics not mentioned.
     *
     * @param docB  reference doc
     * @param s  word to find
     * @return  index of sentence of first occurrence of word, -1 if not found
     */
    private int getFirstOccurrence(String[][] docB, String s) {
        int index = -1;

        int numSentences = docB.length;

        for (int i = 0; i < numSentences; i++) {
            int numWords = docB[i].length;
            for (int j = 0; j < numWords; j++) {
                if (docB[i][j].equals(s))
                    return i;
            }
        }
        return -1;
    }

    /**
     * Takes two docs (array of sentences, which are arrays of words).
     * Returns an ArrayList of topics not mentioned.
     *
     * @param docB  reference doc
     * @param s  word to find
     * @return  ArrayList of indices where word was found, null if not found
     */
    private ArrayList<Integer> getAllOccurrences(String[][] docB, String s) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        indices = null;

        int numSentences = docB.length;

        for (int i = 0; i < numSentences; i++) {
            int numWords = docB[i].length;
            for (int j = 0; j < numWords; j++) {
                if (docB[i][j].equals(s))
                    indices.add(i);
            }
        }
        return indices;
    }
}
