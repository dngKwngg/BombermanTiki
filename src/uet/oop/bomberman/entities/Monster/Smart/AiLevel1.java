package uet.oop.bomberman.entities.Monster.Smart;

import java.util.Random;

public class AiLevel1 extends AI{

    @Override
    public int calculateDirection() {
        Random rd = new Random();
        return rd.nextInt(4);
    }
}
