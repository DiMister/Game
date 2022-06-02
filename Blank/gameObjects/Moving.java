package gameObjects;
import java.awt.Image;


public class Moving extends ImageObject 
{
    protected double speed, dirX=0, dirY=0;
    
    public Moving(int x, int y, int size, Image image, int[] boundingBox, double speed)
    {
        super(x,y,size,image,boundingBox);
        this.speed = speed;
    }

    public void move() {
        //System.out.println(dirX);
        x+=dirX;
        y+=dirY;
        
    }
    
    

}
