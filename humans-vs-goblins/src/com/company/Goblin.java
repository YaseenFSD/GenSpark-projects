package com.company;

import java.util.ArrayList;

public class Goblin extends Animal{

//    public Goblin(int level) {
//        this.level = level;
//    }

   private boolean droppedHealPotion(){
//        80% chance of returning true
        return false;
    }

   private boolean droppedArmorPotion(){
//        50% chance of returning true
        return false;
    }
    private boolean droppedUltimatePotion(){
//        20% chance of returning true
        return false;
    }

    ArrayList<String> dropPotions(){
//       return an ArrayList of potions dropped
        return new ArrayList<>();
    }
    @Override
    public String toString(){
//        returns string representation of this object
        return "";
    }
}
