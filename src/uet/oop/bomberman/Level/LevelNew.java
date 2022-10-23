package uet.oop.bomberman.Level;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import static uet.oop.bomberman.entities.Block.Portal.isEndGame;
import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Block.Portal.onPortal;
import static uet.oop.bomberman.graphics.Sound.*;
//import static uet.oop.bomberman.graphics.Sound.level_complete;

public class LevelNew {
    public static void NewLevel() {
        onPortal = false;
        switch (_gameLevel) {
            case 1:
                screen.stop();
                new Level1();
                break;
            case 2:
                screen.stop();
                new Level2();
                break;
            case 3:
                screen.stop();
                new Level3();
                break;
            case 4:
                isPause = true;
                screen.stop();
                entities.clear();
                stillObjects.clear();
                root.getChildren().removeAll(bg, pa);
                root.getChildren().add(imgView);
                root.getChildren().addAll(pane);
                break;
        }
    }
}

