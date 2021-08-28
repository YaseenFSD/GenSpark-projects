package com.company;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.HashMap;

public class Human extends Animal {
    boolean isInCombat = false;
    HashMap<String, Integer> potions = new HashMap<>() {{
        put("heal", 0);
        put("armor", 0);
        put("ultimate", 0);
    }};

    Human() {
        this.name = initializeName();
    }

    String initializeName() {
        System.out.println("Hello human, what is your name?");
        String name = Land.sc.next();
        return name;
    }


    void increaseMaxHealth(int increaseBy) {
//        increase max health by 'increaseBy'
    }

    void levelUp() {
//        increase level by 1 which should also increase maxHealth and fill health;
    }

    void pickCombatAction(char input) {
//        pick an action of flee, attack or heal
    }

    void fleeCombat() {
//        ends combat
    }

    void move(char direction) {
//        moves character by 1 unit towards 'direction'
    }

    void usePotion() {
//        asks user which potion to use and uses if available
    }

    void printInventory() {
//        prints potions with amount of each potion
    }

    void printStats() {
//        prints name, level, health, and maxHealth
    }

    @Override
    public String toString() {
//        returns string representation of this object
        return String.format("Name: %s\nLevel: %s\nHealth: %s\nMax Health: %s", this.name, this.level,this.currentHealth, this.maxHealth);

    }
}
