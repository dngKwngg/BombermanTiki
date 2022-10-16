package uet.oop.bomberman.entities.Monster;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.Move;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Monster.Smart.AI;

public class Monster extends Entity {

    protected int dieScene = 1;
    protected int direction = 1;
    protected int stepLoop = 1;
    Monster() {

    }

    public Monster(int x, int y, Image img) {
        super( x, y, img);
    }

    public void run() {
        setDelayPerStep(getDelayPerStep() + 2);
    }

    @Override
    public void update() {
        if (this.getDelayPerStep() == 14) {
            Move.checkRun(this);
            this.setDelayPerStep(0);
        }
    }

    public int getMonsterX() {
        return this.x / 32;
    }

    public int getMonsterY() {
        return this.y / 32;
    }
}
