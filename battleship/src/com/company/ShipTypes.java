package com.company;

public enum ShipTypes {
    Carrier,
    Battleship,
    Destroyer,
    Submarine,
    Patrol_Boat;

    @Override
    public String toString(){
        switch (this){
            case Patrol_Boat:
                return "Patrol Boat";
            default:
                return this.toString();
        }
    }
}
