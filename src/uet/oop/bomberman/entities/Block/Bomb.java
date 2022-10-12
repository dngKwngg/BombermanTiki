package uet.oop.bomberman.entities.Block;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.IsBlocked;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {
    protected static long timeBomb = 1;         // Exploding time bomb
    protected static long timeBombTemp = 1;     // TimeBombTemp to handle between 2 bombs

    private static Entity bomb;

    private static int state = 1;       // State of the bomb
    private static int stateExplosion = 1;          // State explosion of the bomb
    public static int numberBomb = 20;              // Number bomb limit in the map
    public static int bombPower = 0;                // Bomb power
    public static int bombPowerLeft = 0;            // Bomb power in left side
    public static int bombPowerRight = 0;           // Bomb power in right side
    public static int bombPowerUp = 0;              // Bomb power in up side
    public static int bombPowerDown = 0;            // Bomb power in down side

    private static Entity lastEdgeUp = null;        // The upper edge of the block blocks bomber going through
    private static Entity lastEdgeDown = null;      // The down edge of the block blocks bomber going through
    private static Entity lastEdgeLeft = null;      // The left edge of the block blocks bomber going through
    private static Entity lastEdgeRight = null;     // The right edge of the block blocks bomber going through

    public static int isPlanted = 0;                // Check if there's a bomb here: 0: clear, 1: have bomb, 2: explode
    public static boolean isEdge = false;           // Variable to check if the edge exist
    public static boolean isMiddle = false;         // Check if the bomb explode in the center position
    private static final long timeBetweenPlantAndExplode = 2000L;     // Set time between plant anh explode is 2 seconds
    private static final long timeBetweenPlantAndAnimation = 100L; // Set time between plant and get bomb animation is 0.1 second
    private static final long timeExploding = 1000L;                  // Set time exploding the bomb is 1 second
    private static final List<Entity> listBombMiddleVertical = new ArrayList<>();
    private static final List<Entity> listBombMiddleHorizontal = new ArrayList<>();

    public Bomb(int x, int y, Image fxImage) {
        super(x, y, fxImage);
    }


    public static void plantBomb() {                // Function to plant a bomb
        if (numberBomb > 0 && isPlanted == 0 && player.getLife()) {
            int x = player.getX() / 32;
            int y = player.getY() / 32;
            x = Math.round((float) x);                              // Get x in Canvas
            y = Math.round((float) y);                              // Get y in canvas
            numberBomb--;
            isPlanted = 1;
            timeBomb = System.currentTimeMillis();             // Get time when plant the bomb
            timeBombTemp = timeBomb;                       // Time between 2 bombs
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());        // Create new object Bomb
            stillObjects.add(bomb);                                 // Add Bomb to list Objects
            objIdx[player.getX() / 32][player.getY() / 32] = 4;         // Set objIdx at position plant the bomb to 4
        }
    }

    public static void showBombAnimation() {            // Show animation of the bomb when it is planted till it explodes
        if (state == 1) {
            bomb.setImg(Sprite.bomb.getFxImage());
            state = 2;
        } else if (state == 2) {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            state = 3;
        } else if (state == 3) {
            bomb.setImg(Sprite.bomb_2.getFxImage());
            state = 4;
        } else {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            state = 1;
        }
    }

    public static void createBlocked() {        // Create a block to prevent bomber move and the explosion of bomb
        int i;
        if (IsBlocked.leftBombBlock(bomb, 0)) {
            lastEdgeLeft = new Bomb(bomb.getX() / 32 - 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            if (bombPower > 0) {
                for (i = 1; i <= bombPower && IsBlocked.leftBombBlock(bomb, i); ++i) {
                    lastEdgeLeft.setX(bomb.getX() - 32 - i * 32);
                    bombPowerLeft++;
                }
            }

            stillObjects.add(lastEdgeLeft);
        }

        if (IsBlocked.rightBombBlock(bomb, 0)) {
            lastEdgeRight = new Bomb(bomb.getX() / 32 + 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            if (bombPower > 0) {
                for (i = 1; i <= bombPower && IsBlocked.rightBombBlock(bomb, i); ++i) {
                    lastEdgeRight.setX(bomb.getX() + 32 + i * 32);
                    bombPowerRight++;
                }
            }

            stillObjects.add(lastEdgeRight);
        }

        if (IsBlocked.upBombBlock(bomb, 0)) {
            lastEdgeUp = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - 1, Sprite.bomb_exploded.getFxImage());
            if (bombPower > 0) {
                for (i = 1; i <= bombPower && IsBlocked.upBombBlock(bomb, i); ++i) {
                    lastEdgeUp.setY(bomb.getY() - 32 - i * 32);
                    bombPowerUp++;
                }
            }

            stillObjects.add(lastEdgeUp);
        }

        if (IsBlocked.downBombBlock(bomb, 0)) {
            lastEdgeDown = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + 1, Sprite.bomb_exploded.getFxImage());
            if (bombPower > 0) {
                for (i = 1; i <= bombPower && IsBlocked.downBombBlock(bomb, i); ++i) {
                    lastEdgeDown.setY(bomb.getY() + 32 + i * 32);
                    bombPowerDown++;
                }
            }

            stillObjects.add(lastEdgeDown);
        }
    }

    public static void changeMiddle() {     // Change the bomb to explode in center position
        Entity middlePos;
        int i;
        for (i = 1; i <= bombPowerLeft; ++i) {
            middlePos = new Bomb(bomb.getX() / 32 - i, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            listBombMiddleHorizontal.add(middlePos);
        }

        for (i = 1; i <= bombPowerRight; ++i) {
            middlePos = new Bomb(bomb.getX() / 32 + i, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            listBombMiddleHorizontal.add(middlePos);
        }

        for (i = 1; i <= bombPowerUp; ++i) {
            middlePos = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - i, Sprite.bomb_exploded.getFxImage());
            listBombMiddleVertical.add(middlePos);
        }

        for (i = 1; i <= bombPowerDown; ++i) {
            middlePos = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + i, Sprite.bomb_exploded.getFxImage());
            listBombMiddleVertical.add(middlePos);
        }

        stillObjects.addAll(listBombMiddleHorizontal);
        stillObjects.addAll(listBombMiddleVertical);
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

    private static void checkBombActive() {         // Function to handle the bomb when it is planted and is not explode
        if (isPlanted == 1) {
            if (System.currentTimeMillis() - timeBomb < 2000L) {
                if (System.currentTimeMillis() - timeBombTemp > 100L) {
                    showBombAnimation();
                    timeBombTemp += 100L;
                }
            } else {
                isPlanted = 2;
                timeBomb = System.currentTimeMillis();
                timeBombTemp = timeBomb;
            }
        }
    }

    private static void checkExplosion() {          // Handle the explosion of bomb after planted
        if (isPlanted == 2) {
            if (System.currentTimeMillis() - timeBomb < 1000L) {
                if (System.currentTimeMillis() - timeBombTemp > 100L) {
                    if (!isEdge) {
                        createBlocked();
                        isEdge = true;
                    }

                    if (bombPower > 0 && !isMiddle) {
                        changeMiddle();
                        isMiddle = true;
                    }

                    bombExplosion();
                    timeBombTemp += 100L;
                }
            } else {
                isPlanted = 0;
                objIdx[bomb.getX() / 32][bomb.getY() / 32] = 0;
                listIsKilled[bomb.getX() / 32][bomb.getY() / 32] = 0;
                bomb.setImg(Sprite.transparent.getFxImage());

                if (IsBlocked.downBombBlock(bomb, bombPowerDown)) {
                    lastEdgeDown.setImg(Sprite.transparent.getFxImage());
                    objIdx[lastEdgeDown.getX() / 32][lastEdgeDown.getY() / 32] = 0;
                    listIsKilled[lastEdgeDown.getX() / 32][lastEdgeDown.getY() / 32] = 0;
                }

                if (IsBlocked.upBombBlock(bomb, bombPowerUp)) {
                    lastEdgeUp.setImg(Sprite.transparent.getFxImage());
                    objIdx[lastEdgeUp.getX() / 32][lastEdgeUp.getY() / 32] = 0;
                    listIsKilled[lastEdgeUp.getX() / 32][lastEdgeUp.getY() / 32] = 0;
                }

                if (IsBlocked.leftBombBlock(bomb, bombPowerLeft)) {
                    lastEdgeLeft.setImg(Sprite.transparent.getFxImage());
                    objIdx[lastEdgeLeft.getX() / 32][lastEdgeLeft.getY() / 32] = 0;
                    listIsKilled[lastEdgeLeft.getX() / 32][lastEdgeLeft.getY() / 32] = 0;
                }

                if (IsBlocked.rightBombBlock(bomb, bombPowerRight)) {
                    lastEdgeRight.setImg(Sprite.transparent.getFxImage());
                    objIdx[lastEdgeRight.getX() / 32][lastEdgeRight.getY() / 32] = 0;
                    listIsKilled[lastEdgeRight.getX() / 32][lastEdgeRight.getY() / 32] = 0;
                }

                if (isMiddle) {
                    for (Entity e : listBombMiddleVertical) {
                        objIdx[e.getX() / 32][e.getY() / 32] = 0;
                        listIsKilled[e.getX() / 32][e.getY() / 32] = 0;
                    }

                    for (Entity e : listBombMiddleHorizontal) {
                        objIdx[e.getX() / 32][e.getY() / 32] = 0;
                        listIsKilled[e.getX() / 32][e.getY() / 32] = 0;
                    }
                }

                stillObjects.removeAll(listBombMiddleVertical);
                stillObjects.removeAll(listBombMiddleHorizontal);
                listBombMiddleHorizontal.clear();
                listBombMiddleVertical.clear();
                bombPowerUp = 0;
                bombPowerDown = 0;
                bombPowerLeft = 0;
                bombPowerRight = 0;
                isEdge = false;
                isMiddle = false;
            }
        }
    }

    @Override
    public void update() {
        checkBombActive();
        checkExplosion();
    }
}
