package uet.oop.bomberman.entities.Items;


import com.sun.xml.internal.ws.api.ha.StickyFeature;
import javafx.scene.image.Image;
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
                if (listIsKilled[entity.getX() / 32][entity.getY()/32] == 4) {          // 4 có thể là 1 giá trị riêng với Items
                    entity.setImg(Sprite.powerup_flames.getFxImage());
                }
            }
        }

        if (!this.isReceived) {
            if (player.getX() == this.x && player.getY() == this.y) {
                this.setImg(Sprite.grass.getFxImage());
                this.isReceived = true;
//                Bomb.bombPower += 2;
            }
        }
    }
}
