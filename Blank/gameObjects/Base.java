package gameObjects;

import java.awt.*;
import java.util.concurrent.TimeUnit;


public class Base
{
    protected int x,y,size; 
    protected double ratio;
    protected Image image;
    
    
    public Base(int x, int y, int size, Image image, double ratio)
    {
        this.x = x;
        this.y = y;
        this.size = size;
        this.image = image;
        this.ratio = ratio;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return size;
    }
    
    public int getHeight() {
        return (int)(size*ratio);
    }
    
    public Image getImage() {
        return image;
    }

}