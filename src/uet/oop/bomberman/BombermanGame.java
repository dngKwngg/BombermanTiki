package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.Menu.*;
import uet.oop.bomberman.entities.Block.*;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.FlameItem;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.entities.Monster.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Control.Move;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import uet.oop.bomberman.entities.Block.Bomb;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import uet.oop.bomberman.Menu.MenuPause;
import javafx.scene.control.MenuButton;
import uet.oop.bomberman.graphics.Sound;
import static uet.oop.bomberman.graphics.Sound.updateSound;
import uet.oop.bomberman.Level.*;
import static uet.oop.bomberman.Level.LevelNew.*;
import static uet.oop.bomberman.entities.Block.Portal.*;
public class BombermanGame extends Application {
    public static int score = 0;

    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static int _mapWidth = 0;
    public static int _mapHeight = 0;
    public static int _gameLevel = 1;

    public static int[][] objIdx;
    public static int[][] listIsKilled;

    public static Bomber player;

    private GraphicsContext gc;

    public static Group root;
    public static ImageView imageView;

    public static ImageView View;

    public static ImageView V;
    public static ImageView imgView;
    public static Pane p;
    public static Pane r;
    public static Pane pane;
    public static Pane pa;
    public static Pane pp;
    public static Rectangle bg;

    private MenuGame menuGame;

    private MenuGameOver menuGameOver;
    private MenuWinGame menuWinGame;
    private MenuPause menuPause;

    public static Slider slider;

    public static Text level, scoreText;
    public static boolean running = true;
    private Canvas canvas;
    //    public static List<Entity> block = new ArrayList<>();           // Contains entities after fixed
    public static List<Entity> entities = new ArrayList<>();
    public static List<Monster> enemies = new ArrayList<>();
    // Contains enemy entities
    public static List<Entity> stillObjects = new ArrayList<>();    // Contains entities after fixed
    public static List<Entity> newEntities = new ArrayList<>();
    public static List<Entity> newStillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        root = new Group();
        menuGame = new MenuGame();
        r = new Pane();
        r.getChildren().add(menuGame);
        Image img = new Image("img/BomberMenu.png");
        imageView = new ImageView(img);
        p = new Pane();
        menuGameOver = new MenuGameOver();
        p.getChildren().add(menuGameOver);
        Image image = new Image("img/Gameover.png");
        V = new ImageView(image);

        menuWinGame = new MenuWinGame();
        pane = new Pane();
        pane.getChildren().add(menuWinGame);
        Image image1 = new Image("img/win.png");
        imgView = new ImageView(image1);

        menuPause = new MenuPause();
        pp = new Pane();
        pp.getChildren().add(menuPause);
        slider = new Slider(0, 100, 100);
        slider.setLayoutX(340);
        slider.setLayoutY(50);

        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.BLACK);
        level.setX(416);
        level.setY(20);
        scoreText = new Text("Score: "+ score);
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        scoreText.setFill(Color.BLACK);
        scoreText.setX(512);
        scoreText.setY(20);
        bg = new Rectangle(285, 25);
        bg.setFill(Color.GRAY);
        bg.setY(2);
        bg.setX(400);
        pa = new Pane();
        pa.getChildren().addAll(level, scoreText);
        root.getChildren().addAll(canvas, imageView, r);

        // Tao scene
        Scene scene = new Scene(root);

        //Bat su kien
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    Move.up(entities.get(0));
                    break;
                case DOWN:
                    Move.down(entities.get(0));
                    break;
                case RIGHT:
                    Move.right(entities.get(0));
                    break;
                case LEFT:
                    Move.left(entities.get(0));
                    break;
                case SPACE:
                    Bomb.plantBomb();
                    break;
                case P:
                    if (running) {
                        running = !running;
                        root.getChildren().add(View);
                        root.getChildren().addAll(pp, slider);
                    } else {
                        running = !running;
                        root.getChildren().removeAll(pp, View, slider);
                    }
                    break;
            }
        });

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

