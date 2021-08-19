package com.company;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean gameIsFinished = false;
        boolean userWon = false;
        int randomInt = new Random().nextInt(20) + 1;
        int userNum = 0;
        int guessCounter = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! What is your name?");
        String username = sc.next();
        System.out.printf("Hello %s, guess a number between 1 and 20\n", username);
        while (!gameIsFinished) {
            while (randomInt != userNum && guessCounter < 6) {
                userNum = retrieveInput(sc);

                if (userNum == -1){
                    System.out.println("Please enter a valid number");
                    continue;
                }

                guessCounter++;

                if (randomInt == userNum) {
                    userWon = true;
                    break;
                } else {
                    if (userNum > randomInt) {
                        System.out.println("Too high, guess again!");
                    } else {
                        System.out.println("Too low, guess again!");
                    }
                }


            }

            if (userWon) {
                System.out.printf("You won in %d guesses, would you like to play again? (y or n)\n", guessCounter);
            } else {
                System.out.printf("Sorry %s you did not win, would you like to try again? (y or n)\n", username);
            }
            char replayChar = sc.next().charAt(0);
            if (replayChar != 'y') {
//                ends game if anything other than 'y' is inputted
                gameIsFinished = true;
            } else {
                guessCounter = 0;
                randomInt = new Random().nextInt(20) + 1;
                userWon = false;
                System.out.println("A new game has begun! Guess a new number between 1 and 20.");
            }

        }

    }

    public static int retrieveInput(Scanner sc) {
//        Returns the guessed number. If an error occurs this method returns -1
        int num;
        try {
            num = sc.nextInt();
            if (num < 1 || num > 20) {
                System.out.println("Error: number out of range");
                num = -1;
            }
        } catch (InputMismatchException err) {
            System.out.println("Error: Invalid Input");
            sc.next(); //       This is used to prevent an infinite loop when a user doesn't input a number.
            num = -1;
        }




        return num;
    }

}
