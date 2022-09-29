package uet.oop.bomberman.entities.Items;


import javafx.scene.image.Image;

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


}
