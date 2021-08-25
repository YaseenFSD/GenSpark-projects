package com.company;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game {
    ArrayList<Object> board = new ArrayList<>();
    char user;
    char bot;
    char currentPlayer;
//    boolean userContinue = true;
    public void start() {
        Scanner sc = new Scanner(System.in);
        userPicksSymbol(sc);
        initializeBoard();
        initializeCurrentPlayer();
//        while (userContinue){
//
//        }
        while (!isEndGame()) {
            if (currentPlayer == user) {
                printBoard();
                userPlay(sc);
            } else {
                botPlay();
            }
            swapTurn();
//            printTurn
        }
        printBoard();
        System.out.println("End");
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
            System.out.printf("You are %c, you are first\n", user);
        } else {
            currentPlayer = bot;
            System.out.printf("You are %c, bot goes first\n", user);
        }
    }

    void swapTurn() {
        if (currentPlayer == user) {
            currentPlayer = bot;
        } else {
            currentPlayer = user;
        }
    }

    boolean isValidSpot(int spot) {
        if (spot < 10 && spot > 0) {
//            prevents index out of bounds error
            if (board.get(spot - 1).getClass().getSimpleName().equals("Integer")) {
//              check if spot is empty (by having a number in the spot)
                return true;
            }
        }
        if (currentPlayer == user) {
            System.out.println("Invalid spot/input, please try again");
        }
        return false;
    }

    void userPlay(Scanner sc) {
//        User inputs a position and updates board
        System.out.printf("Your turn (%c), pick a spot\n", user);
        int spot;


        do {
            if (sc.hasNextInt()) {
                spot = sc.nextInt();
            } else {
                System.out.println("Not a valid input");
                sc.next();
                spot = 0;
            }
        } while (!isValidSpot(spot));


        int positionIndex = spot - 1;
        board.set(positionIndex, user);
    }

    void botPlay() {
//        Bot Plays and updates board
        for (int i = 0; i < board.size(); i++) {
            if (isValidSpot(i + 1)) {
                board.set(i, bot);
                break;
            }
        }
    }

    int countAvailableSpots() {
//        return a count of current available spots
        int count = (int) board.stream().filter(spot -> spot.getClass().getSimpleName().equals("Integer")).count();
        return count;
    }

    ArrayList<Integer> getAvailableIndexes() {
//        return an ArrayList of the indexes of currently available spots

        return new ArrayList<>();
    }

    boolean isEndGame() {
//        if any 3 in a row or no more spaces are left return true
/*
        0 | 1 | 2
        __________
        3 | 4 | 5
        __________
        6 | 7 | 8
*/

        if (countAvailableSpots() == 0) {
            return true;
        }
//        horizontal checks
        if ((board.get(0) == board.get(1) && board.get(1) == board.get(2)) ||
                (board.get(3) == board.get(4) && board.get(4) == board.get(5)) ||
                (board.get(6) == board.get(7) && board.get(7) == board.get(8))
        ) {
            return true;
        }

        // vertical checks
        if ((board.get(0) == board.get(3) && board.get(3) == board.get(6)) ||
                (board.get(1) == board.get(4) && board.get(4) == board.get(7)) ||
                (board.get(2) == board.get(5) && board.get(5) == board.get(8))
        ) {
            return true;
        }

        // diagonal checks
        if ((board.get(0) == board.get(4) && board.get(4) == board.get(8)) ||
                (board.get(2) == board.get(4) && board.get(4) == board.get(6))
        ) {
            return true;
        }

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
