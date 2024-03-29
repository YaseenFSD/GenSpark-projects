package com.company;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
import java.util.stream.Stream;

public class Game {
    ArrayList<Object> board = new ArrayList<>();
    char user;
    char bot;
    char currentPlayer;
    boolean userContinue = true;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";


    public void start() {
        Scanner sc = new Scanner(System.in);
        userPicksSymbol(sc);
        initializeBoard();
        initializeCurrentPlayer();
        while (userContinue) {

            while (!isEndGame()) {
                if (currentPlayer == user) {
                    printBoard();
                    userPlay(sc);
                } else {
                    botPlay();
                }
                swapTurn();
            }
            printBoard();
            displayResult();
            askReplay(sc);
        }
    }

    HashMap<String, Object> miniMax(char currentPlayer, ArrayList<Object> simulatedBoard) {
//        Resource: https://www.youtube.com/watch?v=fT3YWCKvuQE&t=1s
        char maxPlayer = bot;
        char otherPlayer = currentPlayer == bot ? user : bot;

        if (checkPlayerWin(otherPlayer)) {
            int score = otherPlayer == maxPlayer ? 1 * (countAvailableSpots(simulatedBoard) + 1) : -1 * (countAvailableSpots(simulatedBoard) + 1);
            return new HashMap<>(){{
                put("position", null);
                put("score", score);
            }};

        } else if (countAvailableSpots(simulatedBoard) == 0) {
            return new HashMap<>(){{
                put("position", null);
                put("score", 0);
            }};
        }

        HashMap<String, Object> bestMove;
        if (currentPlayer == maxPlayer){
            bestMove = new HashMap<>(){{
                put("position", null);
                put("score", Integer.MIN_VALUE);
            }};
        } else {
            bestMove = new HashMap<>(){{
                put("position", null);
                put("score", Integer.MAX_VALUE);
            }};
        }

        for (int possibleMove: getAvailableIndexes(simulatedBoard)) {
            simulatedBoard.set(possibleMove, currentPlayer);
            HashMap<String, Object> simScore = miniMax(otherPlayer, simulatedBoard);

            simulatedBoard.set(possibleMove, possibleMove + 1);
            simScore.replace("position", possibleMove);

            if (currentPlayer == maxPlayer){
                if ((int)simScore.get("score") > (int)bestMove.get("score")) {
                    bestMove = simScore;
                }
            } else {
                if ((int)simScore.get("score") < (int)bestMove.get("score")) {
                    bestMove = simScore;
                }
            }

        }
        return bestMove;
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
        board.clear();
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
        if(countAvailableSpots(board) == 9){
            botPlayRandomSpot();
        } else {
            board.set((int)miniMax(bot, board).get("position"), bot);
        }
    }

    void botPlayRandomSpot() {
        ArrayList<Integer> spots = getAvailableIndexes(board);
        int rand = new Random().nextInt(spots.size());
        board.set(spots.get(rand), bot);
    }

    int countAvailableSpots(ArrayList<Object> board) {
//        return a count of current available spots
        int count = (int) getAvailableSpotsStream(board).count();
        return count;
    }

    ArrayList<Integer> getAvailableIndexes(ArrayList<Object> board) {
//        return an ArrayList of the indexes of currently available spots

        ArrayList<Integer> spots = new ArrayList<>();


        getAvailableSpotsStream(board).forEach(spot -> spots.add((int) spot - 1));
        return spots;
    }

    Stream getAvailableSpotsStream(ArrayList<Object> board) {
        return board.stream().filter(spot -> spot.getClass().getSimpleName().equals("Integer"));
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

        if (countAvailableSpots(board) == 0) {
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

    void displayResult() {
        if (checkPlayerWin(bot)) {
//            you lost
            System.out.println("Better luck next time!");
        } else if (checkPlayerWin(user)) {
//            You won
            System.out.println("Congrats you won!");

        } else {
//            Tie
            System.out.println("The game has ended in a draw.");

        }
    }



    boolean checkPlayerWin(char player) {
        //        If player gets 3 in a row return true
        //        horizontal checks
        if ((board.get(0) == board.get(1) && board.get(1) == board.get(2) && board.get(0).equals(player)) ||
                (board.get(3) == board.get(4) && board.get(4) == board.get(5) && board.get(3).equals(player)) ||
                (board.get(6) == board.get(7) && board.get(7) == board.get(8) && board.get(6).equals(player))
        ) {
            return true;
        }

        // vertical checks
        if ((board.get(0) == board.get(3) && board.get(3) == board.get(6) && board.get(0).equals(player)) ||
                (board.get(1) == board.get(4) && board.get(4) == board.get(7) && board.get(1).equals(player)) ||
                (board.get(2) == board.get(5) && board.get(5) == board.get(8) && board.get(2).equals(player))
        ) {
            return true;
        }

        // diagonal checks
        if ((board.get(0) == board.get(4) && board.get(4) == board.get(8) && board.get(0).equals(player)) ||
                (board.get(2) == board.get(4) && board.get(4) == board.get(6) && board.get(2).equals(player))
        ) {
            return true;
        }

        return false;
    }


    void resetGame() {
        initializeBoard();
        initializeCurrentPlayer();
    }

    void askReplay(Scanner sc) {
        System.out.println("Would you like to play again? (y or n)");
        char input = Character.toLowerCase(sc.next().charAt(0));
        if (input == 'n') {
            userContinue = false;
        } else {
            resetGame();
        }
    }

    void printBoard() {
//        Print current board
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i) instanceof Character){
                System.out.print(ANSI_RESET + board.get(i) + ANSI_CYAN);
            }else {
                System.out.print(ANSI_CYAN + board.get(i));
            }
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