//        createMap();
//        player = new Bomber(1, 1, Sprite.player_right.getFxImage());
//        entities.add(player);
//        enemies.add(new Minvo(7, 3, Sprite.minvo_left1.getFxImage()));
//        enemies.add(new Oneal(9, 3, Sprite.oneal_left1.getFxImage()));
//        enemies.add(new  Doll(21, 6, Sprite.doll_left1.getFxImage()));
//        enemies.add(new Kondoria(3, 1, Sprite.kondoria_left1.getFxImage()));
//        enemies.add(new Ballom(7, 1, Sprite.balloom_left1.getFxImage()));
//        stillObjects.add(new Portal(23, 13, Sprite.transparent.getFxImage()));
//    }
//
//    public void createMap() {
//////        for (int i = 0; i < WIDTH; i++) {
//////            for (int j = 0; j < HEIGHT; j++) {
//////                Entity object;
//////                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//////                    object = new Wall(i, j, Sprite.wall.getFxImage());
//////                }
//////                else {
//////                    object = new Grass(i, j, Sprite.grass.getFxImage());
//////                }
//////                stillObjects.add(object);
//////            }
//////        }
//
//        String pathLevel = "res/levels/Level1.txt";
//        File fileName = new File(pathLevel);        //TODO: Create object fileName contain info of the level.
//        try (FileReader reader = new FileReader(fileName)) {            // Try - catch to create map.
//            Scanner sc = new Scanner(reader);           // Create obj sc from class Scanner.
//            String mapInfo = sc.nextLine();                // Get input from line 1 in string type.
//
//            StringTokenizer token = new StringTokenizer(mapInfo);      // Read 1 string in line.
//            // parseInt() method: convert String to Integer
//            _gameLevel = Integer.parseInt(token.nextToken());
//            _mapHeight = Integer.parseInt(token.nextToken());
//            _mapWidth = Integer.parseInt(token.nextToken());
//
//            while (sc.hasNextLine()) {              // If sc can read more line.
//                objIdx = new int[_mapWidth][_mapHeight];            // Create new obj mapIdx in main file.
//                listIsKilled = new int[_mapWidth][_mapHeight];         // Create new obj listKill in main file.
//                for (int i = 0; i < _mapHeight; ++i) {
//                    String lineInfo = sc.nextLine();                // Get input from line.
//                    StringTokenizer tokenLineInfo = new StringTokenizer(lineInfo);      // Read info from lineInfo.
//
//                    for (int j = 0; j < _mapWidth; ++j) {
//                        int value = Integer.parseInt(tokenLineInfo.nextToken());
//                        Entity object;                              // Create obj named object from class Entity.
//
//                        // This if - else statement running, and we got a full map for a game.
//                        // Through the program, in the for-loop statement, we can get the map according to each loop it passed.
//                        switch (value) {
//                            case 1:
//                                object = new Portal(j, i, Sprite.portal.getFxImage());
//                                value = 0;
//                                break;
//                            case 2:
//                                object = new Wall(j, i, Sprite.wall.getFxImage());
//                                break;
//                            case 3:
//                                object = new Brick(j, i, Sprite.brick.getFxImage());
//                                break;
//                            case 6:
//                                object = new SpeedItem(j, i, Sprite.brick.getFxImage());
//                                break;
//                            case 7:
//                                object = new FlameItem(j, i, Sprite.brick.getFxImage());
//                                break;
//                            default:
//                                object = new Grass(j, i, Sprite.grass.getFxImage());
//                        }
//
//                        objIdx[j][i] = value;
//                        stillObjects.add(object);
//
//                    }
//                }
//
//            }
//
//        } catch (IOException e) {               // Catch exception.
//            e.printStackTrace();                // printStackTrace(): Help to understand where the problem is actually happening.
//        }

    }
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).run();
            entities.get(i).update();
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).run();
            enemies.get(i).update();
        }
        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).run();
            stillObjects.get(i).update();
        }
       if (player != null && !player.getLife()) {
           updateSound();
            entities.clear();
            stillObjects.clear();
            root.getChildren().add(V);
            root.getChildren().addAll(p);
            root.getChildren().removeAll(bg, pa);
//            enemies.clear();
            player.setLife(true);
      }
        else {
            entities.addAll(newEntities);
            stillObjects.addAll(newStillObjects);
            newEntities.clear();
            newStillObjects.clear();
            entities.forEach(Entity::update);
            stillObjects.forEach(Entity::update);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
    }

    public void updateMenu() {
        level.setText("Level: " + _gameLevel);
    //    player.setText("Bombs: " + bombBank);
//        long now = System.currentTimeMillis();
//        if (now -  lastTime > 1000) {
//            lastTime = System.currentTimeMillis();
//
//            time.setText("Time: " + timeNumber);
//            timeNumber--;
//            if (timeNumber == 0)
//                bomber.setState(1);
//        }
    }
}


