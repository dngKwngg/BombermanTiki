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

            MenuButton  PlayBt = new MenuButton ("PlayAgain");
            PlayBt.setOnMouseClicked(event -> {
                root.getChildren().removeAll(pp, View, slider);
                _gameLevel = 1;
                NewLevel();
                running = true;
            });

            MenuButton  ResumeBt = new MenuButton ("Resume");
            ResumeBt.setOnMouseClicked(event -> {
                running = true;
                //g_mediaPlayer.play();
                root.getChildren().removeAll(pp, View, slider);
            });
            MenuButton  ExitBt = new MenuButton ("Exit");
            ExitBt.setOnMouseClicked(event -> {
                System.exit(0);
            });

            menu.getChildren().addAll(PlayBt, ResumeBt,ExitBt);

            getChildren().addAll(menu);
        }
    }

