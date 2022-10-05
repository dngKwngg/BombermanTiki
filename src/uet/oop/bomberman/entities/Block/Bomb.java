package uet.oop.bomberman.entities.Block;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.IsBlocked;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {
    protected static long timeExploding = 1;
    protected static long timeBetweenPlant = 1;

    private static Entity bomb;

    private static int state = 0;       // State of the bomb
    private static int stateExplosion = 1;          // State explosion of the bomb
    public static int numberBomb = 20;              // Number bomb limit in the map
    public static int bombPower = 1;
    public static int bombPowerLeft = 1;
    public static int bombPowerRight = 1;
    public static int bombPowerUp = 1;
    public static int bombPowerDown = 1;

    private static Entity lastEdgeUp = null;
    private static Entity lastEdgeDown = null;
    private static Entity lastEdgeLeft = null;
    private static Entity lastEdgeRight = null;

    public static int isPlanted = 0;                // Check if there's a bomb here: 0: clear, 1: have bomb, 2: explode
    public static boolean isEdge = false;           // Variable to check if the edge exist
    public static boolean isMiddle = false;         // Check if the bomb explode in the center position
    private static List<Entity> listBombMiddleVertical = new ArrayList<>();
    private static List<Entity> listBombMiddleHorizontal = new ArrayList<>();

    public Bomb(int x, int y, Image fxImage) {
        super(x, y, fxImage);
    }


    public static void plantBomb() {                // Function to plant a bomb
        if (numberBomb > 0 && isPlanted == 0) {
            int x = player.getX() / 32;
            int y = player.getY() / 32;
            x = Math.round((float) x);                              // Get x in Canvas
            y = Math.round((float) y);                              // Get y in canvas
            numberBomb--;
            isPlanted = 1;
            timeExploding = System.currentTimeMillis();             // Get time when plant the bomb
            timeBetweenPlant = timeExploding;                       // Time between 2 bombs
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());        // Create new object Bomb
            stillObjects.add(bomb);                                 // Add Bomb to list Objects
            objIdx[player.getX() / 32][player.getY() / 32] = 4;         // Set objIdx at position plant the bomb to 4
        }
    }

    public static void showBombAnimation() {            // Show animation of the bomb when it is planted till it explodes
        switch (state) {
            case 0:
                bomb.setImg(Sprite.bomb.getFxImage());
                state = 1;
                break;
            case 1:
                bomb.setImg(Sprite.bomb_1.getFxImage());
                state = 2;
                break;
            case 2:
                bomb.setImg(Sprite.bomb_2.getFxImage());
                state = 3;
                break;
            case 3:
                bomb.setImg(Sprite.bomb_1.getFxImage());
                state = 0;
                break;
        }
    }

    public static void createBlocked() {            // Create a block to prevent bomber move and the explosion of bomb
        if (IsBlocked.leftBombBlock(bomb, 0)) {
            lastEdgeLeft = new Bomb(bomb.getX() / 32 - 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            if (bombPower > 0) {
                for (int i = 1; i <= bombPower && IsBlocked.leftBombBlock(bomb, i) == true; ++i) {
                    lastEdgeLeft.setX(bomb.getX() - 32 - i * 32);
                    bombPowerLeft++;
                }
            }

            stillObjects.add(lastEdgeLeft);
        }

        if (IsBlocked.rightBombBlock(bomb, 0)) {
            lastEdgeRight = new Bomb(bomb.getX() / 32 + 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            if (bombPower > 0) {
                for (int i = 1; i <= bombPower && IsBlocked.rightBombBlock(bomb, i) == true; ++i) {
                    lastEdgeRight.setX(bomb.getX() + 32 + i * 32);
                    bombPowerRight++;
                }
            }

            stillObjects.add(lastEdgeRight);
        }

        if (IsBlocked.upBombBlock(bomb, 0)) {
            lastEdgeUp = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - 1, Sprite.bomb_exploded.getFxImage());
            if (bombPower > 0) {
                for (int i = 1; i <= bombPower && IsBlocked.upBombBlock(bomb, i) == true; ++i) {
                    lastEdgeUp.setY(bomb.getY() - 32 - i * 32);
                    bombPowerUp++;
                }
            }

            stillObjects.add(lastEdgeUp);
        }

        if (IsBlocked.downBombBlock(bomb, 0)) {
            lastEdgeDown = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + 1, Sprite.bomb_exploded.getFxImage());
            if (bombPower > 0) {
                for (int i = 1; i <= bombPower && IsBlocked.downBombBlock(bomb, i) == true; ++i) {
                    lastEdgeLeft.setX(bomb.getX() + 32 + i * 32);
                    bombPowerDown++;
                }
            }

            stillObjects.add(lastEdgeDown);
        }
    }

    public static void changeMiddle() {     // Change the bomb to explode in center position
        Entity middlePos;
        for (int i = 1; i <= bombPowerLeft; ++i) {
            middlePos = new Bomb(bomb.getX() / 32 - i, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            listBombMiddleHorizontal.add(middlePos);
        }

        for (int i = 1; i <= bombPowerRight; ++i) {
            middlePos = new Bomb(bomb.getX() / 32 + i, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            listBombMiddleHorizontal.add(middlePos);
        }

        for (int i = 1; i <= bombPowerUp; ++i) {
            middlePos = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - i, Sprite.bomb_exploded.getFxImage());
            listBombMiddleVertical.add(middlePos);
        }

        for (int i = 1; i <= bombPowerDown; ++i) {
            middlePos = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + i, Sprite.bomb_exploded.getFxImage());
            listBombMiddleVertical.add(middlePos);
        }

        for (Entity middleBomb : listBombMiddleHorizontal) {
            stillObjects.add(middleBomb);
        }

        for (Entity middleBomb : listBombMiddleVertical) {
            stillObjects.add(middleBomb);
        }
    }

    public static void bombExplosion() {
        if (stateExplosion == 1) {
            bomb.setImg(Sprite.bomb_exploded.getFxImage());
            listIsKilled[bomb.getX() / 32][bomb.getY() / 32] = 4;

            if (IsBlocked.upBombBlock(bomb, bombPowerUp)) {
                lastEdgeUp.setImg(Sprite.explosion_vertical_top_last.getFxImage());
                listIsKilled[lastEdgeUp.getX() / 32][lastEdgeUp.getY() / 32] = 4;
            }

            if (IsBlocked.downBombBlock(bomb, bombPowerDown)) {
                lastEdgeDown.setImg(Sprite.explosion_vertical_down_last.getFxImage());
                listIsKilled[lastEdgeDown.getX() / 32][lastEdgeDown.getY() / 32] = 4;
            }

            if (IsBlocked.leftBombBlock(bomb, bombPowerLeft)) {
                lastEdgeLeft.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
                listIsKilled[lastEdgeLeft.getX() / 32][lastEdgeLeft.getY() / 32] = 4;
            }

            if (IsBlocked.rightBombBlock(bomb, bombPowerRight)) {
                lastEdgeRight.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
                listIsKilled[lastEdgeRight.getX() / 32][lastEdgeRight.getY() / 32] = 4;
            }

            if (listBombMiddleVertical.size() > 0) {
                for (Entity entity : listBombMiddleVertical) {
                    entity.setImg(Sprite.explosion_vertical.getFxImage());
                    listIsKilled[entity.getX() / 32][entity.getY() / 32] = 4;
                }
            }

            if (listBombMiddleHorizontal.size() > 0) {
                for (Entity entity : listBombMiddleHorizontal) {
                    entity.setImg(Sprite.explosion_horizontal.getFxImage());
                    listIsKilled[entity.getX() / 32][entity.getY() / 32] = 4;
                }
            }

            stateExplosion = 2;
        } else if (stateExplosion == 2) {
            bomb.setImg(Sprite.bomb_exploded1.getFxImage());

            if (IsBlocked.upBombBlock(bomb, bombPowerUp)) {
                lastEdgeUp.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
            }

            if (IsBlocked.downBombBlock(bomb, bombPowerDown)) {
                lastEdgeDown.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
            }

            if (IsBlocked.leftBombBlock(bomb, bombPowerLeft)) {
                lastEdgeLeft.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
            }

            if (IsBlocked.rightBombBlock(bomb, bombPowerRight)) {
                lastEdgeRight.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
            }

            if (isMiddle) {
                for (Entity entity : listBombMiddleVertical) {
                    entity.setImg(Sprite.explosion_vertical1.getFxImage());
                }

                for (Entity entity : listBombMiddleHorizontal) {
                    entity.setImg(Sprite.explosion_horizontal1.getFxImage());
                }
            }

            stateExplosion = 3;

        } else if (stateExplosion == 3) {
            bomb.setImg(Sprite.bomb_exploded2.getFxImage());

            if (IsBlocked.upBombBlock(bomb, bombPowerUp)) {
                lastEdgeUp.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
            }

            if (IsBlocked.downBombBlock(bomb, bombPowerDown)) {
                lastEdgeDown.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
            }

            if (IsBlocked.leftBombBlock(bomb, bombPowerLeft)) {
                lastEdgeLeft.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
            }

            if (IsBlocked.rightBombBlock(bomb, bombPowerRight)) {
                lastEdgeRight.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
            }

            if (isMiddle) {
                for (Entity entity : listBombMiddleVertical) {
                    entity.setImg(Sprite.explosion_vertical2.getFxImage());
                }

                for (Entity entity : listBombMiddleHorizontal) {
                    entity.setImg(Sprite.explosion_horizontal2.getFxImage());
                }
            }

            stateExplosion = 1;
        }

    }



    @Override
    public void update() {

    }
}
