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
        printBoard();
    }

    void setPatrolBoat() {
        printBoard();
        System.out.println("Where would you like to place the patrol boat? (Size 2)");
        System.out.println("Input format: x,y");
        int[] startingCoordinates;
        if (sc.hasNext("\\d,\\d")){
            String input = sc.next("\\d,\\d");
            startingCoordinates = Arrays.stream(input.split(",")).mapToInt(x -> Integer.valueOf(x) - 1).toArray();
        } else {
            sc.next();
            System.out.println("Invalid format/input, please try again");
            setPatrolBoat();
            return;
        }
        int rowNum = startingCoordinates[1];
        int colNum = startingCoordinates[0];

        if (cells[colNum][rowNum] != 0){
            System.out.println("Invalid format/input, please try again");
            setPatrolBoat();
            return;
        }
        System.out.println("Vertical or horizontal? (v or h)");
        char directionInput = Character.toLowerCase(sc.next().charAt(0));
        if (directionInput != 'v' && directionInput != 'h') {
            System.out.println("Invalid format/input, please try again");
            setPatrolBoat();
            return;
        }
//        Ship currentBoat = new Ship(Ships.Patrol_Boat,2, int );
//        int rowNum = startingCoordinates[1];
//        int colNum = startingCoordinates[0];
        int[][] occupiedCells = new int[2][2];
//        array is filled with arrays of coordinates to replicate reserved spots
        occupiedCells[0] = new int[]{rowNum, colNum};
        if (directionInput == 'v'){
            for (int i = 1; i < 2; i++) {
                if(rowNum + i >= maxY){
                    System.out.println("Ship cannot be placed out of bounds, please try again");
                    setPatrolBoat();
                    return;
                }
                if (cells[rowNum + i][colNum] != 0){
                    System.out.println("Ship collides with another ship, please try again");
                    setPatrolBoat();
                    return;
                }
                occupiedCells[i] = new int[]{rowNum + i , colNum};
            }

        }

        if (directionInput == 'h') {
            for (int i = 1; i < 2; i++) {
                if(colNum + i >= maxX){
                    System.out.println("Ship cannot be placed out of bounds, please try again");
                    setPatrolBoat();
                    return;
                }
                if (cells[rowNum][colNum + i] != 0){
                    System.out.println("Ship collides with another ship, please try again");
                    setPatrolBoat();
                    return;
                }
                occupiedCells[i] = new int[]{rowNum , colNum + i};
            }

        }
        for (int[] coordinates : occupiedCells) {
            int row = coordinates[0];
            int col = coordinates[1];
            cells[row][col] = 1;
        }
        System.out.println(playerShips);

        Ship patrol = new Ship(ShipTypes.Patrol_Boat, 2, occupiedCells);
        playerShips.add(patrol);

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
                } else {
                    System.out.print(" "+ cells[row][col] + " ");
                }
            }
            System.out.println("");
        }
    }

    void insertShip(Ship ship) {
//

    }

}
