package gameObjects;
import java.awt.Image;


public class Movement extends Base 
{
    protected double speed, dirX=0, dirY=0;

    
    public Movement(int x, int y, int size, Image image, double ratio, double speed)
    {
        super(x,y,size,image,ratio);
        this.speed = speed;
    }

    public void move() {
        //System.out.println(dirX+", "+dirY);
        x+=dirX;
        y+=dirY;
    }
}
