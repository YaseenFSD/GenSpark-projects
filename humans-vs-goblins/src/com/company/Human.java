package com.company;

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

    void pickWorldAction() {
//        pick an action of (use potion, move, print stats, or print inventory)
        printWorldActions();
        if (Land.sc.hasNextInt()) {
            int input = Land.sc.nextInt();
            switch (input) {
                case 1:
//                    View potions
                    usePotion();
                    break;
                case 2:
                    printStats();
//                    Print stats
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        } else {
            char direction = Land.sc.next().charAt(0);
            move(direction);

        }
    }

    private void printWorldActions() {
//        prints world actions
        printCoordinates();
        System.out.println("Choose a direction (n/s/w/e)");
        System.out.println("Other options: 1- View potions, 2- Print stats");
    }

    private void printCoordinates() {
        System.out.println(String.format("Current coordinates: x=%d, y=%d", this.coordinates[0], this.coordinates[1]));
    }

    void increaseMaxHealth(int increaseBy) {
//        increase max health by 'increaseBy'
    }

    void levelUpChance() {
//        chance to increase level by 1 which should also increase maxHealth and fill health;
//        the higher the level the less chance of leveling up
    }

    void pickCombatAction(char input) {
//        pick an action of flee, attack or heal
    }

    private void fleeCombat() {
//        ends combat
    }


    void move(char direction) {
//        moves character by 1 unit towards 'direction'
//        if (isOutOfBounds(direction)) return;
        switch (direction) {
            case 'n':
                if(this.coordinates[1] + 1 > Land.maxYrange / 2){
                    System.out.println("You can not go any further in this direction");
                    break;
                }
                this.coordinates[1]++;
                break;
            case 's':
                if(this.coordinates[1] - 1 < -(Land.maxYrange / 2)){
                    System.out.println("You can not go any further in this direction");
                    break;
                }
                this.coordinates[1]--;

                break;
            case 'w':
                if(this.coordinates[0] - 1 < -(Land.maxXrange / 2)){
                    System.out.println("You can not go any further in this direction");
                    break;
                }
                this.coordinates[0]--;

                break;
            case 'e':
                if(this.coordinates[0] + 1 > Land.maxXrange / 2){
                    System.out.println("You can not go any further in this direction");
                    break;
                }
                this.coordinates[0]++;

                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }


    void usePotion() {
//        asks user which potion to use and uses if available
    }

    private void printInventory() {
//        prints potions with amount of each potion
    }

    private void printStats() {
//        prints name, level, health, and maxHealth
    }

    @Override
    public String toString() {
//        returns string representation of this object
        return String.format("Name: %s\nLevel: %s\nHealth: %s\nMax Health: %s", this.name, this.level, this.currentHealth, this.maxHealth);

    }
}
