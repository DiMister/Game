package gameObjects;
import java.awt.Image;


public class Movement extends Base 
{
    protected double speed, dirX=0, dirY=0;
    protected boolean firction;
    
    public Movement(int x, int y, int size, Image image, double ratio, double speed)
    {
        super(x,y,size,image,ratio);
        this.speed = speed;
    }

    public void move() {
        //System.out.println(dirX);
        x+=dirX;
        y+=dirY;
        if(firction){
            if(dirX > 0.1)dirX/=1.3;
            else dirX = 0;
            if(dirY > 0.1)dirY/=1.3;
            else dirY = 0;
        }
    }
    
    public void stop() {
        firction = true;
    }
}
