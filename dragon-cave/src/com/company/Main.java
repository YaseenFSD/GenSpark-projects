package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("You are in a land full of dragons. In front of you,");
        System.out.println("you see two caves. In one cave, the dragon is friendly");
        System.out.println("and will share his treasure with you. The other dragon");
        System.out.println("is greedy and hungry and will eat you on sight.");
        System.out.println("Which cave will you go into? (1 or 2)");

        int input = -1;
        while (input == -1) {
            try {
                input = askNumber(sc);
            } catch (InputMismatchException err) {
                System.out.println("Invalid input, please enter a valid number");
                sc.next();
            }
        }
        Random rand = new Random();
        int randInt = rand.nextInt(2) + 1;
        if (randInt == input) {
            goodCave();
        } else {
            badCave();
        }
    }


    public static void goodCave() {
        System.out.println("It is dark and spooky...");
        System.out.println("You approach the cave...");
        System.out.println("You softly hear a voice and...");
        System.out.println("Then you get cuddled by the dragon 🥰");

    }
    public static void badCave() {
        System.out.println("You approach the cave...");
        System.out.println("It is dark and spooky...");
        System.out.println("A large dragon jumps out in front of you! He opens his jaws and...");
        System.out.println("Gobbles you down in one bite!");
    }

    public static int askNumber(Scanner sc){
        int input = sc.nextInt();
        if (input != 1 && input != 2) {
            System.out.println("Invalid input, please enter a valid number");
            return -1;
        }
        return input;
    }
}
