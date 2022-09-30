package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Block.Brick;
import uet.oop.bomberman.entities.Block.Grass;
import uet.oop.bomberman.entities.Block.Portal;
import uet.oop.bomberman.entities.Block.Wall;
import uet.oop.bomberman.entities.Items.FlameItem;
import uet.oop.bomberman.entities.Items.SpeedItem;

import static uet.oop.bomberman.BombermanGame.*;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;


public class CreateMap {
    public CreateMap(String pathLevel) {
        File fileName = new File(pathLevel);        // Create object fileName contain info of the level.
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
}
