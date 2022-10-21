package uet.oop.bomberman.Menu;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.graphics.Sound.*;
import static uet.oop.bomberman.Level.LevelNew.NewLevel;

public class MenuWinGame extends Parent {
    public MenuWinGame() {
        VBox menu = new VBox(15);

        menu.setTranslateX(300);
        menu.setTranslateY(200);

        Menubutton  PlayBt = new Menubutton ("PlayAgain");
        PlayBt.setOnMouseClicked(event -> {
            _gameLevel = 1;
            NewLevel();
            root.getChildren().removeAll(pane, imgView);
            root.getChildren().add(bg);
            root.getChildren().add(pa);
        });

        Menubutton  ExitBt = new Menubutton ("Exit");
        ExitBt.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu.getChildren().addAll(PlayBt, ExitBt);

        getChildren().addAll(menu);
    }
}
