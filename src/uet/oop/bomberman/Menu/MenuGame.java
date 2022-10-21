package uet.oop.bomberman.Menu;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import uet.oop.bomberman.Level.Level1;

import static uet.oop.bomberman.BombermanGame.*;

public class MenuGame extends Parent {
    public MenuGame() {
        VBox menu = new VBox(15);
        //VBox menu1 = new VBox(15);

        menu.setTranslateX(200);
        menu.setTranslateY(200);
        //menu1.setTranslateX(100);
        //menu1.setTranslateY(200);

        final int offset = 400;

        Menubutton  PlayBt = new Menubutton ("Play");
//        g_mediaPlayer = new MediaPlayer(menu_sound);
//        g_mediaPlayer.play();
        PlayBt.setOnMouseClicked(event -> {
            new Level1();
            root.getChildren().removeAll(r, imageView);
            root.getChildren().add(bg);
            root.getChildren().add(pa);
        });

        Menubutton  ExitBt = new Menubutton ("Exit");
        ExitBt.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu.getChildren().addAll(PlayBt,ExitBt);

        getChildren().addAll(menu);
    }

}
