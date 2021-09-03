package com.company;


public class Ship {
    private int size;
    private Enum name;
    private int health;
    private int[][] occupiedCells;
    private boolean isSunk;

    Ship(){

    }

    Ship(Enum name, int size, int[][] occupiedCells){
        this.name = name;
        this.size = size;
        this.health = size;
        this.occupiedCells = occupiedCells;

    }

    int[][] getOccupiedCells(){
        return this.occupiedCells;
    }

    void opponentHitShip() {
//  decrease health by one
    }

}
