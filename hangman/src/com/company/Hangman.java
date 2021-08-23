package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Hangman {
    private static final String[] words = {"banana", "apple", "monkey", "orange", "cat", "dog", "one", "monster"};

    private int stage = 0;
    private String answer;
    ArrayList<Character> historyLetters = new ArrayList<>();
    ArrayList<Character> currentGuess = new ArrayList<>();
    boolean gameDone = false;
    boolean roundDone = false;

    public void start(Scanner sc) {
        answer = getRandomWord();
//        char[] currentGuess = initializeArray();
        initializeCurrentGuess();
        while (!gameDone) {
            printHangman();
            printLetters();
            char input = sc.next().charAt(0);
            if (hasLetter(input)) {
                updateGuess(input);
            } else {
                if (checkLetterMistake(input)) {
                    stage++;
                }
            }

//            char[] answerCharArray = answer.toCharArray();

//            boolean wonGame = Arrays.equals(answerCharArray, currentGuess);
            String guessStringRep = currentGuess.stream().map(x -> x.toString()).reduce("", (acc, val) -> acc + val);
            boolean wonGame = guessStringRep.equals(answer);
            if (wonGame) {
                System.out.println("Congratulations, you won!");
                roundDone = true;
            }

            if (stage > 4 || roundDone) {
                if (!wonGame) {
                    printHangman();
                    System.out.println("Oh no hangman is gone for good. 😢");
                }
                if (wantsRestart(sc)) {
                    restartGame();
                    initializeCurrentGuess();
                } else {
                    gameDone = true;
                }
            }
        }
    }

    void restartGame() {
        gameDone = false;
        roundDone = false;
        answer = getRandomWord();
        stage = 0;
        historyLetters.clear();
    }

    boolean wantsRestart(Scanner sc) {
        System.out.print("Do you want to play again? (y or n)");
        char input = sc.next().charAt(0);
        if (input == 'y') {
            return true;
        }
        return false;
    }

    boolean checkLetterMistake(char input) {
        if (historyLetters.contains(input)) {
            System.out.printf("You have already tried ' %c '\n", input);
            return false;
        }
        ;

        historyLetters.add(input);
        return true;
    }

    void updateGuess(char correctChar) {
        for (int i = 0; i < answer.length(); i++) {
            char currentLetter = answer.charAt(i);
            if (correctChar == currentLetter) {
                currentGuess.set(i, currentLetter);
            }
        }
    }

    boolean hasLetter(char letter) {
        boolean hasLetter = answer.contains(Character.toString(letter));
        if (hasLetter) {
            return true;
        }
        return false;
    }

    void printLetters() {
        System.out.println("Guess a Letter");
//        for (char letter : guessedLetters) {
//            System.out.print(letter);
//        }
        currentGuess.forEach(letter -> System.out.print(letter));
        System.out.println("");

        if (!historyLetters.isEmpty()) System.out.println("Missed Letters");
        historyLetters.forEach(letter -> {
                    System.out.print(letter + " ");
                }
        );
        System.out.println("");
    }

    String getRandomWord() {
        int randInt = new Random().nextInt(words.length);
        return words[randInt];
    }

    void initializeCurrentGuess() {
        currentGuess.clear();
        for (int i = 0; i < answer.length(); i++) {
            currentGuess.add('_');
        }
    }

    void printHangman() {
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
