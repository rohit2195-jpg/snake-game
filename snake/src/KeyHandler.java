import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean spacePressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP){
             upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }

    }

    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP){
            upPressed = false;

        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }




    }

    public boolean isUpPressed() {
        return upPressed;
    }
    public boolean isDownPressed() {
        return downPressed;
    }
    public boolean isLeftPressed() {
        return leftPressed;
    }
    public boolean isRightPressed() {
        return rightPressed;
    }
    public boolean isSpacePressed() {
        return spacePressed;
    }



}
