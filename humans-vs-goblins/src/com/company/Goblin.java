package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Goblin extends Animal{
    public static final String[] NAMES = {"Ulgrim", "Xull", "Volkov", "Caspian"};

//    Goblin(){
//        this.name = randomName();
//    }

    Goblin(int level) {
        this.level = level;
        this.name = randomName();
        this.maxHealth = 100 + (level - 1) * 20;
        this.currentHealth = this.maxHealth;
    }


    private String randomName(){
        int randInt = new Random().nextInt(NAMES.length);
        return NAMES[randInt];
    }

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
        return String.format("Goblin Type: %s\nLevel: %s\nMax Health: %s", this.name, this.level, this. maxHealth);
    }
}
