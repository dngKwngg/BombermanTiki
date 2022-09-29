package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;

public class SpeedItem extends Items{
    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public SpeedItem() {

    }

    public SpeedItem(boolean isReceived) {
        super(isReceived);
    }
}
