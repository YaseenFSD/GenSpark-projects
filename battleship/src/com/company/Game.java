package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    void start() {
        Scanner sc = new Scanner(System.in);
//        initializes game and assigns boards to each player
        PlayerBoard playerOne = new PlayerBoard(sc);
        PlayerBoard playerTwo = new PlayerBoard(sc);


        playerOne.askName("player one");
        playerTwo.askName("player two");
        playerOne.initializeShips();
        for (int i = 0; i < 50; i++) System.out.println();
//        Hide previous content out of sight
        System.out.println(playerTwo.getName() + ", your turn!");
        playerTwo.initializeShips();
        for (int i = 0; i < 50; i++) System.out.println();


        while (playerOne.getPlayerHasAliveShips()){
            playerOne.attemptAttack(playerTwo);
            if (!playerTwo.getPlayerHasAliveShips()){
                break;
            }
            playerTwo.attemptAttack(playerOne);
        }
    }
}
