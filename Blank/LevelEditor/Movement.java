package LevelEditor;
import java.awt.Image;


public class Movement extends Base 
{
    double speed, dirX=0, dirY=0;

    
    public Movement(int x, int y, int size, Image image, double ratio, double speed)
    {
        super(x,y,size,image,ratio);
        this.speed = speed;
    }

    public void Move() {
        x+=dirX;
        y+=dirY;
    }
}
