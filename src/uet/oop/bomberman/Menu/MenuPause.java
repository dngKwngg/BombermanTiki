package uet.oop.bomberman.Menu;

import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import uet.oop.bomberman.Level.Level1;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.V;
import static uet.oop.bomberman.Level.LevelNew.NewLevel;
import static uet.oop.bomberman.graphics.Sound.screen;

public class MenuPause extends Parent {
    public MenuPause() {
        VBox menu = new VBox(15);
        menu.setTranslateX(300);
        menu.setTranslateY(200);


        Button MenuBt = new Button("Back To Menu");
        MenuBt.setOnMouseClicked(event -> {
            entities.clear();
            enemies.clear();
            stillObjects.clear();
            screen.stop();
//                root.getChildren().removeAll(pp, View, slider);
            root.getChildren().removeAll(pp, View);
            root.getChildren().addAll(imageView, r);
            running = true;
        });

        Button ResumeBt = new Button("Resume");
        ResumeBt.setOnMouseClicked(event -> {
            isPause = false;
            running = true;
            //g_mediaPlayer.play();
            root.getChildren().removeAll(pp, View);
        });
        Button ExitBt = new Button("Exit");
        ExitBt.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu.getChildren().addAll(MenuBt, ResumeBt, ExitBt);

        getChildren().addAll(menu);
    }
}

