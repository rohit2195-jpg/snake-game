import java.awt.*;
import java.awt.geom.Point2D;

public class sprite {

    private int positionX;
    private int positionY;
    private int speedX;
    private int speedY;
    private int prevX, prevY;
    private KeyHandler keyHandler;
    public sprite(int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.speedX = 0;
        this.speedY = 0;


    }

    public sprite(int positionX, int positionY, int speedX, int speedY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void move(){

        prevX = getPositionX();
        prevY = getPositionY();

        this.setPositionX(getPositionX() + getSpeedX());
        this.setPositionY(getPositionY() + getSpeedY());
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getSpeedX() {
        return speedX;
    }
    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getPrevX() {
        return prevX;
    }
    public int getPrevY() {
        return prevY;
    }











}
