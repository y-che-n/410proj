package com.example.oldchenny.myapplication.backend;
import java.util.*;

/**
 * Created by old chenny on 4/30/2017.
 */

public class Negatory {
    /*
     * GLOBAL VARIABLES
     */
    private static ArrayList<Integer> xCoord = new ArrayList<Integer>();
    private static ArrayList<Integer> yCoord = new ArrayList<Integer>();

    /**
     * Determines accuracy of negations between two docs.
     *
     * (when running: switch docA and docB in parameters to find extraneous negatories in docA)
     *
     * @param docA  student doc
     * @param docB  reference doc
     * @return ArrayList of ArrayLists containing x and y coordinates of missing negatory phrases.
     */
    public static ArrayList<ArrayList<Integer>> Negatory(String[][] docA, String[][] docB) {
        xCoord = new ArrayList<Integer>();
        yCoord = new ArrayList<Integer>();
        for (int i = 0; i < docB.length; i++) {
            for (int j = 0; j < docB[i].length - 1; j++) {
                String token = docB[i][j];
                switch (token) {
                    case "not":
                        if (!find(docA, "not", docB[i][j + 1]))
                            put(i, j);
                        break;
                    case "without":
                        if (!find(docA, "without", docB[i][j + 1]))
                            put(i, j);
                        break;
                    case "with":
                        if (!find(docA, "with", docB[i][j + 1]))
                            put(i, j);
                        break;
                    default:
                        continue;
                }
            }
        }

        ArrayList<ArrayList<Integer>> coords = new ArrayList<ArrayList<Integer>>(2);
        coords.add(xCoord);
        coords.add(yCoord);

        return coords;
    }

    /**
     * Update ArrayLists.
     *
     * @param i x index to be added
     * @param j y index to be added
     */
    private static void put(int i, int j) {
        xCoord.add(i);
        yCoord.add(j);
    }

    /**
     * Finds whether or not a + b exists in a sentence of docA.
     *
     * @param docA  Student doc
     * @param a Word from reference doc
     * @param b Word from reference doc
     * @return Whether or not word exists
     */
    private static boolean find(String[][] docA, String a, String b) {
        for (int i = 0; i < docA.length; i++) {
            for (int j = 0; j < docA[i].length - 1; j++) {
                if (docA[i][j].equals(a))
                    if (docA[i][j + 1].equals(b))
                        return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String a[][] = new String[1][7];
        a[0][0] = "the";
        a[0][1] = "quick";
        a[0][2] = "brown";

        a[0][3] = "fox";
        a[0][4] = "did";
        a[0][5] = "not";
        a[0][6] = "jump";

        String b[][] = new String[1][6];
        b[0][0] = "the";
        b[0][1] = "quick";
        b[0][2] = "brown";

        b[0][3] = "dog";
        b[0][4] = "did";
        b[0][5] = "jump";

        System.out.println(Negatory(a, b));
    }
}
