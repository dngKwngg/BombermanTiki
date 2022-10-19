package uet.oop.bomberman.Menu;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
//import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Level.Level1;

import static uet.oop.bomberman.BombermanGame.*;
//import static uet.oop.bomberman.graphics.Sound.menu_sound;

public class MenuGame extends Parent {
    public MenuGame() {
        VBox menu = new VBox(15);

        menu.setTranslateX(300);
        menu.setTranslateY(200);
        //menu1.setTranslateX(100);
        //menu1.setTranslateY(200);

        final int offset = 400;

        MenuButton PlayBt = new MenuButton("Play");
        PlayBt.setOnMouseClicked(event -> {
            new Level1();
            root.getChildren().removeAll(r, imageView);
            root.getChildren().add(bg);
            root.getChildren().add(pa);
        });

        MenuButton ExitBt = new MenuButton("Exit");
        ExitBt.setOnMouseClicked(event -> {
            System.exit(0);
        });


        menu.getChildren().addAll(PlayBt,ExitBt);

        getChildren().addAll(menu);
    }

}

