package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Animal.Animal;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Block.Grass;
import uet.oop.bomberman.entities.Block.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Control.Move;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static int _mapWidth = 0;
    public static int _mapHeight = 0;
    public static int _gameLevel = 1;

    public static int[][] objIdx;
    public static int[][] listIsKilled;

    public static Bomber player;

    private GraphicsContext gc;
    private Canvas canvas;
    //    public static List<Entity> block = new ArrayList<>();           // Contains entities after fixed
    public static List<Entity> entities = new ArrayList<>();
    public static List<Animal> enemies = new ArrayList<>();         // Contains enemy entities
    public static List<Entity> stillObjects = new ArrayList<>();    // Contains entities after fixed


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

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
                    break;
                case P:
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

        createMap();

        player = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(player);
    }

    public void createMap() {
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                Entity object;
//                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                }
//                else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
//                stillObjects.add(object);
//            }
//        }

        String pathLevel = "res/levels/Level1.txt";
        File fileName = new File(pathLevel);        //TODO: Create object fileName contain info of the level.
        try (FileReader reader = new FileReader(fileName)) {            // Try - catch to create map.
            Scanner sc = new Scanner(reader);           // Create obj sc from class Scanner.
            String mapInfo = sc.nextLine();                // Get input from line 1 in string type.

            StringTokenizer token = new StringTokenizer(mapInfo);      // Read 1 string in line.
            // parseInt() method: convert String to Integer
            _gameLevel = Integer.parseInt(token.nextToken());
            _mapHeight = Integer.parseInt(token.nextToken());
            _mapWidth = Integer.parseInt(token.nextToken());

            while (sc.hasNextLine()) {              // If sc can read more line.
                objIdx = new int[_mapWidth][_mapHeight];            // Create new obj mapIdx in main file.
                listIsKilled = new int[_mapWidth][_mapHeight];         // Create new obj listKill in main file.
                for (int i = 0; i < _mapHeight; ++i) {
                    String lineInfo = sc.nextLine();                // Get input from line.
                    StringTokenizer tokenLineInfo = new StringTokenizer(lineInfo);      // Read info from lineInfo.

                    for (int j = 0; j < _mapWidth; ++j) {
                        int value = Integer.parseInt(tokenLineInfo.nextToken());
                        Entity object;                              // Create obj named object from class Entity.

                        // This if - else statement running, and we got a full map for a game.
                        // Through the program, in the for-loop statement, we can get the map according to each loop it passed.
                        switch (value) {
                            case 1:
                                object = new Portal(j, i, Sprite.portal.getFxImage());
                                value = 0;
                                break;
                            case 2:
                                object = new Wall(j, i, Sprite.wall.getFxImage());
                                break;
                            case 3:
                                object = new Brick(j, i, Sprite.brick.getFxImage());
                                break;
                            case 6:
                                object = new SpeedItem(j, i, Sprite.brick.getFxImage());
                                break;
                            case 7:
                                object = new FlameItem(j, i, Sprite.brick.getFxImage());
                                break;
                            default:
                                object = new Grass(j, i, Sprite.grass.getFxImage());
                        }

                        objIdx[j][i] = value;
                        stillObjects.add(object);

                    }
                }

            }

        } catch (IOException e) {               // Catch exception.
            e.printStackTrace();                // printStackTrace(): Help to understand where the problem is actually happening.
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        player.run();
        player.update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
