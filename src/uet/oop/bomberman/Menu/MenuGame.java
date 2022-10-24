package uet.oop.bomberman.Menu;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import uet.oop.bomberman.Level.Level1;

import static uet.oop.bomberman.BombermanGame.*;

public class MenuGame extends Parent {
    public MenuGame() {
        VBox menu = new VBox(15);

        menu.setTranslateX(300);
        menu.setTranslateY(350);

        final int offset = 400;

        Button PlayBt = new Button("Play");
        PlayBt.setOnMouseClicked(event -> {
            new Level1();
            root.getChildren().removeAll(r, imageView);
            root.getChildren().addAll(bg, pa);
        });

        Button ExitBt = new Button("Exit");
        ExitBt.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu.getChildren().addAll(PlayBt, ExitBt);

        getChildren().addAll(menu);
    }

}
