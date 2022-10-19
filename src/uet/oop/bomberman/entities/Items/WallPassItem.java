package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Block.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.player;

public class WallPassItem extends Items {
    public static int speed = 1;

    public WallPassItem(int x, int y, Image img) {
        super(x, y, img);
    }


    @Override
    public void update() {
        for (Entity entity : stillObjects) {
            if (entity instanceof WallPassItem && (!isReceived)) {
                if (listIsKilled[entity.getX() / 32][entity.getY() / 32] == 4) {
                    entity.setLife(false);
                    entity.setImg(Sprite.powerup_wallpass.getFxImage());
                }
            }
        }

        if (!isReceived) {
            if (player.getX() == this.x && player.getY() == this.y) {
                if (!this.getLife()) {
                    this.setImg(Sprite.grass.getFxImage());
                    this.isReceived = true;
                    player.setCanPass(true);
                }
            }
        }
    }
}
