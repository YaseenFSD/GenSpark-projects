package com.company;

import java.util.Random;

public class Animal {
    boolean isDead = false;
    int maxHealth = 100;
    int currentHealth = 100;
    String name;
    int level = 1;
    int[] coordinates = new int[2];
    void die() {
        this.isDead = true;
    }

    void attemptAttack(Animal opponent) {
//        50% chance of landing an attack. if attack is successful decrease opponent current health by (randomInt 10-20 * level)
        boolean isHumanAttacking = this instanceof Human;
        int randInt = new Random().nextInt(100);
        if (randInt < 50) {
            int randDamage = this.level * (new Random().nextInt(11) + 10);
            opponent.currentHealth -= randDamage;
            System.out.println(String.format("%s has inflicted %d damage on %s", this.name, randDamage, opponent.name));
            if (opponent.currentHealth < 1){
                opponent.die();
                System.out.println(String.format("RIP %s", opponent.name));
                return;
            }
        } else if (isHumanAttacking) {
            System.out.println("Oh no your opponent has dodged your attack");
        } else {
            System.out.println(String.format("Good dodge %s!", opponent.name));
        }

    }

}
