package uet.oop.bomberman.entities.Block;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame._gameLevel;
import static uet.oop.bomberman.BombermanGame.player;

public class Portal extends Entity {

    public static boolean onPortal = false;     // Variables use to display the portal img when player pass the level
    public static boolean isEndGame = false;

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (isEndGame) {
            this.setImg(Sprite.portal.getFxImage());
            if (player.getX() == this.getX() && player.getY() == this.getY()) {
                onPortal = true;
                _gameLevel ++;
            }
        }

    }
}
