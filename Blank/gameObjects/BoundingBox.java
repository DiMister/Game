package gameObjects;

import java.awt.*;

public class BoundingBox
{
    int rightX,y,width,height,leftX,x;
    public BoundingBox(Image image, int[] boundingBox, int size)
    {
        rightX = (int)(MoreMath.map(boundingBox[0],0,image.getWidth(null),0,size)+0.5);
        y = (int)(MoreMath.map(boundingBox[1],0,image.getHeight(null),0,size)+0.5);
        width = (int)(MoreMath.map(boundingBox[2],0,image.getWidth(null),0,size)+0.5);
        height = (int)(MoreMath.map(boundingBox[3],0,image.getHeight(null),0,size)+0.5);
        leftX = (int)(MoreMath.reverse(rightX,0,size)+0.5);
        x = rightX;
    }
    
    public int getX() {return x;}
    
    public int getY() {return y;}
    
    public int getWidth() {return width;}
    
    public int getHeight() {return height;}
    
    public void left() {x = leftX;}
    
    public void right() {x = rightX;}
        
}
