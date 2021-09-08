package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Crypt {
    private Scanner sc;
    private Pattern p = Pattern.compile("[A-Za-z]");

    Crypt(Scanner sc) {
        this.sc = sc;
    }

    public void encrypt(String filename, String message) throws IOException {
//        askKey();
//        System.out.println( encryptChar('a', 2));
//        System.out.println( encryptChar('.', 25));
//        System.out.println( encryptChar('A', 2));
//        System.out.println( encryptChar('0', 29));
//        System.out.println( encryptChar('z', 1));
//        System.out.println( encryptChar('z', 26));
//        System.out.println( encryptChar('Z', 27));
//        System.out.println( encryptChar('Z', 53));
//
//        System.out.println("Decrypting");
//        System.out.println(decryptChar('c',2));
//        System.out.println(decryptChar('.',25));
//        System.out.println(decryptChar('C',2));
//        System.out.println(decryptChar('0',29));
//        System.out.println(decryptChar('a',1));
//        System.out.println(decryptChar('z',26));
//        System.out.println(decryptChar('A',27));
//        System.out.println(decryptChar('A',53));


        Files.write(Paths.get(filename), message.getBytes(StandardCharsets.UTF_8));
    }

    public String decrypt(String filename, int key) {
        return "";
    }

    private int askKey() {
        System.out.println("Enter a key 1-52");
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            System.out.println("Invalid Key. Key must be an integer");
            sc.next();
            return askKey();
        }
    }

    char encryptChar(char letter, int key) {
        boolean isAlphaLetter = p.matcher(String.valueOf(letter)).matches();
//        char charRep = letter.charAt(0);
        if (!isAlphaLetter) {
            return letter;
        }

        int asciiVal = letter;
        boolean isLowerCase = letter > 90 ? true : false;
//        90 is 'Z'
        int newAsciiVal;
        int alphaOrder; //  // alphaOrder example : 0 is a/A. 25 is z/Z
        if (isLowerCase){
            alphaOrder = asciiVal - 97;
            newAsciiVal = alphaOrder + key;
            if (newAsciiVal > 25) {
                newAsciiVal %= 26;
            }
            newAsciiVal += 97;
        } else {
            alphaOrder = asciiVal - 65;
            newAsciiVal = alphaOrder + key;
            if (newAsciiVal > 25) {
                newAsciiVal %= 26;
            }
            newAsciiVal += 65;
        }
        return (char) newAsciiVal;
    }

    char decryptChar(char letter, int key) {
        boolean isAlphaLetter = p.matcher(String.valueOf(letter)).matches();
        if (!isAlphaLetter) {
            return letter;
        }
        int asciiVal = letter;
        boolean isLowerCase = letter > 90 ? true : false;
        int newAsciiVal;
        int alphaOrder; // alphaOrder example : 0 is a/A. 25 is z/Z
        if (isLowerCase){
            alphaOrder = asciiVal - 97;
            newAsciiVal = alphaOrder - key;
            while (newAsciiVal < 0) {
                newAsciiVal = 26 + newAsciiVal;
            }

            newAsciiVal += 97;

        } else {
            alphaOrder = asciiVal - 65;
            newAsciiVal = alphaOrder - key;
            while (newAsciiVal < 0) {
                newAsciiVal = 26 + newAsciiVal;
            }

            newAsciiVal += 65;
        }
        return (char) newAsciiVal;
    }

}
