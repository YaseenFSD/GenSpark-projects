package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerBoard {
    private Scanner sc;
    private String name;
    private int maxX = 9;
    private int maxY = 9;
    private int[][] cells = new int[maxX][maxY];
    private ArrayList<Ship> playerShips = new ArrayList<>();

    PlayerBoard  () {

    }

    PlayerBoard (Scanner sc) {
        this.sc = sc;
//        System.out.println()
    }

    void askName(String player){
        System.out.println("Hi " + player + ", what is your name");
        String input = sc.next();
        this.name = input;
    }

    void insertShip(Ship ship) {
//

    }

}
