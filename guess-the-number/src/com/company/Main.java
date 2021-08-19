package com.company;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! What is your name?");
        String username = sc.next();
        System.out.printf("Hello %s, guess a number between 1 and 20\n", username);
        int randomInt = new Random().nextInt(20) + 1;
        int userNum = 0;
        int guessCounter = 0;
//        while (randomInt != userNum && guessCounter < 6){
        userNum = retrieveInput(sc);
//            guessCounter++;
//        }
        System.out.println(userNum);

    }

    public static int retrieveInput(Scanner sc) {
//        Returns the guessed number. If an error occurs this method returns -1
        int num;
        try {
            num = sc.nextInt();
            if (num < 1 || num > 20) {
                System.out.println("Error number out of range");
                num = -1;
            }
        } catch (InputMismatchException err) {
            System.out.println("Error Invalid Input");
            num = -1;
        }


        return num;
    }

}
