package com.company;

import java.util.*;

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
        while (!player.isDead) {
            player.pickWorldAction();

            Goblin collidedGoblin = playerCollidesGoblin(player);
            if (collidedGoblin != null) {
                combat(player, collidedGoblin);
            }
        }
        System.out.println("Message from Goblins: GG humans!");
    }


    void combat(Human player, Goblin goblin) {
        player.isInCombat = true;
        while (player.isInCombat){
            player.pickCombatAction(goblin);
            if (!player.isInCombat){
//                if player flees
                break;
            }
            if (goblin.isDead){
//                chance drop potion
                ArrayList<String> droppedPotions = goblin.dropPotions();
                droppedPotions.forEach(p -> {
                    System.out.println("You've received a " + p + " potion");
                    player.potions.computeIfPresent(p, (key, val) -> val + 1);
                });
                player.levelUpChance();
                player.isInCombat = false;
                break;
            }

            goblin.attemptAttack(player);
            if(player.isDead){
                player.isInCombat = false;
                break;
            }
            System.out.println(String.format("Your health: %d/%d\n%s's health: %d/%d", player.currentHealth, player.maxHealth, goblin.name, goblin.currentHealth, goblin.maxHealth));
        }
//        spawn a new goblin
        goblins.removeIf(g -> g == goblin);
        int randomGoblinLvl = new Random().nextInt(player.level) + 1;
        spawnGoblin(randomValidSpawnLocation(player), randomGoblinLvl);
    }

    Goblin playerCollidesGoblin(Human player) {
        for (Goblin goblin: goblins) {
            if (Arrays.equals(goblin.coordinates, player.coordinates)) {
                System.out.println("Oh no you have collided with a goblin!");
                System.out.println(goblin);
                return goblin;
            }
        }
        return null;
    }


    void spawnWorld(Human player) {
//        spawns human at 0,0 and spawns 40 goblins randomly
        player.coordinates = new int[]{0, 0};
        for (int i = 0; i < 40; i++) {
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
