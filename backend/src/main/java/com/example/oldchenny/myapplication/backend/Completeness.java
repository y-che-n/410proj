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
     * @param docA  reference doc
     * @param docB  student doc
     * @return percentage of topics mentioned in docB that are also in docA
     */
    public static double compare(String[][] docA, String[][] docB) {
        HashSet<String> mapB = new HashSet<String>();
        HashSet<String> mapA = new HashSet<String>();
        indices = new ArrayList<Integer>();
        // convert docB to hash map
        for (String[] s : docB)
            for (String s1 : s)
                if (!mapB.contains(s1))
                    mapB.add(s1);

        for (String[] s : docA)
            for (String s1 : s)
                if (!mapA.contains(s1))
                    mapA.add(s1);

        int totalTopics = mapA.size();
        int notThere = 0;

        for (String[] s: docA)
            for (String s1 : s)
                if (!mapB.contains(s1)) {
                    int index = getFirstOccurrence(docB, s1);
                    indices.add(index);

                    /* OR:
                      ArrayList<Integer> occurrences =
                              getAllOccurrences(docB, s1);
                      indices.addAll(occurrences);
                    */

                    notThere++;
                }

        double percentage = 100-((double) notThere / (double) totalTopics) * 100.0;
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
    private static int getFirstOccurrence(String[][] docB, String s) {
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
    private static ArrayList<Integer> getAllOccurrences(String[][] docB, String s) {
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

    public static void main(String[] args) {
        String a[][] = new String[1][5];
        a[0][0] = "the";
        a[0][1] = "quick";
        a[0][2] = "brown";

        a[0][3] = "fox";
        a[0][4] = "jumped";

        String b[][] = new String[1][5];
        b[0][0] = "the";
        b[0][1] = "quick";
        b[0][2] = "brown";

        b[0][3] = "dog";
        b[0][4] = "jumped";

        System.out.println(compare(a, b));
    }
}
