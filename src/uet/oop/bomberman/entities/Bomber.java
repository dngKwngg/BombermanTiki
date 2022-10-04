package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Control.Move;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {

    private int isLife=1;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public int isLife(){
        return isLife;
    }

    public void run() {
        setRunOneKey(getRunOneKey() + 1);
    }
    @Override
    public void update() {
        if (this.getRunOneKey() == 4) {
            Move.checkRun(this);
            this.setRunOneKey(0);
        }
    }
}
