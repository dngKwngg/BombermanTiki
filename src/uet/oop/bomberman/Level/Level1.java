package uet.oop.bomberman.Level;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.entities.Block.Portal;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Monster.*;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Block.Bomb.*;
import static uet.oop.bomberman.graphics.Sound.updateSound;
import static uet.oop.bomberman.graphics.Sound.*;


public class Level1 {
    public Level1() {
        isPause = false;
        entities.clear();
        enemies.clear();
        stillObjects.clear();
        new CreateMap("res/levels/Level1.txt");
        Image image1 = new Image("img/Pause.png");
        View = new ImageView(image1);
        View.setX(0);
        score = 0;
        stateExplosion = 1;
        isPlanted = 0;
        bombPower = 0;
        bombPowerLeft = 0;
        bombPowerRight = 0;
        bombPowerUp = 0;
        bombPowerDown = 0;
        lastEdgeUp = null;
        lastEdgeDown = null;
        lastEdgeLeft = null;
        lastEdgeRight = null;
        isEdge = false;
        isMiddle = false;
        listBombMiddleVertical.clear();
        listBombMiddleHorizontal.clear();
        isSoundDie = false;
        isSoundScreen = false;
        player = new Bomber(1, 1, Sprite.player_right.getFxImage());
        player.setLife(true);
        entities.add(player);
        enemies.add(new Ballom(9, 3, Sprite.balloom_left1.getFxImage()));
//        enemies.add(new Ballom(3, 3, Sprite.balloom_left1.getFxImage()));
        //enemies.add(new Doll(21, 6, Sprite.doll_left1.getFxImage()));
        // enemies.add(new Kondoria(3, 1, Sprite.kondoria_left1.getFxImage()));
//        enemies.add(new Ballom(7, 1, Sprite.balloom_left1.getFxImage()));
//        enemies.add(new Ballom(7, 1, Sprite.balloom_left1.getFxImage()));
        stillObjects.add(new Portal(23, 13, Sprite.transparent.getFxImage()));
        updateSound();
    }
}
