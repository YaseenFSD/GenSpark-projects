package com.company;

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

    public static String getRandomWord() {
        int randInt = new Random().nextInt(words.length);
        return words[randInt];
    }
}
