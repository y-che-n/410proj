package com.example.oldchenny.myapplication.backend;
import java.util.*;

/**
 * Created by old chenny on 4/29/2017.
 */

public class WordForWord {
    // determine word for word (3-word chains)

    /**
     * Compares two docs (array of sentences, which are arrays of words).
     * Word-for-word-ness: identical 3+ word chains.
     *
     * @param docA  student doc
     * @param docB  reference doc
     * @return ArrayList of String phrases that were word for word in both docs.
     */
    public static ArrayList<ArrayList<Integer>> wordForWord(String[][] docA, String[][] docB) {
        ArrayList<ArrayList<Integer>> phrases = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        ArrayList<Integer> length = new ArrayList<Integer>();
        for (int i = 0; i < docB.length; i++) {
            for (int j = 0; j < docA.length; j++) {
                ArrayList<ArrayList<Integer>> s = compare(docB[i], docA[j], i);
                x.addAll(s.get(0));
                y.addAll(s.get(1));
                length.addAll(s.get(2));
            }
        }
        phrases.add(x);
        phrases.add(y);
        phrases.add(length);
        return phrases;
    }

    /**
     * Finds word-for-word-ness between two strings.
     *
     * @param stringA  String from student doc
     * @param stringB  String from reference doc
     * @return ArrayList of word-for-word phrases between stringA and stringB.
     */
    private static ArrayList<ArrayList<Integer>> compare(String[] stringA, String[] stringB, int sentenceNo) {
        ArrayList<Integer> phrases = new ArrayList<Integer>();
        ArrayList<Integer> xCoord = new ArrayList<Integer>();
        ArrayList<Integer> yCoord = new ArrayList<Integer>();

        out: for (int i = 0; i < stringB.length; i++) {
            for (int j = 0; j < stringA.length; j++) {
                String abc = stringB[i];

                if (stringB[i].equalsIgnoreCase(stringA[j])) {
                    int x = sentenceNo;
                    int y = i;
                    String s = stringB[i];
                    i++; j++;
                    int phraseLength = 1;
                    while (j < stringA.length && i < stringB.length) {
                        if (stringB[i].equalsIgnoreCase(stringA[j])) {
                            s += " " + stringB[i];
                            i++; j++;
                            phraseLength++;
                        } else break;
                    }

                    if (phraseLength > 2) {
                        xCoord.add(x);
                        yCoord.add(y);
                        phrases.add(phraseLength);
                    }

                    if(i >= stringB.length)
                        break out;
                }
            }
        }
        ArrayList<ArrayList<Integer>> coords = new ArrayList<ArrayList<Integer>>();
        coords.add(xCoord);
        coords.add(yCoord);
        coords.add(phrases);
        return coords;
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

        System.out.println(wordForWord(a, b));
    }

}
