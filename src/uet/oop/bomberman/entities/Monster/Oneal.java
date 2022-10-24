package uet.oop.bomberman.entities.Monster;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.Move;
import uet.oop.bomberman.entities.Monster.Smart.AI;
import uet.oop.bomberman.entities.Monster.Smart.AiLevel2;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.listIsKilled;
import static uet.oop.bomberman.BombermanGame.score;

public class Oneal extends Monster {
    public int scoreOfThis = 100;
    private int direction;
    protected AI ai;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        ai = new AiLevel2(this);
    }

    public void Die() {
        if (!this.getLife()) {
            if (dieScene == 1) {
                this.setImg(Sprite.oneal_dead.getFxImage());
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
                score += scoreOfThis;
                scoreOfThis = 0;
            }
        }
    }

    private void dieByFlameFromBomb() {
        int x = this.getX() / 32;
        int y = this.getY() / 32;
        if (listIsKilled[x][y] == 4) {
            this.life = false;
        }
    }

    public void randomDirection() {
        direction = ai.calculateDirection();
        moveWithNumber(direction);
    }

    void moveWithNumber(int number) {
        switch (number) {
            case 0:
                Move.left(this);
                break;
            case 1:
                Move.right(this);
                break;
            case 2:
                Move.down(this);
                break;
            case 3:
                Move.up(this);
                break;
        }
    }

    public void run() {
        setDelayPerStep(getDelayPerStep() + 1);
    }

    @Override
    public void update() {
        dieByFlameFromBomb();
        if (this.getLife()) {
            randomDirection();
        }
        if (this.getDelayPerStep() == 8) {
            Die();
            if (this.getLife()) {
                Move.checkRun(this);
                stepLoop++;
            }
            this.setDelayPerStep(0);
        }
    }
}


