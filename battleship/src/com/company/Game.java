package com.company;

import java.util.Scanner;

public class Game {
    void start() {
        Scanner sc = new Scanner(System.in);
//        initializes game and assigns boards to each player
        PlayerBoard playerOne = new PlayerBoard(sc);
        PlayerBoard playerTwo = new PlayerBoard(sc);

//        System.out.println(ShipTypes.Patrol_Boat);
//        System.out.println(ShipTypes.Destroyer);

        playerOne.askName("player one");
        playerTwo.askName("player two");

        playerOne.initializeShips();
    }
}
