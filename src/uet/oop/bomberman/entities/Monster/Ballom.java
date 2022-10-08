package uet.oop.bomberman.entities.Monster;

import uet.oop.bomberman.Control.IsBlocked.*;
import javafx.scene.image.Image;
import uet.oop.bomberman.Control.Move;

import java.util.Random;

public class Ballom extends Monster {
    private int direction;

    private int stepLoop = 0;

    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }

    public void randomDirection() {
        if (standing == 0 && stepLoop!=5) {
            moveWithNumber(direction);

        } else {
            Random random = new Random();
            direction = random.nextInt(4);
            moveWithNumber(direction);
        }
    }

    void moveWithNumber(int number) {
        switch (number) {
            case 0:
                Move.down(this);
                break;
            case 1:
                Move.up(this);
                break;
            case 2:
                Move.left(this);
                break;
            case 3:
                Move.right(this);
                break;
        }
    }

    public void run() {
        setDelayPerStep(getDelayPerStep() + 1);
    }

    @Override
    public void update() {
        randomDirection();
        if (this.getDelayPerStep() == 8) {
            Move.checkRun(this);
            this.setDelayPerStep(0);
            stepLoop++;
        }
    }
}
