package uet.oop.bomberman.entities.Block;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.block;
import static uet.oop.bomberman.BombermanGame.stillObjects;

public class Brick extends Entity {

    /**
     * Create constructor.
     *
     * @param x
     * @param y
     * @param img
     */
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }


    public void checkBrickHidden() {
        for (Entity entity : stillObjects) {
            if (entity instanceof Brick) {

            }
        }
    }

    @Override

    public void update() {

    }
}
