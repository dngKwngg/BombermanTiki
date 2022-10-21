package uet.oop.bomberman.Level;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.entities.Block.Portal;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Monster.*;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.Sound;
import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.graphics.Sound.updateSound;

public class Level3 {
        public Level3() {
            entities.clear();
            stillObjects.clear();
            new CreateMap("res/levels/Level3.txt");
            Image image1 = new Image("img/meme1.png");
            View = new ImageView(image1);
            View.setX(250);
            player.setX(32);
            player.setY(32);
            entities.add(player);
            enemies.add(new Doll(21, 6, Sprite.doll_left1.getFxImage()));
            enemies.add(new Ballom(21, 6, Sprite.balloom_left1.getFxImage()));
            enemies.add(new Ballom(21, 6, Sprite.balloom_left1.getFxImage()));
            enemies.add(new Oneal(21, 6, Sprite.oneal_left1.getFxImage()));
            enemies.add(new Minvo(21, 6, Sprite.minvo_left1.getFxImage()));
            enemies.add(new Kondoria(21, 6, Sprite.kondoria_left1.getFxImage()));
            stillObjects.add(new Portal(23, 13, Sprite.transparent.getFxImage()));
                updateSound();
        }
    }
