package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner sc = new Scanner(System.in);
        Crypt crypt = new Crypt(sc);
        Enum cryptType = crypt.askCryptType();

        if (Crypts.Encrypt.equals(cryptType)) {
            String filename = crypt.askFileName();
            String message = crypt.askMessage();
            crypt.encrypt(filename, message);
        } else if (Crypts.Decrypt.equals(cryptType)) {
            String filename = crypt.askFileName();
            crypt.decrypt(filename);
        }
    }

}
