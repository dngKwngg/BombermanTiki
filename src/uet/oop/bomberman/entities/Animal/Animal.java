package uet.oop.bomberman.entities.Animal;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.entities;

public abstract class Animal extends Entity {
    protected boolean isAlive;              // Check enemy alive
    protected int isMove;                   // Jump animal by pixel
    protected int swap;                     // Swap image
    protected String direction;             // Direction of layer (left, right, up, down)
    protected int countStep;                // Count number of step in a jump
    protected int countToRun;               // Run after count frame


    public Animal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Animal() {

    }
}
