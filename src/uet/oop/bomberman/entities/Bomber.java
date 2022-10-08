package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.Move;

public class Bomber extends Entity {


    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }


    public void run() {
        setDelayPerStep(getDelayPerStep() + 1);
    }
    @Override
    public void update() {
<<<<<<< Updated upstream
        if (this.getDelayPerStep() == 5) {
=======
        if (this.getRunOneKey() == 6) {
>>>>>>> Stashed changes
            Move.checkRun(this);
            this.setDelayPerStep(0);
        }
    }
}
