import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;



public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 12;
    final int scale = 3;

    final int tilesize  = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tilesize * maxScreenCol;
    final int screenHeight = tilesize * maxScreenRow;

    //fps
    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Snake snake = new Snake(100, 100, 50, 50);
    final int SNAKESPEED = 5;

    int score = 0;

    Random rand = new Random();

    Apple apple = new Apple(rand.nextInt(screenWidth), rand.nextInt(screenHeight), 50);
    ArrayList<Apple> apples = new ArrayList<Apple>();


    ArrayList<Block> snakeBlock = new ArrayList<Block>();
    Block b1 = new Block(snake.getPositionX() - snake.getWidth()-5, snake.getPositionY(), snake.getSpeedX(),
            snake.getSpeedY(), snake.getWidth(), snake.getHeight());
    boolean startGame = false;
    int count = 0;

    Sound sound = new Sound();





    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);


    }



    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        apples.add(apple);
        snakeBlock.add(b1);

        //starting the game


    }

    public void run(){

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread !=  null) {
            //update information such as character postiion
            // draw: draw screen with updated information
            //redraw screen

            update();

            repaint();

            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime <= 0) {
                    remainingTime=0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    public void update(){

        snake.move();
        checkEnd();



        if (snake.checkCollisionRect(snake.getPositionX(), snake.getPositionY(), snake.getWidth(), snake.getHeight(),
                apple.getPositionX(), apple.getPositionY(), apple.getRadius(), apple.getRadius())) {
            sound.playSound("/Users/rohitsattuluri/IdeaProjects/snake/res/power_up.wav");

            // Add a new block to the snake
            addNewBlock();
            score++;

            // Move the apple to a new random location
            relocateApple();
        }

        for(Block b : snakeBlock){
            b.move();
            b.updateVelocity(snake.getDirection());

        }





        if(keyHandler.isUpPressed() && startGame){
            snake.setSpeedY(-1*SNAKESPEED);
            snake.setSpeedX(0);
            snake.setDirection("up");
            for(Block block : snakeBlock){
                Point2D p = new Point2D.Double(snake.getPositionX(), snake.getPositionY());
                block.addTurn(p);
                Point2D v = new Point2D.Double(snake.getSpeedX(), snake.getSpeedY());
                block.addVelocityTurn(v);

            }


        }

        if(keyHandler.isDownPressed() && startGame){
            snake.setSpeedY(SNAKESPEED);
            snake.setSpeedX(0);
            snake.setDirection("down");
            for(Block block : snakeBlock){
                Point2D p = new Point2D.Double(snake.getPositionX(), snake.getPositionY());
                block.addTurn(p);
                Point2D v = new Point2D.Double(snake.getSpeedX(), snake.getSpeedY());
                block.addVelocityTurn(v);

            }

        }
        if(keyHandler.isLeftPressed() && startGame){
            snake.setSpeedX(-1*SNAKESPEED);
            snake.setSpeedY(0);
            snake.setDirection("left");
            for(Block block : snakeBlock){
                Point2D p = new Point2D.Double(snake.getPositionX(), snake.getPositionY());
                block.addTurn(p);
                Point2D v = new Point2D.Double(snake.getSpeedX(), snake.getSpeedY());
                block.addVelocityTurn(v);

            }
        }
        if(keyHandler.isRightPressed()){
            snake.setSpeedX(SNAKESPEED);
            snake.setSpeedY(0);
            snake.setDirection("right");

            for(Block block : snakeBlock){
                Point2D p = new Point2D.Double(snake.getPositionX(), snake.getPositionY());
                block.addTurn(p);
                Point2D v = new Point2D.Double(snake.getSpeedX(), snake.getSpeedY());
                block.addVelocityTurn(v);

            }
            if(startGame == false){
                b1.setSpeedX(snake.getSpeedX());
                b1.setSpeedY(snake.getSpeedY());
                startGame = true;
            }
        }
        if (snakeBlock.size() > 1) {
            Block newBlock = snakeBlock.get(snakeBlock.size() - 1);
            Block lastBlock = snakeBlock.get(snakeBlock.size() - 2);

            // If the new block's speed is 0, check for separation
            if (newBlock.getSpeedX() == 0 && newBlock.getSpeedY() == 0) {
                // If the last block and new block are no longer overlapping, copy the velocity
                if (!newBlock.checkCollisionRect(
                        lastBlock.getPositionX(), lastBlock.getPositionY(), lastBlock.getWidth(), lastBlock.getHeight(),
                        newBlock.getPositionX(), newBlock.getPositionY(), newBlock.getWidth(), newBlock.getHeight())) {

                    // Set the new block's velocity to match the last block
                    newBlock.setSpeedX(lastBlock.getSpeedX());
                    newBlock.setSpeedY(lastBlock.getSpeedY());
                }
            }
        }




    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        snake.draw(g2d);
        for (Apple apple : apples) {
            apple.draw(g2d);
        }


        for (Block block : snakeBlock) {
            block.draw(g2d);
            count++;
        }

        // Set the font and color for the score
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.BLACK);

        // Display the score at the top-left corner
        g2d.drawString("Score: " + score, 20, 30);



    }


    public void addNewBlock() {
        // Get the last block of the snake
        Block lastBlock = snakeBlock.get(snakeBlock.size() - 1);

        // Create the new block at the same position as the last block
        Block newBlock = new Block(lastBlock.getPositionX(), lastBlock.getPositionY(),
                0, 0, lastBlock.getWidth(), lastBlock.getHeight());

        // Add the same turns and velocity turns to the new block
        for (Point2D turn : lastBlock.getTurns()) {
            newBlock.addTurn(turn);
        }
        for (Point2D velocityTurn : lastBlock.getVelocityTurns()) {
            newBlock.addVelocityTurn(velocityTurn);
        }

        // Append the new block to the snakeBlock list
        snakeBlock.add(newBlock);
    }

    public void relocateApple() {
        apple.setPositionX(rand.nextInt(screenHeight));
        apple.setPositionY(rand.nextInt(screenHeight));
    }

    public void checkEnd(){
        if(snake.getPositionX()<0 || snake.getPositionY()<0 || snake.getPositionX() > screenWidth || snake.getPositionY() > screenHeight){
            System.exit(0);
        }
        for (int i=1; i<snakeBlock.size(); i++) {

            if(snake.checkCollisionRect(snake.getPositionX(), snake.getPositionY(), snake.getWidth(), snake.getHeight(),
                    snakeBlock.get(i).getPositionX(), snakeBlock.get(i).getPositionY(), snakeBlock.get(i).getWidth(), snakeBlock.get(i).getHeight()
                    )){
                sound.playSound("/Users/rohitsattuluri/IdeaProjects/snake/res/gameOver2.wav");
                System.exit(0);
            }
        }
    }



}
