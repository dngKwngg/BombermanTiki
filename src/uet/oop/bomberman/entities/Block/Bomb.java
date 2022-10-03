package uet.oop.bomberman.entities.Block;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

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

    public Bomb(int x, int y, Image fxImage) {
        super(x, y, fxImage);
    }


    public static void plantBomb() {                // Function to plant a bomb
        if (numberBomb > 0 && isPlanted == 0) {
            numberBomb--;
            isPlanted = 1;
            timeExploding = System.currentTimeMillis();             // Get time when plant the bomb
            timeBetweenPlant = timeExploding;                       // Time between 2 bombs
            int x = player.getX() / 32;
            int y = player.getY() / 32;
            x = Math.round((float) x);                              // Get x in Canvas
            y = Math.round((float) y);                              // Get y in canvas
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());        // Create new object Bomb
            stillObjects.add(bomb);                                 // Add Bomb to list Objects
            objIdx[player.getX() / 32][player.getY() / 32] = 4;         // Set objIdx at position plant the bomb to 4
        }
    }

    public static void showBombAnimation() {
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

    @Override
    public void update() {

    }
}
