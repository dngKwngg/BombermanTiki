package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.Move;

public class Bomber extends Entity {


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }




    @Override
    public void update() {

        if (this.getDelayPerStep() == 6) {

            Move.checkRun(this);
            this.setDelayPerStep(0);
        }
    }
}
