package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! What is your name?");
        String username = sc.next();
        System.out.printf("Hello %s, guess a number between 1 and 20\n", username);

    }
}
