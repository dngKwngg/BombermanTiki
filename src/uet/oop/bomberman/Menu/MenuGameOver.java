package uet.oop.bomberman.Menu;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Level.*;
import uet.oop.bomberman.Menu.MenuGame.*;
import static uet.oop.bomberman.Level.LevelNew.*;
import static uet.oop.bomberman.BombermanGame.*;

import static uet.oop.bomberman.graphics.Sound.*;
import static uet.oop.bomberman.graphics.Sound.screen;
public class MenuGameOver extends Parent {
    private MenuGame menuGame;
    public MenuGameOver() {
        VBox menu = new VBox(15);

        menu.setTranslateX(300);
        menu.setTranslateY(200);
        Button PlayBt = new Button("PlayAgain");
        PlayBt.setOnMouseClicked(event -> {
            _gameLevel = 1;
//            screen.stop();
            NewLevel();
            root.getChildren().removeAll(p, V);
            root.getChildren().add(bg);
            root.getChildren().add(pa);
        });

        Button  MenuBt = new Button ("Back To Menu");
        MenuBt.setOnMouseClicked(event -> {
            entities.clear();
            enemies.clear();
            stillObjects.clear();
            root.getChildren().removeAll(bg, pa);
            root.getChildren().removeAll(V, p);
            root.getChildren().addAll(imageView,r);

            root.getChildren().addAll(imageView, r);

        });
        Button  ExitBt = new Button ("Exit");
        ExitBt.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu.getChildren().addAll(PlayBt,MenuBt,ExitBt);

        getChildren().addAll(menu);
    }
}
