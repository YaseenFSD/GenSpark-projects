package com.company;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Random;

/* ascii art credit: https://ascii.co.uk/art/hangman
      _______
     |/      |
     |      (_)
     |      \|/
     |       |
     |      / \
     |
 ____|___
         */

public class Main {
    private static String[] words = {"banana", "apple", "monkey", "orange", "cat", "dog", "one", "monster"};

    public static void main(String[] args) {
        System.out.println(getRandomWord());

    }

    static String getRandomWord() {
        int randInt = new Random().nextInt(words.length);
        return words[randInt];
    }

    static void printHangman(int stage) {
//        Stage 0 is no mistakes have been made yet. Stage 5 is the last stage

//        Concise Solution
        /*
        System.out.println("      _______");
        System.out.println("     |/      " + (stage > 0 ? "|" : ""));
        System.out.println("     |      " + (stage > 1 ? "(_)" : ""));
        System.out.println("     |      " + (stage > 2 ? "\\|/" : ""));
        System.out.println("     |       " + (stage > 3 ? "|" : ""));
        System.out.println("     |      " + (stage > 4 ? "/ \\" : ""));
        System.out.println("     |");
        System.out.println(" ____|___");
        */

//        Performance Solution
//        This is faster because of less condition checks
        System.out.println("      _______");

        switch (stage) {
            case 1:
                System.out.print(
                        "     |/      |\n" +
                                "     |      \n" +
                                "     |      \n" +
                                "     |       \n" +
                                "     |      \n");


                break;
            case 2:
                System.out.print("     |/      |\n"
                        + "     |      (_)\n"
                        + "     |      \n"
                        + "     |       \n"
                        + "     |      \n");

                break;
            case 3:
                System.out.println("     |/      |\n"
                        + "     |      (_)\n"
                        + "     |      \\|/\n"
                        + "     |       \n"
                        + "     |      \n");


                break;
            case 4:
                System.out.print("     |/      |\n"
                        + "     |      (_)\n"
                        + "     |      \\|/\n"
                        + "     |       |\n"
                        + "     |      \n");

                break;
            case 5:
                System.out.print("     |/      |\n"
                        + "     |      (_)\n"
                        + "     |      \\|/\n"
                        + "     |       |\n"
                        + "     |      / \\\n");

                break;

            default:
                System.out.print("     |/      \n"
                        + "     |      \n"
                        + "     |      \n"
                        + "     |       \n"
                        + "     |      \n");
                break;
        }
        System.out.println("     |");
        System.out.println(" ____|___");

    }

}
