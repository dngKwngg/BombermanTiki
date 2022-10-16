package uet.oop.bomberman.entities.Items;


import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.player;

public class BombPassItem extends Items{
    public BombPassItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public BombPassItem() {

    }

    public BombPassItem(boolean isReceived) {
        super(isReceived);
    }

    @Override
    public void update() {
        for (Entity entity: stillObjects) {
            if (entity instanceof SpeedItem && (!isReceived)) {
                if (listIsKilled[entity.getX() / 32][entity.getY()/32] == 4) {
                    entity.setImg(Sprite.powerup_bombpass.getFxImage());
                }
            }
        }

        if (!isReceived) {
            if (player.getX() == this.x && player.getY() == this.y) {
                this.setImg(Sprite.grass.getFxImage());
//                this.isReceived = true;
//                player.setLimitDelay(4);
            }
        }
    }
}
