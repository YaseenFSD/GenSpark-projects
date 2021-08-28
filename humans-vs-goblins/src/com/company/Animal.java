package com.company;

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
    }

}
