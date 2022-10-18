package uet.oop.bomberman.Level;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import uet.oop.bomberman.entities.Block.Portal;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Monster.*;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Block.Bomb.bombPower;
import static uet.oop.bomberman.graphics.Sound.isSoundDie;

//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;

public class Level1 {
    public Level1() {
        entities.clear();
        stillObjects.clear();
        bombPower = 0;
        isSoundDie = false;

        new CreateMap("res/levels/Level1.txt");
        player = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(player);
        enemies.add(new Minvo(7, 3, Sprite.minvo_left1.getFxImage()));
        enemies.add(new Oneal(9, 3, Sprite.oneal_left1.getFxImage()));
        enemies.add(new Doll(21, 6, Sprite.doll_left1.getFxImage()));
        enemies.add(new Kondoria(3, 1, Sprite.kondoria_left1.getFxImage()));
        enemies.add(new Ballom(7, 1, Sprite.balloom_left1.getFxImage()));
        stillObjects.add(new Portal(23, 13, Sprite.transparent.getFxImage()));


    }
}
