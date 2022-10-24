package uet.oop.bomberman.entities.Block;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.Level.LevelNew.NewLevel;

public class Portal extends Entity {

    public static boolean onPortal = false;     // Variables use to display the portal img when player pass the level

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    public boolean canNextLevel() {
        for (int i = 0; i < enemies.size(); ++i) {
            if (enemies.get(i).getLife()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void update() {
        if (canNextLevel()) {
            this.setImg(Sprite.portal.getFxImage());
            if (player.getX() == this.getX() && player.getY() == this.getY()) {
                onPortal = true;
                _gameLevel++;
                NewLevel();
            }
        }
    }
}
