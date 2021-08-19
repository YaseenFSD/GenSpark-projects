package com.company;

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

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
    private int stage = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer = getRandomWord();
        char[] guessedChars = getFillerArray(answer.length());
//        System.out.println(answer);
        char input = sc.next().charAt(0);

        if (hasLetter(input, answer)){
            updateAnswer(guessedChars, input, answer);
        } else {
//            updateMistakes()
        }

        printLetters(guessedChars);


    }
 static void updateAnswer (char[] guessedChars, char correctChar, String answer){
     for (int i = 0; i < answer.length(); i++) {
         char currentLetter = answer.charAt(i);
         if (correctChar == currentLetter){
             guessedChars[i] = currentLetter;
         }
     }
 }
    static boolean hasLetter(char letter, String answer) {
        boolean hasLetter = answer.contains(Character.toString(letter));
        if (hasLetter){
            return true;
        }
        return false;
    }

//    static void updateChars(char[] guessedChars){
////        test[0] = 'a';
//        ;
//    }

    static String getRandomWord() {
        int randInt = new Random().nextInt(words.length);
        return words[randInt];
    }

    static char[] getFillerArray(int length) {
        char[] fillers = new char[length];
        Arrays.fill(fillers, '_');
        return fillers;
    }

    static void printLetters(char[] letters) {
        for (char letter : letters) {
            System.out.print(letter);
        }
        System.out.println("");
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
