package uet.oop.bomberman.Level;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import static uet.oop.bomberman.entities.Block.Portal.isEndGame;
import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Block.Portal.onPortal;
//import static uet.oop.bomberman.graphics.Sound.level_complete;

public class LevelNew {
    public static void NewLevel() {
        onPortal = false;
        switch (_gameLevel) {
            case 1:
                new Level1();
                isEndGame = false;
                break;
            case 2:
                new Level2();
                isEndGame = false;
                break;
            case 3:
                new Level3();
                isEndGame = false;
                break;
            case 4:
                entities.clear();
                stillObjects.clear();
                root.getChildren().removeAll(bg, pa);
                root.getChildren().add(imgView);
                root.getChildren().addAll(pane);
                isEndGame = false;
                break;
        }
    }
}

