package uet.oop.bomberman.entities.Monster.Smart;

import java.util.Random;

public abstract class AI {
    protected Random random = new Random();

    // 0: down
    // 1: up
    // 2: left
    // 3: right

    public abstract int calculateDirection();
}
