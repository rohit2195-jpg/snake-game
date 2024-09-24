import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class Block extends sprite {

    ArrayList<Point2D> turns = new ArrayList<Point2D>();
    ArrayList<Point2D> velocityTurns = new ArrayList<Point2D>();
    private int width, height;

    public Block(int x, int y){
        super(x,y);
        this.height=50;
        this.width=50;
    }

    public Block(int x, int y, int speedX, int speedY){
        super(x,y,speedX,speedY);
        this.height=50;
        this.width=50;
    }
    public Block(int x, int y, int speedX, int speedY, int width, int height){
        super(x,y,speedX,speedY);
        this.height=height;
        this.width=width;
    }

    public void updateVelocity(String direction){




        if(this.getSpeedX()>0 && !turns.isEmpty() && !velocityTurns.isEmpty() && this.getPositionX() >= turns.get(0).getX()){
            this.setPositionX((int)turns.get(0).getX());
            this.setPositionY((int)turns.get(0).getY());
            this.setSpeedX((int)velocityTurns.get(0).getX());
            this.setSpeedY((int)velocityTurns.get(0).getY());
            turns.remove(0);
            velocityTurns.remove(0);
        }
        if(this.getSpeedX()<0 && !turns.isEmpty() && !velocityTurns.isEmpty() && this.getPositionX() <= turns.get(0).getX()){
            this.setPositionX((int)turns.get(0).getX());
            this.setPositionY((int)turns.get(0).getY());
            this.setSpeedX((int)velocityTurns.get(0).getX());
            this.setSpeedY((int)velocityTurns.get(0).getY());
            turns.remove(0);
            velocityTurns.remove(0);
        }
        if(this.getSpeedY()>0 && !turns.isEmpty() && !velocityTurns.isEmpty() && this.getPositionY() >= turns.get(0).getY()){
            this.setPositionX((int)turns.get(0).getX());
            this.setPositionY((int)turns.get(0).getY());
            this.setSpeedX((int)velocityTurns.get(0).getX());
            this.setSpeedY((int)velocityTurns.get(0).getY());
            turns.remove(0);
            velocityTurns.remove(0);
        }
        if(this.getSpeedY()<0 && !turns.isEmpty() && !velocityTurns.isEmpty() && this.getPositionY() <= turns.get(0).getY()){
            this.setPositionX((int)turns.get(0).getX());
            this.setPositionY((int)turns.get(0).getY());
            this.setSpeedX((int)velocityTurns.get(0).getX());
            this.setSpeedY((int)velocityTurns.get(0).getY());
            turns.remove(0);
            velocityTurns.remove(0);
        }

    }



    public void draw(Graphics g){
        //g.setColor(Color.green);
        g.fillRect(this.getPositionX(), this.getPositionY(), 50, 50);
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
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



    public void setTurns(ArrayList<Point2D> turns){
        this.turns = turns;
    }
    public void setVelocityTurns(ArrayList<Point2D> velocityTurns){
        this.velocityTurns = velocityTurns;
    }
    public void addTurn(Point2D p){
        turns.add(p);
    }

    public void addVelocityTurn(Point2D p){
        velocityTurns.add(p);
    }
    public ArrayList<Point2D> getTurns(){
        return turns;
    }
    public ArrayList<Point2D> getVelocityTurns(){
        return velocityTurns;
    }
    public void repaint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(getPositionX(), getPositionX(), width, height);
    }








}
