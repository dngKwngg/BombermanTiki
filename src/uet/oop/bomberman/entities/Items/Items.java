package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;


public abstract class Items extends Entity {
    protected boolean isReceived = false;

    public Items(int x, int y, Image img) {
        super(x, y, img);
    }

    public Items(boolean isReceived) {
        super();
    }

    public Items() {
        super();
    }

    public boolean isReceived() {
        return isReceived;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }

    @Override
    public void update() {

    }
}
