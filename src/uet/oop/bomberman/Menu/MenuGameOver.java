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
import static uet.oop.bomberman.Level.LevelNew.*;
import static uet.oop.bomberman.BombermanGame.*;
public class MenuGameOver extends Parent {
    Stage window;
    Scene scene1;
    public MenuGameOver() {
        VBox menu = new VBox(15);

        menu.setTranslateX(300);
        menu.setTranslateY(200);
        MenuButton PlayBt = new MenuButton("PlayAgain");
        PlayBt.setOnMouseClicked(event -> {
            _gameLevel = 1;
            NewLevel();
            root.getChildren().removeAll(p, V);
            root.getChildren().add(bg);
            root.getChildren().add(pa);
        });

        MenuButton MenuBt = new MenuButton("Menu");
        MenuBt.setOnMouseClicked(event -> {
            new MenuGame();

        });
        MenuButton ExitBt = new MenuButton("Exit");
        ExitBt.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu.getChildren().addAll(PlayBt,MenuBt,ExitBt);

        getChildren().addAll(menu);
    }
}
