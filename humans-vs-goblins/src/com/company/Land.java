package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Land {
    public static Scanner sc = new Scanner(System.in);
    int maxXrange = 20;
    int maxYrange = 20;

    void startGame(){
//        starts game and doesn't finish until Human is dead
        Human player = new Human();
//        System.out.println(player.name);

    }


    void spawnWorld(){
//        spawns human at 0,0 and spawns 10 goblins randomly

    }
    void spawnGoblin(int[] coordinates, int level) {
//        spawn a new goblin at 'coordinates' with a level
    }

    int[] randomValidSpawnLocation(Human player) {
//        returns random coordinates that is not out of bounds and also not the same as the Human's current location
        int[] rand = new int[2];

        do {
        rand[0] = new Random().nextInt(maxXrange + 1) - maxXrange / 2;
        rand[1] = new Random().nextInt(maxYrange + 1) - maxYrange / 2;
        } while (Arrays.equals(player.coordinates, rand));

        return rand;
    }

}
