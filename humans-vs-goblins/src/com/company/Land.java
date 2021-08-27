package com.company;

public class Land {
    int maxX = 20;
    int maxY = 20;

    void startGame(){
//        starts game and doesn't finish until Human is dead
    }

    void spawnWorld(){
//        spawns human at 0,0 and spawns 10 goblins randomly

    }
    void spawnGoblin(int[] coordinates, int level) {
//        spawn a new goblin at 'coordinates' with a level
    }

    int[] randomValidSpawnLocation() {
//        returns random coordinates that is not out of bounds and also not the same as the Human's current location
        return new int[] {0, 0};
    }

}
