package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.Move;

public class Bomber extends Entity {
    public int limitDelay=6;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setLimitDelay(int limitDelay) {
        this.limitDelay = limitDelay;
    }

    public int getLimitDelay() {
        return limitDelay;
    }

    @Override
    public void update() {
        if (this.getDelayPerStep() == this.getLimitDelay()) {
            Move.checkRun(this);
            this.setDelayPerStep(0);
        }
    }
}
