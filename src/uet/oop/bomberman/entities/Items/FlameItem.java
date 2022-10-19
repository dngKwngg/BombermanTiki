package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Block.Bomb;
import uet.oop.bomberman.entities.Block.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class FlameItem extends Items {
    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public FlameItem() {
        super();
    }

    public FlameItem(boolean isReceived) {
        super(isReceived);
    }

    @Override
    public void update() {
        for (Entity entity: stillObjects) {
            if (entity instanceof FlameItem && (!this.isReceived)) {
                if (listIsKilled[entity.getX() / 32][entity.getY()/32] == 4) {
                    entity.setLife(false);
                    entity.setImg(Sprite.powerup_flames.getFxImage());
                }
            }
        }

        if (!this.isReceived) {
            if (player.getX() == this.x && player.getY() == this.y) {
                if(!this.getLife()) {
                        this.setImg(Sprite.grass.getFxImage());
                        this.isReceived = true;
                        Bomb.bombPower += 1;
                    }
            }
        }
    }
}
