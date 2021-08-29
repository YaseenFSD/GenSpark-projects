package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Human extends Animal {
    boolean isInCombat = false;
    HashMap<String, Integer> potions = new HashMap<>() {{
        put("heal", 1);
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
                    usedPotion();
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
        this.maxHealth += increaseBy;
    }

    void heal() {
        this.currentHealth = this.maxHealth;
    }


    void levelUpChance() {
//        chance to increase level by 1 which should also increase maxHealth and fill health;
//        the higher the level the less chance of leveling up
        int randInt = new Random().nextInt((int) (this.level * .4) + 1);
        if (randInt == 0) {
            this.level++;
            increaseMaxHealth(20);
            this.currentHealth = this.maxHealth;
            System.out.println(String.format("You leveled up! Your health is now %d/%d", this.currentHealth, this.maxHealth));
        }
    }

    void pickCombatAction(Goblin goblin) {
//        pick an action of flee, attack or heal
        printCombatActions();

        while (!Land.sc.hasNextInt()) {
            Land.sc.next();
            System.out.println("Invalid option");
        }
        int input = Land.sc.nextInt();

        switch (input) {
            case 1:
                this.attemptAttack(goblin);
                break;
            case 2:
                if(!usedPotion()){
                    pickCombatAction(goblin);
//                    ask for action again
                };
                break;
            case 3:
                fleeCombat();
                break;
            default:
                System.out.println("Please choose a valid number");
                pickCombatAction(goblin);
//              ask for action again
                break;
        }
    }

    void printCombatActions() {
        System.out.println("1- attack, 2- use potion, 3- flee,");

    }

    void fleeCombat() {
//        ends combat
        this.isInCombat = false;
    }


    void move(char direction) {
//        moves character by 1 unit towards 'direction'
        switch (direction) {
            case 'n':
                if (this.coordinates[1] + 1 > Land.maxYrange / 2) {
                    System.out.println("You can not go any further in this direction");
                    break;
                }
                this.coordinates[1]++;
                break;
            case 's':
                if (this.coordinates[1] - 1 < -(Land.maxYrange / 2)) {
                    System.out.println("You can not go any further in this direction");
                    break;
                }
                this.coordinates[1]--;

                break;
            case 'w':
                if (this.coordinates[0] - 1 < -(Land.maxXrange / 2)) {
                    System.out.println("You can not go any further in this direction");
                    break;
                }
                this.coordinates[0]--;

                break;
            case 'e':
                if (this.coordinates[0] + 1 > Land.maxXrange / 2) {
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


    boolean usedPotion() {
//        asks user which potion to use and uses if available
        printInventory();

        System.out.println("Choose a potion: 1- Heal,  2- Armor, 3- Ultimate, 4- Go back");
        if (Land.sc.hasNextInt()) {
            int option = Land.sc.nextInt();
            switch (option) {
                case 1:
                    if (potions.get("heal") > 0) {
                        heal();
                        potions.replace("heal", potions.get("heal") - 1);
                    } else {
                        System.out.println("Sorry you don't have any heal potions");
                        return false;
                    }
                    break;

                case 2:
                    if (potions.get("armor") > 0) {
                        increaseMaxHealth(20);
                        potions.replace("armor", potions.get("armor") - 1);
                    } else {
                        System.out.println("Sorry you don't have any armor potions");
                        return false;
                    }

                    break;
                case 3:
                    if (potions.get("ultimate") > 0) {
                        increaseMaxHealth(35);
                        heal();
                        levelUpChance();
                        potions.replace("ultimate", potions.get("ultimate") - 1);
                    } else {
                        System.out.println("Sorry you don't have any ultimate potions");
                        return false;
                    }
                    break;
                case 4:
                    return false;
                default:
                    System.out.println("Invalid option");
                    return false;
            }
        } else {
            Land.sc.next();
            System.out.println("Invalid option");
            return false;
        }
        printStats();
        return true;
    }


    private void printInventory() {
//        prints potions with amount of each potion
        System.out.println("Potions:");
        for (Map.Entry<String, Integer> potion : potions.entrySet()) {
            if (potion.getValue() != 0) {
                System.out.println(String.format("Potion: %s\tx%d", potion.getKey(), potion.getValue()));
            }
        }
    }

    private void printStats() {
//        prints name, level, health, and maxHealth
        System.out.println(this);
    }

    @Override
    public String toString() {
//        returns string representation of this object
        return String.format("Name: %s\nLevel: %s\nHealth: %s\nMax Health: %s", this.name, this.level, this.currentHealth, this.maxHealth);

    }
}
