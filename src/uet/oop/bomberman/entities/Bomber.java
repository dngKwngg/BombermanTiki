package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.Move;

public class Bomber extends Entity {


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }


    public void run() {
        setDelayPerStep(getDelayPerStep() + 1);
    }

    @Override
    public void update() {

        if (this.getDelayPerStep() == 5) {

            Move.checkRun(this);
            this.setDelayPerStep(0);
        }
    }
}
