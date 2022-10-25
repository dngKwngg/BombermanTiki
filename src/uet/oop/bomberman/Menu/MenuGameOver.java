package uet.oop.bomberman.Menu;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import static uet.oop.bomberman.Level.LevelNew.*;
import static uet.oop.bomberman.BombermanGame.*;

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

        Button MenuBt = new Button("Back To Menu");
        MenuBt.setOnMouseClicked(event -> {
            entities.clear();
            enemies.clear();
            stillObjects.clear();
            root.getChildren().removeAll(bg, pa);
            root.getChildren().removeAll(V, p);
            root.getChildren().addAll(imageView, r);
        });
        Button ExitBt = new Button("Exit");
        ExitBt.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu.getChildren().addAll(PlayBt, MenuBt, ExitBt);

        getChildren().addAll(menu);
    }
}
