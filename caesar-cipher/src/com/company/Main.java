package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner sc = new Scanner(System.in);
        String message = "This is a testing message";
        new Crypt(sc).encrypt("encryption.txt" ,message);
    }
}
