package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Crypt {
    private Scanner sc;
    private Pattern p = Pattern.compile("[A-Za-z]");

    Crypt(Scanner sc) {
        this.sc = sc;
    }

    Enum askCryptType(){
//        returns "encrypt" if user wants to encrypt a message and "decrypt" if user wants to decrypt message
        System.out.println("Do you wish to encrypt or decrypt a message?");
        String input = sc.nextLine();
        if (input.contains("encrypt")) {
            return Crypts.Encrypt;
        } else if (input.contains("decrypt")) {
            return Crypts.Decrypt;
        } else {
            System.out.println("Invalid option, Try again");
            return askCryptType();
        }
    }

    String askMessage(){
        System.out.println("Enter the message you would like to encrypt");
        String input = sc.nextLine();
        return input;
    }

    String askFileName() {
        System.out.println("Input the filename");
        String input = sc.nextLine();
        return input;
    }

    public void encrypt(String filename, String message) throws IOException {
        int key = askKey();
//        System.out.println(key);
        String encrypted = encryptString(message, key);
        encrypted += "\nKey: " + key;


        Files.write(Paths.get(filename), encrypted.getBytes(StandardCharsets.UTF_8));
    }

    public String decrypt(String filename) throws IOException {
        int key = askKey();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        lines.remove(lines.size() - 1);
        String result = "";
        for (String line : lines) {
            result += decryptString(line, key);
            System.out.println(decryptString(line, key));
        }
        return result;
    }

    private int askKey() {
        System.out.println("Enter a key (Integer)");
        if (sc.hasNextInt()) {
            return Math.abs(sc.nextInt());
        } else {
            System.out.println("Invalid Key. Key must be an integer");
            sc.next();
            return askKey();
        }
    }

    private String encryptString(String message, int key) {
//        return message.chars().map(x -> encryptChar((char) x, key)).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        String result = "";
        for (int i = 0; i < message.length(); i++) {
            result += encryptChar(message.charAt(i), key);
        }
        return result;
    }

    private String decryptString(String message, int key) {
//        return message.chars().map(x -> decryptChar((char) x, key)).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        String result = "";
        for (int i = 0; i < message.length(); i++) {
            result += decryptChar(message.charAt(i), key);
        }
        return result;

    }

    private char encryptChar(char letter, int key) {
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
        if (isLowerCase) {
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

    private char decryptChar(char letter, int key) {
        boolean isAlphaLetter = p.matcher(String.valueOf(letter)).matches();
        if (!isAlphaLetter) {
            return letter;
        }
        int asciiVal = letter;
        boolean isLowerCase = letter > 90 ? true : false;
        int newAsciiVal;
        int alphaOrder; // alphaOrder example : 0 is a/A. 25 is z/Z
        if (isLowerCase) {
            alphaOrder = asciiVal - 97;
            newAsciiVal = alphaOrder - key;
            while (newAsciiVal < 0) {
//                any letter that shifts more than the beginning (letter a) will start shifting from z (26th letter)

                newAsciiVal = 26 + newAsciiVal;
            }

            newAsciiVal += 97;

        } else {
            alphaOrder = asciiVal - 65;
            newAsciiVal = alphaOrder - key;
            while (newAsciiVal < 0) {
//                any letter that shifts more than the beginning (letter a) will start shifting from z (26th letter)
                newAsciiVal = 26 + newAsciiVal;
            }

            newAsciiVal += 65;
        }
        return (char) newAsciiVal;
    }

}
