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
        if (_gameLevel == 1) {
            new Level1();
            isEndGame = false;
        } else if (_gameLevel == 3) {
            new Level2();
            isEndGame = false;
        } else if (_gameLevel == 5) {
            new Level3();
            isEndGame = false;
        } else if (_gameLevel == 7) {
            entities.clear();
            stillObjects.clear();
            root.getChildren().removeAll(bg, pa);
            root.getChildren().add(imgView);
            root.getChildren().addAll(pane);
            isEndGame = false;
        }
    }
}

