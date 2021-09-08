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
//        System.out.println( encryptChar("a", 2));
//        System.out.println( encryptChar(".", 25));
//        System.out.println( encryptChar("A", 2));
//        System.out.println( encryptChar("0", 29));
//        System.out.println( encryptChar("z", 1));
//        System.out.println( encryptChar("z", 27));
//        System.out.println( encryptChar("Z", 27));
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

    char encryptChar(String letter, int key) {
        boolean isAlphaLetter = p.matcher(letter).matches();
        char charRep = letter.charAt(0);
        if (!isAlphaLetter) {
            return charRep;
        }

        int asciiVal = charRep;
        boolean isLowerCase = charRep > 90 ? true : false;
//        90 is 'Z'
        int newAsciiVal;
        int alphaOrder; // 0 should be a/A. 25 should be z/Z
        if (isLowerCase){
            alphaOrder = asciiVal - 97;
            newAsciiVal = alphaOrder + key;
            while (newAsciiVal > 25) {
                newAsciiVal %= 26;
            }
            newAsciiVal += 97;
        } else {
            alphaOrder = asciiVal - 65;
            newAsciiVal = alphaOrder + key;
            while (newAsciiVal > 25) {
                newAsciiVal %= 26;
            }
            newAsciiVal += 65;
        }
        return (char) newAsciiVal;
    }

    char decryptChar(String letter) {
        boolean isAlphaLetter = p.matcher(letter).matches();
        if (isAlphaLetter) {
            //TODO
         }
        return letter.charAt(0);
    }

}
