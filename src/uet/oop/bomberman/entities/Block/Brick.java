package uet.oop.bomberman.entities.Block;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.Move;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Brick extends Entity {
    public int dieScene = 1;

    public int scoreOfThis = 10;

    public void Exploding() {
        if (!this.getLife()) {
            if (dieScene == 1) {
                this.setImg(Sprite.brick_exploded.getFxImage());
                dieScene = 2;
            } else if (dieScene == 2) {
                this.setImg(Sprite.brick_exploded1.getFxImage());
                dieScene = 3;
            } else if (dieScene == 3) {
                this.setImg(Sprite.brick_exploded2.getFxImage());
                dieScene = 4;
            } else {
                this.setImg(Sprite.grass.getFxImage());
                score+=scoreOfThis;
                scoreOfThis=0;
            }
        }
    }

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
        if (listIsKilled[this.getX() / 32][this.getY() / 32] == 4) {
            this.setLife(false);
        }
    }


    @Override
    public void update() {
        checkBrickHidden();
        if (this.getDelayPerStep() == 8) {
            Exploding();
            this.setDelayPerStep(0);
        }
    }
}
