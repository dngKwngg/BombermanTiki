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

    public static boolean leftBombBlock(Entity entity, int power) {        // Limit the range and animation of the explosion to the left
        return objIdx[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 0
                || objIdx[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 3
                || objIdx[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 6
                || objIdx[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 7;
//                || objIdx[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 8;
    }

    public static boolean rightBombBlock(Entity entity, int power) {        // Limit the range and animation of the explosion to the right
        return objIdx[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 0
                || objIdx[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 3
                || objIdx[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 6
                || objIdx[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 7;
//                || objIdx[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 8;
    }

    public static boolean upBombBlock(Entity entity, int power) {       // Limit the range and animation of the explosion to the up
        return objIdx[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 0
                || objIdx[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 3
                || objIdx[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 6
                || objIdx[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 7;
//                || objIdx[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 8;
    }
    public static boolean downBombBlock(Entity entity, int power) {     // Limit the range and animation of the explosion to the down
        return objIdx[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 0
                || objIdx[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 3
                || objIdx[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 6
                || objIdx[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 7;
//                || objIdx[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 8;
    }

}
