import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Apple extends sprite{

    private int radius;
    private int width;
    private int height;
    private Image appleImage;

    public Apple(int x, int y, int r){
        super(x,y);
        radius = r;
        width = radius;
        height = radius;

        try {
            // Load the image (make sure to provide the correct file path)
            appleImage = ImageIO.read(new File("/Users/rohitsattuluri/IdeaProjects/snake/res/apple.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g){
        g.drawImage(appleImage, getPositionX(), getPositionY(), radius , radius , null);
    }

    public int getRadius(){
        return radius;
    }


}
