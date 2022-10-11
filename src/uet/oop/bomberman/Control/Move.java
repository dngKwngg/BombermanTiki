package uet.oop.bomberman.Control;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Monster.Ballom;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.Control.IsBlocked.*;


public class
Move {

    public static void checkRun(Entity entity) {
        if (entity instanceof Bomber && entity.getCount() > 0) {
            stepByStep(entity.getDirection(), entity, 8 );
            entity.setCount(entity.getCount() - 1);
        }

        if (entity instanceof Ballom && entity.getCount() > 0) {
            stepByStep(entity.getDirection(), entity, 4);
            entity.setCount(entity.getCount() - 1);
        }
    }

//    public static void setDirection(String direction, Entity entity, int step) {

    public static void stepByStep(int direction, Entity entity, int distance) {     //Show the direction of all mob
        switch (direction) {
            case 0:
                ifDown(entity);
                entity.setY(entity.getY() + distance);
                break;
            case 1:
                ifUp(entity);
                entity.setY(entity.getY() - distance);
                break;
            case 2:
                ifLeft(entity);
                entity.setX(entity.getX() - distance);
                break;
            case 3:
                ifRight(entity);
                entity.setX(entity.getX() + distance);
                break;
        }
    }

    public static void up(Entity entity) {
        if(entity instanceof Bomber && entity.getCount()==0 && blockUp(entity)) {
            entity.setCount(4);
            entity.setDirection(1);
            checkRun(entity);
        }
        if(entity instanceof Ballom && entity.getCount()==0) {
            if(blockUp(entity)) {
                entity.setStanding(0);
                entity.setCount(8);
                entity.setDirection(1);
                checkRun(entity);
            } else {
                entity.setStanding(1);
            }
        }
    }

    public static void down(Entity entity) {
        if(entity instanceof Bomber && entity.getCount()==0 && blockDown(entity)) {
            entity.setCount(4);
            entity.setDirection(0);
            checkRun(entity);
        }
        if(entity instanceof Ballom && entity.getCount()==0) {
            if(blockDown(entity)) {
                entity.setStanding(0);
                entity.setCount(8);
                entity.setDirection(0);
                checkRun(entity);
            } else {
                entity.setStanding(1);
            }
        }
    }

    public static void right(Entity entity) {
        if(entity instanceof Bomber && entity.getCount()==0 && blockRight(entity)) {
            entity.setCount(4);
            entity.setDirection(3);
            checkRun(entity);
        }
        if(entity instanceof Ballom && entity.getCount()==0) {
            if(blockRight(entity)) {
                entity.setStanding(0);
                entity.setCount(8);
                entity.setDirection(3);
                checkRun(entity);
            } else {
                entity.setStanding(1);
            }
        }
    }

    public static void left(Entity entity) {
        if(entity instanceof Bomber && entity.getCount()==0 && blockLeft(entity)) {
            entity.setCount(4);
            entity.setDirection(2);
            checkRun(entity);
        }
        if(entity instanceof Ballom && entity.getCount()==0) {
            if(blockLeft(entity)) {
                entity.setStanding(0);
                entity.setCount(8);
                entity.setDirection(2);
                checkRun(entity);
            } else {
                entity.setStanding(1);
            }
        }
    }

    public static void ifUp(Entity entity) {
        if (entity instanceof Bomber) {
            if (entity.getCount() % 4 == 0) {
                entity.setImg(Sprite.player_up.getFxImage());
            } else if (entity.getCount() % 4 == 3) {
                entity.setImg(Sprite.player_up_1.getFxImage());
            } else if (entity.getCount() % 4 == 2) {
                entity.setImg(Sprite.player_up_2.getFxImage());
            } else {
                entity.setImg(Sprite.player_up.getFxImage());
            }
        }
        if (entity instanceof Ballom) {
            if (entity.getCount() % 4 == 0) {
                entity.setImg(Sprite.balloom_left1.getFxImage());
            } else if (entity.getCount() % 4 == 3) {
                entity.setImg(Sprite.balloom_left2.getFxImage());
            } else if (entity.getCount() % 4 == 2) {
                entity.setImg(Sprite.balloom_left3.getFxImage());
            } else {
                entity.setImg(Sprite.balloom_left3.getFxImage());
            }
        }
    }

    public static void ifDown(Entity entity) {
        if (entity instanceof Bomber) {
            if (entity.getCount() % 4 == 0) {
                entity.setImg(Sprite.player_down.getFxImage());
            } else if (entity.getCount() % 4 == 3) {
                entity.setImg(Sprite.player_down_1.getFxImage());
            } else if (entity.getCount() % 4 == 2) {
                entity.setImg(Sprite.player_down_2.getFxImage());
            } else {
                entity.setImg(Sprite.player_down.getFxImage());
            }
        }
        if (entity instanceof Ballom) {
            if (entity.getCount() % 4 == 0) {
                entity.setImg(Sprite.balloom_right1.getFxImage());
            } else if (entity.getCount() % 4 == 3) {
                entity.setImg(Sprite.balloom_right2.getFxImage());
            } else if (entity.getCount() % 4 == 2) {
                entity.setImg(Sprite.balloom_right3.getFxImage());
            } else {
                entity.setImg(Sprite.balloom_right3.getFxImage());
            }
        }
    }

    public static void ifLeft(Entity entity) {
        if (entity instanceof Bomber) {
            if (entity.getCount() % 4 == 0) {
                entity.setImg(Sprite.player_left.getFxImage());
            } else if (entity.getCount() % 4 == 3) {
                entity.setImg(Sprite.player_left_1.getFxImage());
            } else if (entity.getCount() % 4 == 2) {
                entity.setImg(Sprite.player_left_2.getFxImage());
            } else {
                entity.setImg(Sprite.player_left.getFxImage());
            }
        }
        if (entity instanceof Ballom) {
            if (entity.getCount() % 4 == 0) {
                entity.setImg(Sprite.balloom_left1.getFxImage());
            } else if (entity.getCount() % 4 == 3) {
                entity.setImg(Sprite.balloom_left2.getFxImage());
            } else if (entity.getCount() % 4 == 2) {
                entity.setImg(Sprite.balloom_left3.getFxImage());
            } else {
                entity.setImg(Sprite.balloom_left3.getFxImage());
            }
        }
    }

    public static void ifRight(Entity entity) {
        if (entity instanceof Bomber) {
            if (entity.getCount() % 4 == 0) {
                entity.setImg(Sprite.player_right.getFxImage());
            } else if (entity.getCount() % 4 == 3) {
                entity.setImg(Sprite.player_right_1.getFxImage());
            } else if (entity.getCount() % 4 == 2) {
                entity.setImg(Sprite.player_right_2.getFxImage());
            } else {
                entity.setImg(Sprite.player_right.getFxImage());
            }
        }
        if (entity instanceof Ballom) {
            if (entity.getCount() % 4 == 0) {
                entity.setImg(Sprite.balloom_right1.getFxImage());
            } else if (entity.getCount() % 4 == 3) {
                entity.setImg(Sprite.balloom_right2.getFxImage());
            } else if (entity.getCount() % 4 == 2) {
                entity.setImg(Sprite.balloom_right3.getFxImage());
            } else {
                entity.setImg(Sprite.balloom_right3.getFxImage());
            }
        }
    }
}
