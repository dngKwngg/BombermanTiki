package uet.oop.bomberman.entities.Block;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

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


    // Function to check if brick is hidden (grass instead of brick)
    public void checkBrickHidden() {
        for (Entity entity : stillObjects) {
            if (entity instanceof Brick) {
                // If listIsKilled value == 4, brick and grass will appear
                if (listIsKilled[entity.getX() / 32][entity.getY() / 32] == 4) {
                    entity.setImg(Sprite.grass.getFxImage());
                }
            }
        }
    }

    @Override

    public void update() {
        checkBrickHidden();
    }
}
