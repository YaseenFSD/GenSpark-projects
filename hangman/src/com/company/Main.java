package com.company;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
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


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer = getRandomWord();
        int stage = 0;
        char[] currentGuess = getFillerArray(answer.length());
        ArrayList<Character> historyLetters = new ArrayList<>();
        boolean gameDone = false;
        boolean roundDone = false;

        while (!gameDone) {
            printHangman(stage);
            printLetters(currentGuess, historyLetters);
            char input = sc.next().charAt(0);

            if (hasLetter(input, answer)) {
                updateGuess(currentGuess, input, answer);
            } else {
                if (checkLetterMistake(historyLetters, input)){
                    stage++;
                };
            }
            char[] answerCharArray = answer.toCharArray();
            boolean wonGame = Arrays.equals(answerCharArray, currentGuess);
            if (wonGame){
                System.out.println("Congratulations, you won!");
                roundDone = true;
            }


            if (stage > 4 || roundDone) {
                if (!wonGame){
                    printHangman(stage);
                    System.out.println("Oh no hangman is gone for good. 😢");
                }
                if (wantsRestart(sc)) {
                    gameDone = false;
                    roundDone = false;
                    answer = getRandomWord();
                    stage = 0;
                    currentGuess = getFillerArray(answer.length());
                    historyLetters.clear();
                }else {
//
                    gameDone = true;
                }
            }

        }


    }

    static boolean checkLetterMistake(ArrayList letters, char input) {
        if (letters.contains(input)) {
            System.out.printf("You have already tried ' %c '\n", input);
            return false;
        };

        letters.add(input);
        return true;
    }

    static void updateGuess(char[] currentGuess, char correctChar, String answer) {
        for (int i = 0; i < answer.length(); i++) {
            char currentLetter = answer.charAt(i);
            if (correctChar == currentLetter) {
                currentGuess[i] = currentLetter;
            }
        }
    }

    static boolean hasLetter(char letter, String answer) {
        boolean hasLetter = answer.contains(Character.toString(letter));
        if (hasLetter) {
            return true;
        }
        return false;
    }

//    static void updateChars(char[] currentGuess){
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

    static void printLetters(char[] guessedLetters, ArrayList historyLetters) {
        System.out.println("Guess a Letter");
        for (char letter : guessedLetters) {
            System.out.print(letter);
        }
        System.out.println("");

        if (!historyLetters.isEmpty()) System.out.println("Missed Letters");
        historyLetters.forEach(letter -> {
                    System.out.print(letter + " ");
                }
        );
        System.out.println("");

    }

    static boolean wantsRestart(Scanner sc) {
        System.out.print("Do you want to play again? (y or n)");
        char input = sc.next().charAt(0);
        if (input == 'y') {
            return true;
        }
        return false;
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
                System.out.print("     |/      |\n"
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
