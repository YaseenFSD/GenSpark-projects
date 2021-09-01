package com.company;


public class Ship {
    private int size;
    private Enum name;
    private int health;
    private boolean isHorizontal;
    private int[][] occupiedCells;
    private boolean isSunk;

    Ship(){

    }

    Ship(Enum name, int size, int[][] occupiedCells){
        this.name = name;
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
