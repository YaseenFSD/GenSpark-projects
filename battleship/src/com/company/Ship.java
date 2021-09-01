package com.company;

import java.util.ArrayList;

public class Ship {
    private int size;
    private int health;
    private boolean isHorizontal;
    private int[][] occupiedCells;
    private boolean isSunk;

    Ship(){

    }

    Ship(int size, int[][] occupiedCells){
        this.size = size;
        this.occupiedCells = occupiedCells;

    }

    void setIsHorizontal(boolean isHorizontal){
        this.isHorizontal = isHorizontal;
    }

    void opponentHitShip() {
//  decrease health by one
    }

}
