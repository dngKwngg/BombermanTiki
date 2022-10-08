package uet.oop.bomberman.Control;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Monster.Ballom;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Control.IsBlocked;

import static uet.oop.bomberman.Control.IsBlocked.*;
<<<<<<< Updated upstream
=======
import static uet.oop.bomberman.entities.Items.SpeedItem.speed;
>>>>>>> Stashed changes

public class Move {

    public static void checkRun(Entity entity) {
        if (entity instanceof Bomber && entity.getCount() > 0) {
            setDirection(entity.getDirection(), entity, 8 );
            entity.setCount(entity.getCount() - 1);
        }
        if (entity instanceof Ballom && entity.getCount() > 0) {
            setDirection(entity.getDirection(), entity, 4);
            entity.setCount(entity.getCount() - 1);
        }
    }

<<<<<<< Updated upstream
    public static void setDirection(String direction, Entity entity, int step) {
=======
    public static void setDirection(String direction, Entity entity, int step) {     //Show the direction of all mob
>>>>>>> Stashed changes
        switch (direction) {
            case "down":
                downstep(entity);
                entity.setY(entity.getY() + step);
                break;
            case "up":
                upstep(entity);
                entity.setY(entity.getY() - step);
                break;
            case "left":
                leftstep(entity);
                entity.setX(entity.getX() - step);
                break;
            case "right":
                rightstep(entity);
                entity.setX(entity.getX() + step);
                break;
        }
    }

    public static void up(Entity entity) {
        if(entity instanceof Bomber && entity.getCount()==0 && blockUp(entity)) {
            entity.setCount(4);
            entity.setDirection("up");
            checkRun(entity);
        }
        if(entity instanceof Ballom && entity.getCount()==0) {
            if(blockUp(entity)) {
                entity.setStanding(0);
                entity.setCount(8);
                entity.setDirection("up");
                checkRun(entity);
            } else {
                entity.setStanding(1);
            }
        }
    }

    public static void down(Entity entity) {
        if(entity instanceof Bomber && entity.getCount()==0 && blockDown(entity)) {
            entity.setCount(4);
            entity.setDirection("down");
            checkRun(entity);
        }
        if(entity instanceof Ballom && entity.getCount()==0) {
            if(blockDown(entity)) {
                entity.setStanding(0);
                entity.setCount(8);
                entity.setDirection("down");
                checkRun(entity);
            } else {
                entity.setStanding(1);
            }
        }
    }

    public static void right(Entity entity) {
        if(entity instanceof Bomber && entity.getCount()==0 && blockRight(entity)) {
            entity.setCount(4);
            entity.setDirection("right");
            checkRun(entity);
        }
        if(entity instanceof Ballom && entity.getCount()==0) {
            if(blockRight(entity)) {
                entity.setStanding(0);
                entity.setCount(8);
                entity.setDirection("right");
                checkRun(entity);
            } else {
                entity.setStanding(1);
            }
        }
    }

    public static void left(Entity entity) {
        if(entity instanceof Bomber && entity.getCount()==0 && blockLeft(entity)) {
            entity.setCount(4);
            entity.setDirection("left");
            checkRun(entity);
        }
        if(entity instanceof Ballom && entity.getCount()==0) {
            if(blockLeft(entity)) {
                entity.setStanding(0);
                entity.setCount(8);
                entity.setDirection("left");
                checkRun(entity);
            } else {
                entity.setStanding(1);
            }
        }
    }

    public static void upstep(Entity entity) {
        if (entity instanceof Bomber) {
            if (entity.getSwap() == 1) {
                entity.setImg(Sprite.player_up.getFxImage());
                entity.setSwap(2);
            } else if (entity.getSwap() == 2) {
                entity.setImg(Sprite.player_up_1.getFxImage());
                entity.setSwap(3);
            } else if (entity.getSwap() == 3) {
                entity.setImg(Sprite.player_up_2.getFxImage());
                entity.setSwap(4);
            } else {
                entity.setImg(Sprite.player_up.getFxImage());
                entity.setSwap(1);
            }
        }
        if (entity instanceof Ballom) {
            if (entity.getSwap() == 1) {
                entity.setImg(Sprite.balloom_left1.getFxImage());
                entity.setSwap(2);
            } else if (entity.getSwap() == 2) {
                entity.setImg(Sprite.balloom_left2.getFxImage());
                entity.setSwap(3);
            } else if (entity.getSwap() == 3) {
                entity.setImg(Sprite.balloom_left3.getFxImage());
                entity.setSwap(4);
            } else {
                entity.setImg(Sprite.balloom_left3.getFxImage());
                entity.setSwap(1);
            }
        }
    }

    public static void downstep(Entity entity) {
        if (entity instanceof Bomber) {
            if (entity.getSwap() == 1) {
                entity.setImg(Sprite.player_down.getFxImage());
                entity.setSwap(2);
            } else if (entity.getSwap() == 2) {
                entity.setImg(Sprite.player_down_1.getFxImage());
                entity.setSwap(3);
            } else if (entity.getSwap() == 3) {
                entity.setImg(Sprite.player_down_2.getFxImage());
                entity.setSwap(4);
            } else {
                entity.setImg(Sprite.player_down.getFxImage());
                entity.setSwap(1);
            }
        }
        if (entity instanceof Ballom) {
            if (entity.getSwap() == 1) {
                entity.setImg(Sprite.balloom_right1.getFxImage());
                entity.setSwap(2);
            } else if (entity.getSwap() == 2) {
                entity.setImg(Sprite.balloom_right2.getFxImage());
                entity.setSwap(3);
            } else if (entity.getSwap() == 3) {
                entity.setImg(Sprite.balloom_right3.getFxImage());
                entity.setSwap(4);
            } else {
                entity.setImg(Sprite.balloom_right3.getFxImage());
                entity.setSwap(1);
            }
        }
    }

    public static void leftstep(Entity entity) {
        if (entity instanceof Bomber) {
            if (entity.getSwap() == 1) {
                entity.setImg(Sprite.player_left.getFxImage());
                entity.setSwap(2);
            } else if (entity.getSwap() == 2) {
                entity.setImg(Sprite.player_left_1.getFxImage());
                entity.setSwap(3);
            } else if (entity.getSwap() == 3) {
                entity.setImg(Sprite.player_left_2.getFxImage());
                entity.setSwap(4);
            } else {
                entity.setImg(Sprite.player_left.getFxImage());
                entity.setSwap(1);
            }
        }
        if (entity instanceof Ballom) {
            if (entity.getSwap() == 1) {
                entity.setImg(Sprite.balloom_left1.getFxImage());
                entity.setSwap(2);
            } else if (entity.getSwap() == 2) {
                entity.setImg(Sprite.balloom_left2.getFxImage());
                entity.setSwap(3);
            } else if (entity.getSwap() == 3) {
                entity.setImg(Sprite.balloom_left3.getFxImage());
                entity.setSwap(4);
            } else {
                entity.setImg(Sprite.balloom_left3.getFxImage());
                entity.setSwap(1);
            }
        }
    }

    public static void rightstep(Entity entity) {
        if (entity instanceof Bomber ) {
            if (entity.getSwap() == 1) {
                entity.setImg(Sprite.player_right.getFxImage());
                entity.setSwap(2);
            } else if (entity.getSwap() == 2) {
                entity.setImg(Sprite.player_right_1.getFxImage());
                entity.setSwap(3);
            } else if (entity.getSwap() == 3) {
                entity.setImg(Sprite.player_right_2.getFxImage());
                entity.setSwap(4);
            } else {
                entity.setImg(Sprite.player_right.getFxImage());
                entity.setSwap(1);
            }
        }
        if (entity instanceof Ballom) {
            if (entity.getSwap() == 1) {
                entity.setImg(Sprite.balloom_right1.getFxImage());
                entity.setSwap(2);
            } else if (entity.getSwap() == 2) {
                entity.setImg(Sprite.balloom_right2.getFxImage());
                entity.setSwap(3);
            } else if (entity.getSwap() == 3) {
                entity.setImg(Sprite.balloom_right3.getFxImage());
                entity.setSwap(4);
            } else {
                entity.setImg(Sprite.balloom_right3.getFxImage());
                entity.setSwap(1);
            }
        }
    }
}
