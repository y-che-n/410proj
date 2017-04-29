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
    public ArrayList<String> wordForWord(String[][] docA, String[][] docB) {
        ArrayList<String> phrases = new ArrayList<String>();

        for (int i = 0; i < docB.length; i++) {
            for (int j = 0; j < docA.length; j++) {
                ArrayList<String> s = compare(docB[i], docA[j]);
                phrases.addAll(s);
            }
        }

        return phrases;
    }

    /**
     * Finds word-for-word-ness between two strings.
     *
     * @param stringA  String from student doc
     * @param stringB  String from reference doc
     * @return ArrayList of word-for-word phrases between stringA and stringB.
     */
    private ArrayList<String> compare(String[] stringA, String[] stringB) {
        ArrayList<String> phrases = new ArrayList<String>();

        for (int i = 0; i < stringB.length; i++) {
            for (int j = 0; j < stringA.length; j++) {
                if (stringB[i].equalsIgnoreCase(stringA[j])) {
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
                    if (phraseLength > 2) phrases.add(s);
                }
            }
        }

        return phrases;
    }

}
