package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Control.Move;
import uet.oop.bomberman.entities.Monster.Monster;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.enemies;
import static uet.oop.bomberman.BombermanGame.listIsKilled;

public class Bomber extends Entity {

    public boolean canPass = false;

    public int dieScene=1;
    public int limitDelay = 6;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setCanPass(boolean canPass) {
        this.canPass = canPass;
    }

    public boolean isCanPass() {
        return canPass;
    }

    public void setLimitDelay(int limitDelay) {
        this.limitDelay = limitDelay;
    }

    public int getLimitDelay() {
        return limitDelay;
    }

    public void Die() {
        if(!this.getLife()) {
            if (dieScene == 1) {
                this.setImg(Sprite.player_dead1.getFxImage());
                dieScene = 2;
            } else if (dieScene == 2) {
                this.setImg(Sprite.player_dead2.getFxImage());
                dieScene = 3;
            } else if (dieScene == 3) {
                this.setImg(Sprite.player_dead3.getFxImage());
                dieScene = 4;
            } else {
                this.setImg(Sprite.transparent.getFxImage());
            }
        }
    }

    private void dieByMonster() {
        int x = this.getX();
        int y = this.getY();
        for (Monster monster : enemies) {
            int monsterPositionX = monster.getX();
            int monsterPositionY = monster.getY();
            if (
                    x == monsterPositionX && monsterPositionY - 32 < y && monsterPositionY + 32 > y
                            || y == monsterPositionY && monsterPositionX - 32 < x && monsterPositionX + 32 > x
            ) {
                if(monster.getLife()) {
                    this.life = false;
                    break;
                }
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

    @Override
    public void update() {
        dieByMonster();
        dieByFlameFromBomb();
        if (this.getDelayPerStep() == this.getLimitDelay() ) {
            Die();
            Move.checkRun(this);
            this.setDelayPerStep(0);
        }
    }
}
