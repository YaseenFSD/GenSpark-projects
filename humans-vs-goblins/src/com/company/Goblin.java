package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Goblin extends Animal {
    public static final String[] NAMES = {"Ulgrim", "Xull", "Volkov", "Caspian"};


    Goblin(int level) {
        this.level = level;
        this.name = randomName();
        this.maxHealth = 100 + (level - 1) * 20;
        this.currentHealth = this.maxHealth;
    }


    private String randomName() {
        int randInt = new Random().nextInt(NAMES.length);
        return NAMES[randInt];
    }

    private boolean droppedHealPotion() {
//        80% chance of returning true
        int randInt = new Random().nextInt(100);
        if (randInt < 80) {
            return true;
        }
        return false;
    }

    private boolean droppedArmorPotion() {
//        50% chance of returning true
        int randInt = new Random().nextInt(100);
        if (randInt < 50) {
            return true;
        }
        return false;
    }

    private boolean droppedUltimatePotion() {
//        20% chance of returning true
        int randInt = new Random().nextInt(100);
        if (randInt < 20) {
            return true;
        }
        return false;
    }

    ArrayList<String> dropPotions() {
//       return an ArrayList of potions dropped
        ArrayList<String> potions = new ArrayList<>();
        if (droppedHealPotion()) potions.add("heal");
        if (droppedArmorPotion()) potions.add("armor");
        if (droppedUltimatePotion()) potions.add("ultimate");
        return potions;
    }

    @Override
    public String toString() {
//        returns string representation of this object
        return String.format("Goblin Type: %s\nLevel: %s\nMax Health: %s", this.name, this.level, this.maxHealth);
    }
}
