package uet.oop.bomberman.entities.Monster.Smart;

import uet.oop.bomberman.entities.Block.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Monster.Monster;

import java.util.List;

import static uet.oop.bomberman.BombermanGame.player;

public class AiLevel2 extends AI {
    Monster _m;
//    List<Bomb> bombList;

    public AiLevel2(Monster _m) {
        this._m = _m;
    }

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
        if (player.getBomberX() < _m.getMonsterX()) {
            return 3;
        } else if (player.getBomberX() > _m.getMonsterX()) {
            return 1;
        }

        return -1;
    }

    protected int calculateRowDir() {
        if (player.getBomberY() < _m.getMonsterY()) {
            return 0;
        } else if (player.getBomberY() > _m.getMonsterY()) {
            return 2;
        }

        return -1;
    }
}