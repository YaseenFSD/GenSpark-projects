package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class PlayerBoard {
    private Scanner sc;
    private String name;
    private int maxX = 9;
    private int maxY = 9;
    private int[][] cells = new int[maxX][maxY];
    private ArrayList<Ship> playerShips = new ArrayList<>();
    private HashMap<Integer, Enum> ids = new HashMap<>() {{
        put(1, ShipTypes.Patrol_Boat);
        put(2, ShipTypes.Submarine);
        put(3, ShipTypes.Destroyer);
        put(4, ShipTypes.Battleship);
        put(5, ShipTypes.Carrier);
    }};

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
        setBoat(ShipTypes.Patrol_Boat, 2);
        printBoard();
    }

    void setBoat(Enum ShipType, int size) {

        int[] startingCoordinates = askStartingPosition(ShipType, size);
        int rowNum = startingCoordinates[1];
        int colNum = startingCoordinates[0];
        isValidStartPoint(rowNum, colNum);

        if (!isValidStartPoint(rowNum, colNum)) {
            setBoat(ShipType, size);
            return;
        }
        char directionInput = setDirection();
        if (!didPlaceShip(ShipType, size, startingCoordinates, directionInput)) {
            setBoat(ShipType, size);
            return;
        }


    }

    boolean isValidStartPoint(int rowNum, int colNum) {
        if (cells[colNum][rowNum] != 0) {
            System.out.println("Invalid format/input, please try again");
            return false;
        }
        return true;
    }

    char setDirection() {
        System.out.println("Vertical or horizontal? (v or h)");
        char directionInput = Character.toLowerCase(sc.next().charAt(0));
        if (directionInput != 'v' && directionInput != 'h') {
            System.out.println("Invalid format/input, please try again");
            return setDirection();
        }
        return directionInput;
    }

    boolean didPlaceShip(Enum ShipType, int size, int[] startingCoordinates, char directionInput) {
        int rowNum = startingCoordinates[1];
        int colNum = startingCoordinates[0];

        int[][] occupiedCells = new int[size][2];
//        array is filled with arrays of coordinates to represent reserved spots
        occupiedCells[0] = new int[]{rowNum, colNum};
        if (directionInput == 'v') {
            for (int i = 1; i < size; i++) {
                if (rowNum + i >= maxY) {
                    System.out.println("Ship cannot be placed out of bounds, please try again");
                    return false;
                }
                if (cells[rowNum + i][colNum] != 0) {
                    System.out.println("Ship collides with another ship, please try again");
                    return false;
                }

                occupiedCells[i] = new int[]{rowNum + i, colNum};
            }

        }

        if (directionInput == 'h') {
            for (int i = 1; i < size; i++) {
                if (colNum + i >= maxX) {
                    System.out.println("Ship cannot be placed out of bounds, please try again");
                    return false;
                }
                if (cells[rowNum][colNum + i] != 0) {
                    System.out.println("Ship collides with another ship, please try again");
                    return false;
                }
                occupiedCells[i] = new int[]{rowNum, colNum + i};
            }

        }
        for (int[] coordinates : occupiedCells) {
            int row = coordinates[0];
            int col = coordinates[1];
            cells[row][col] = 1;
        }

        Ship patrol = new Ship(ShipType, size, occupiedCells);
        playerShips.add(patrol);
        return true;
    }


    int[] askStartingPosition(Enum ship, int size) {
        printBoard();
        System.out.println("Where would you like to place the " + ship + " ? (Size " + size + ")");
        System.out.println("Input format: x,y");
        int[] startingCoordinates;
        if (sc.hasNext("\\d,\\d")) {
            String input = sc.next("\\d,\\d");
            startingCoordinates = Arrays.stream(input.split(",")).mapToInt(x -> Integer.valueOf(x) - 1).toArray();
        } else {
            sc.next();
            System.out.println("Invalid format/input, please try again");
            return askStartingPosition(ship, size);
        }
        return startingCoordinates;
    }


    void printBoard() {
        System.out.print(" ");
        for (int i = 0; i < maxY; i++) {
            System.out.print(" " + (i + 1) + " ");
        }
        System.out.println("");

        for (int row = 0; row < maxX; row++) {
            System.out.print(row + 1);
            for (int col = 0; col < maxY; col++) {
                if (cells[row][col] == 0) {
                    System.out.print(" ~ ");
                } else {
                    System.out.print(" " + ids.get(cells[row][col]).toString().charAt(0) + " ");
                }
            }
            System.out.println("");
        }
    }

    void insertShip(Ship ship) {
//

    }

}
