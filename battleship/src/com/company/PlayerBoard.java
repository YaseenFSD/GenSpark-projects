package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PlayerBoard {
    private Scanner sc;
    private String name;
    private int maxX = 9;
    private int maxY = 9;
    private int[][] cells = new int[maxX][maxY];
    private ArrayList<Ship> playerShips = new ArrayList<>();

    PlayerBoard() {

    }

    PlayerBoard(Scanner sc) {
        this.sc = sc;
//        System.out.println()
    }

    void askName(String player) {
        System.out.println("Hi " + player + ", what is your name");
        String input = sc.next();
        this.name = input;
    }

    void initializeShips() {
        setPatrolBoat();
    }

    void setPatrolBoat() {
//        printBoard();
//        System.out.println("Where would you like to place the patrol boat? (Size 2)");
//        System.out.println("Input format: x,y");
//        int[] coordinates;
//        if (sc.hasNext("\\d,\\d")){
//            String input = sc.next("\\d,\\d");
//            coordinates = Arrays.stream(input.split(",")).mapToInt(x -> Integer.valueOf(x)).toArray();
//        } else {
//            System.out.println("Invalid format/input, please try again");
//            setPatrolBoat();
//            return;
//        }
//        if (cells[coordinates[0]][coordinates[1]] != 0){
//            System.out.println("Invalid format/input, please try again");
//            setPatrolBoat();
//            return;
//        }
//        System.out.println("Vertical or horizontal? (v or h)");
//        char directionInput = Character.toLowerCase(sc.next().charAt(0));
//        if (directionInput != 'v' || directionInput != 'h') {
//            System.out.println("Invalid format/input, please try again");
//            setPatrolBoat();
//            return;
//        }

    }

//    String askBoatCoordinates(int size, String boatName) {
//        return "";
//    }

    void printBoard(){
        System.out.print(" ");
        for(int i=0;i<maxY;i++){
            System.out.print(" "+ (i+ 1) + " ");
        }
        System.out.println("");

        for (int row = 0; row < maxX; row++) {
            System.out.print(row + 1);
            for (int col = 0; col < maxY; col++) {
                if (cells[row][col] == 0) {
                    System.out.print(" ~ ");
                }
            }
            System.out.println("");
        }
    }

    void insertShip(Ship ship) {
//

    }

}
