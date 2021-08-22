package com.company;

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

    public static void main(String[] args) {
        Hangman game = new Hangman();
        Scanner sc = new Scanner(System.in);
        game.start(sc);

    }
}
