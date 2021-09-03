package com.company;


public class Ship {
    private int size;
    private Enum name;
    private int health;
    private int[][] occupiedCells;
    private boolean isSunk = false;

    Ship(){

    }

    boolean getIsSunk() {
        return this.isSunk;
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

    void sinkShip(){
        System.out.println("Oh no a "+ this.name + " has sunk");
        this.isSunk = true;
    }

    void opponentHitShip() {
//  decrease health by one
        this.health = this.health - 1;
        if (this.health == 0) {
            sinkShip();
        }
    }

}
