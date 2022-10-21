package uet.oop.bomberman.Menu;
    import javafx.scene.Parent;
    import javafx.scene.control.MenuButton;
    import javafx.scene.layout.VBox;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.V;
import static uet.oop.bomberman.Level.LevelNew.NewLevel;

    public class MenuPause extends Parent {
        public MenuPause() {
            VBox menu = new VBox(15);
            menu.setTranslateX(300);
            menu.setTranslateY(200);

            Menubutton  PlayBt = new Menubutton ("PlayAgain");
            PlayBt.setOnMouseClicked(event -> {
                root.getChildren().removeAll(pp, View, slider);
                _gameLevel = 1;
                NewLevel();
                running = true;
            });

            Menubutton  ResumeBt = new Menubutton ("Resume");
            ResumeBt.setOnMouseClicked(event -> {
                running = true;
                //g_mediaPlayer.play();
                root.getChildren().removeAll(pp, View, slider);
            });
            Menubutton  ExitBt = new Menubutton ("Exit");
            ExitBt.setOnMouseClicked(event -> {
                System.exit(0);
            });

            menu.getChildren().addAll(PlayBt, ResumeBt,ExitBt);

            getChildren().addAll(menu);
        }
    }

