package uet.oop.bomberman.entities.Monster.Smart;

import uet.oop.bomberman.entities.Block.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Monster.Monster;

import java.util.List;

public class AiLevel2 extends AI {

    Bomber _bomber;

    Monster _m;
//    List<Bomb> bombList;

    @Override
    public int calculateDirection() {
        // TODO: Chase bomber
        int choice = random.nextInt(2);

        if (choice == 1) {
            if (calculateColumnDir() != -1) {
                return calculateColumnDir();
            } else {
                return calculateRowDir();
            }
        } else {
            if (calculateRowDir() != -1) {
                return calculateRowDir();
            } else {
                return calculateColumnDir();
            }
        }

    }

    protected int calculateColumnDir() {
        if (_bomber.getBomberX() < _m.getMonsterX()) {
            return 3;
        } else if (_bomber.getBomberX() > _m.getMonsterX()) {
            return 1;
        }

        return -1;
    }

    protected int calculateRowDir() {
        if (_bomber.getBomberY() < _m.getMonsterY()) {
            return 0;
        } else if (_bomber.getBomberY() > _m.getMonsterY()) {
            return 2;
        }

        return -1;
    }
}