package uet.oop.bomberman.entities.Block;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    protected static long timeExploding = 1;
    protected static long timeBetweenPlant = 1;

    private static Entity bomb;

    private static int state = 0;       // State of the bomb
    private static int stateExplosion = 1;          // State explosion of the bomb
    public static int bombPower = 0;
    public static int bombPowerLeft = 0;
    public static int bombPowerRight = 0;
    public static int bombPowerUp = 0;
    public static int bombPowerDown = 0;

    private static Entity lastEdgeUp = null;
    private static Entity lastEdgeDown = null;
    private static Entity lastEdgeLeft = null;
    private static Entity lastEdgeRight = null;

    public static int isPlanted = 0;                // Check if there's a bomb here: 0: clear, 1: have bomb, 2: explode


    @Override
    public void update() {

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
}
