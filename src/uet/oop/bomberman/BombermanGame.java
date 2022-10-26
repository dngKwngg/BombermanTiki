package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Monster.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Control.Move;

import java.io.*;

import uet.oop.bomberman.entities.Block.Bomb;

import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.Menu.MenuPause;

import static uet.oop.bomberman.entities.Block.Bomb.bomb;

public class BombermanGame extends Application {
    public static int score = 0;
    public static int highScore;

    public static boolean isPause = false;

    FileReader fr;

    {
        try {
            fr = new FileReader("res/score/highscore.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean isOver = false;
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

    public static Text level, scoreText, highscore;
    public static boolean running = true;
    private Canvas canvas;
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
        try {
            highScore = fr.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        Image image1 = new Image("img/winner.png");
        imgView = new ImageView(image1);

        menuPause = new MenuPause();
        pp = new Pane();
        pp.getChildren().add(menuPause);

        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.BLACK);
        level.setX(352);
        level.setY(20);
        scoreText = new Text("Score: " + score);
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        scoreText.setFill(Color.BLACK);
        scoreText.setX(448);
        scoreText.setY(20);

        highscore = new Text("Highscore:" + highScore);
        highscore.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        highscore.setFill(Color.BLACK);
        highscore.setX(560);
        highscore.setY(20);

        bg = new Rectangle(385, 25);
        bg.setFill(Color.GRAY);
        bg.setY(2);
        bg.setX(300);
        pa = new Pane();
        pa.getChildren().addAll(level, scoreText, highscore);
        root.getChildren().addAll(canvas, imageView, r);

        // Create Scece
        Scene scene = new Scene(root);

        // Catch event


        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    Move.up(player);
                    break;
                case DOWN:
                    Move.down(player);
                    break;
                case RIGHT:
                    Move.right(player);
                    break;
                case LEFT:
                    Move.left(player);
                    break;
                case SPACE:
                    if (player != null) {
                        Bomb.plantBomb();
                    }
                    break;
                case P:
                    if (running) {
                        isPause = true;
                        running = !running;
                        root.getChildren().add(View);
                        root.getChildren().addAll(pp);
                    } else {
                        isPause = false;
                        running = !running;
                        root.getChildren().removeAll(pp, View);
                    }
                    break;
            }
        });

        // Add scene to stage
        stage.setScene(scene);
        stage.setTitle("Bomberman By JavaFx");
        Image icon = new Image("img/icon.png");
        stage.getIcons().add(icon);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!isPause) {
                    render();
                    update();
                    updateMenu();
                }
            }
        };
        timer.start();
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

        if (player != null && !player.getLife() && isOver) {
            entities.clear();
            stillObjects.clear();
            root.getChildren().add(V);
            root.getChildren().addAll(p);
            root.getChildren().removeAll(bg, pa);
            isOver = false;
            player.setLife(true);
        } else {
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
        if (score > highScore) {
            highScore = score;
            try {
                FileWriter fw = new FileWriter("res/score/highscore.txt");
                fw.write(highScore);
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        level.setText("Level: " + _gameLevel);
        scoreText.setText("Score: " + score);
        highscore.setText("Highscore: " + highScore);
    }
}


