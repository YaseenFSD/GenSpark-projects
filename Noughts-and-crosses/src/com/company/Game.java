package com.company;

import com.sun.jdi.IntegerType;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game {
    ArrayList<Object> board = new ArrayList<>();
    char user;
    char bot;
    char currentPlayer;

    public void start() {
        Scanner sc = new Scanner(System.in);
        userPicksSymbol(sc);
        initializeBoard();
        printBoard();
        
    }

    void userPicksSymbol(Scanner sc) {
        System.out.println("Choose your symbol (X or O)");
        char input = sc.next().charAt(0);
        input = Character.toUpperCase(input);
        if (input == 'X') {
            user = 'X';
            bot = 'O';
            System.out.println("You have been assigned to X");
        } else if (input == 'O') {
            user = 'O';
            bot = 'X';
            System.out.println("You have been assigned to O");
        } else {
            user = 'X';
            bot = 'O';
            System.out.println("Invalid input. You are assigned to X");
        }
    }

    void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            board.add(i + 1);
        }
    }

    void initializeCurrentPlayer() {
//        Chooses a random player to go first
        int rand = new Random().nextInt(2) + 1;
        if (rand == 1) {
            currentPlayer = user;
        } else {
            currentPlayer = bot;
        }
    }

    void swapTurn() {
        if (currentPlayer == user) {
            currentPlayer = bot;
        } else {
            currentPlayer = user;
        }
    }

    boolean isValidSpot(Object spot) {
        if (spot.getClass().getSimpleName().equals("Integer")) {
            int intRep = Integer.parseInt(spot.toString());
            if (intRep < 10 && intRep > 0){
                return true;
            }
        }
        return false;
    }

    void userPlay(Scanner sc) {
//        User inputs a position and updates board

    }

    void botPlay() {
//        Bot Plays and updates board
//        for (int i = 0; i < board.size(); i++) {
//            if (if  board.get(i)) {
//
//            }
//        }
    }

    int countAvailableSpots() {
//        return a count of current available spots
        return 0;
    }

    ArrayList<Integer> getAvailableSpots() {
//        return an ArrayList of the numbers of currently available spots

        return new ArrayList<>();
    }

    boolean isEndGame() {
//        if any 3 in a row or no more spaces are left return true

        return false;
    }

    boolean checkTie() {
//        If getAvailableSpots.isEmpty and both (checkBotWin and checkUserWin) are false, then return true
        return false;
    }

    boolean checkBotWin() {
//        If bot gets 3 in a row return true
        return false;
    }

    boolean checkUserWin() {
//        If user gets 3 in a row return true
        return false;
    }

    void printBoard() {
//        Print current board
        for (int i = 0; i < board.size(); i++) {
            System.out.print(board.get(i));
            if (i == board.size() - 1) {
//                end of board
                System.out.println("");
                continue;
            }
            ;
            if ((i + 1) % 3 == 0) {
                System.out.println("\n__________");
            } else {
                System.out.print(" | ");
            }
        }
    }


}
