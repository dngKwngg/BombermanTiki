package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected Image img;
    protected int count;
    protected String direction;
    protected int swap=1;
    protected int delayPerStep;
    protected boolean life;

    protected int standing=1;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setCount(int count) {
        this.count=count;
    }

    public int getCount() {
        return count;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSwap(){
        return swap;
    }

    public void setSwap(int swap){
        this.swap=swap;
    }

    public int getDelayPerStep() {
        return delayPerStep;
    }

    public void setDelayPerStep(int delayPerStep) {
        this.delayPerStep = delayPerStep;
    }

    public boolean getLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life=life;
    }

    public int getStanding() {
        return standing;
    }

    public void setStanding(int standing) {
        this.standing=standing;
    }

    public void run() {
        setDelayPerStep(getDelayPerStep() + 1);
    }
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
}
