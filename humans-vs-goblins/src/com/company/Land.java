package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Land {
    public static Scanner sc = new Scanner(System.in);
    private static ArrayList<Goblin> goblins = new ArrayList();
//    Anywhere between -10 and 10 is a valid spot for a range of 20
    public static int maxXrange = 20;
    public static int maxYrange = 20;

    void startGame() {
//        starts game and doesn't finish until Human is dead
        Human player = new Human();
        spawnWorld(player);
        while(!player.isDead){
            player.pickWorldAction();
        }
    }

//    boolean playerCollidesGoblin(Human player){}


    void spawnWorld(Human player) {
//        spawns human at 0,0 and spawns 10 goblins randomly
        player.coordinates = new int[]{0, 0};
        for (int i = 0; i < 10; i++) {
            spawnGoblin(randomValidSpawnLocation(player));
        }

    }

    void spawnGoblin(int[] coordinates, int level) {
//        spawn a new goblin at 'coordinates' with a level
        Goblin goblin = new Goblin(level);
        goblin.coordinates = coordinates;
        goblins.add(goblin);
    }

    void spawnGoblin(int[] coordinates) {
//        spawn a new goblin at 'coordinates' with a level of 1
        spawnGoblin(coordinates, 1);
    }

    int[] randomValidSpawnLocation(Human player) {
//        returns random coordinates that is not out of bounds and also not the same as the Human's current location
//        and also not same spot as another goblin

        int[] rand = new int[2];
        boolean isOccupied;
        do {
            rand[0] = new Random().nextInt(maxXrange + 1) - maxXrange / 2;
            rand[1] = new Random().nextInt(maxYrange + 1) - maxYrange / 2;
            isOccupied = goblins.stream().anyMatch(goblin -> Arrays.equals(goblin.coordinates, rand));
        } while (Arrays.equals(player.coordinates, rand) && !isOccupied);

        return rand;
    }

}
