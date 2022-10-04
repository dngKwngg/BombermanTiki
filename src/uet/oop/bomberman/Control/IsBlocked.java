package uet.oop.bomberman.Control;

import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.objIdx;

public class IsBlocked {

    public static boolean blockLeft(Entity entity) {            // Check if player, animals can go left through the block
        return objIdx[entity.getX()/ 32 - 1][entity.getY() / 32] == 0;
    }

    public static boolean blockRight(Entity entity) {           // Check if player, animals can go right through the block
        return objIdx[entity.getX() / 32 + 1][entity.getY() / 32] == 0;
    }

    public static boolean blockUp(Entity entity) {              // Check if player, animals can go up through the block
        return objIdx[entity.getX() / 32][entity.getY() / 32 - 1] == 0;
    }

    public static boolean blockDown(Entity entity) {            // Check if player, animals can go down through the block
        return objIdx[entity.getX() / 32][entity.getY() / 32 + 1] == 0;
    }

}
