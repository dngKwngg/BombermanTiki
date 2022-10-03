package uet.oop.bomberman.entities.Items;


import javafx.scene.image.Image;

public class BombItem extends Items{
    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public BombItem() {

    }

    public BombItem(boolean isReceived) {
        super(isReceived);
    }

    @Override
    public void update() {

    }
}
