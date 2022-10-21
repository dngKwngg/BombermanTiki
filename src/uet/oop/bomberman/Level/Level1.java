package uet.oop.bomberman.Level;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.entities.Block.Portal;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Monster.*;
//import static uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.Sound;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Block.Bomb.*;
import static uet.oop.bomberman.graphics.Sound.updateSound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Level1 {
    public Level1() {
        entities.clear();
        stillObjects.clear();
        // if (g_mediaPlayer != null) g_mediaPlayer.stop();
        new CreateMap("res/levels/Level1.txt");
        //timeNumber = 120;
        Image image1 = new Image("img/meme1.png");
        View = new ImageView(image1);
        View.setX(250);
//        bombRadius = 1;
//        bombBank = 1;
//        speedItem = 0;
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
        player = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(player);
        //enemies.add(new Minvo(7, 3, Sprite.minvo_left1.getFxImage()));
        enemies.add(new Oneal(9, 3, Sprite.oneal_left1.getFxImage()));
        //enemies.add(new Doll(21, 6, Sprite.doll_left1.getFxImage()));
        // enemies.add(new Kondoria(3, 1, Sprite.kondoria_left1.getFxImage()));
//        enemies.add(new Ballom(7, 1, Sprite.balloom_left1.getFxImage()));
//        enemies.add(new Ballom(7, 1, Sprite.balloom_left1.getFxImage()));
        stillObjects.add(new Portal(23, 13, Sprite.transparent.getFxImage()));
        updateSound();
    }
}
