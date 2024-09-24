import java.awt.*;
import java.lang.Math;

import static java.lang.Math.sqrt;

public class Snake extends sprite {

    private int length;
    private String direction;
    private int width;
    private int height;


    public Snake(int x, int y) {

        super(x, y);
        this.length = 1;
        this.width=50;
        this.height=50;
    }

    public Snake(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }



    public void update(){

    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void draw(Graphics g){
        g.setColor(Color.green);
        g.fillRect(this.getPositionX(), this.getPositionY(), width, height);
    }
    public void addLength(){
        this.length++;
    }
    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public boolean checkCollisionRect(float r1x, float r1y, float r1w, float r1h, float r2x, float r2y, float r2w, float r2h) {

        // are the sides of one rectangle touching the other?

        if (r1x + r1w >= r2x &&    // r1 right edge past r2 left
                r1x <= r2x + r2w &&    // r1 left edge past r2 right
                r1y + r1h >= r2y &&    // r1 top edge past r2 bottom
                r1y <= r2y + r2h) {    // r1 bottom edge past r2 top
            return true;
        }
        return false;
    }

    public boolean checkCollisionCircle(float cx, float cy, float radius, float rx, float ry, float rw, float rh) {

        // temporary variables to set edges for testing
        float testX = cx;
        float testY = cy;

        // which edge is closest?
        if (cx < rx)         testX = rx;      // test left edge
        else if (cx > rx+rw) testX = rx+rw;   // right edge
        if (cy < ry)         testY = ry;      // top edge
        else if (cy > ry+rh) testY = ry+rh;   // bottom edge

        // get distance from closest edges
        float distX = cx-testX;
        float distY = cy-testY;
        double distance = sqrt((distX*distX) + (distY*distY));

        // if the distance is less than the radius, collision!
        if (distance <= radius) {
            return true;
        }
        return false;
    }


}
