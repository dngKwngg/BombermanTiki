package uet.oop.bomberman.entities.Monster;
import javafx.scene.image.Image;
import uet.oop.bomberman.Control.Move;
import uet.oop.bomberman.entities.Monster.Smart.AI;
import uet.oop.bomberman.entities.Monster.Smart.AiLevel1;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.listIsKilled;

public class Kondoria extends Monster {

//    public int dieScene=1;
    private int direction;
    protected AI ai;

//    private int stepLoop = 0;

    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
        ai = new AiLevel1();
    }

    public void Die() {
        if(!this.getLife()) {
            if (dieScene == 1) {
                this.setImg(Sprite.kondoria_dead.getFxImage());
                dieScene = 2;
            } else if (dieScene == 2) {
                this.setImg(Sprite.mob_dead1.getFxImage());
                dieScene = 3;
            } else if (dieScene == 3) {
                this.setImg(Sprite.mob_dead2.getFxImage());
                dieScene = 4;
            } else if (dieScene == 4) {
                this.setImg(Sprite.mob_dead3.getFxImage());
                dieScene = 5;
            } else if (dieScene == 5) {
                this.setImg(Sprite.transparent.getFxImage());
            }
        }
    }

    private void dieByFlameFromBomb() {
        int x = this.getX()/32;
        int y = this.getY()/32;
        if(listIsKilled[x][y]==4) {
            this.life = false;
        }
    }

    public void randomDirection() {
        if (standing == 0 && stepLoop != 5) {
            moveWithNumber(direction);

        } else {
//            Random random = new Random();
//            direction = random.nextInt(4);
            direction = ai.calculateDirection();
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
        dieByFlameFromBomb();
        if(this.getLife()) {
            randomDirection();
        }
        if (this.getDelayPerStep() == 12) {
            Die();
            if(this.getLife()) {
                Move.checkRun(this);
                stepLoop++;
            }
            this.setDelayPerStep(0);
        }
    }
}
