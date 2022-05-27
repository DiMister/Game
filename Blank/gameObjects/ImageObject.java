package gameObjects;

import java.awt.*;
import java.util.concurrent.TimeUnit;


public class ImageObject
{
    protected int x,y,size; 
    protected double ratio;
    protected Image image;
    protected int[] boundingBox;
    
    public ImageObject(int x, int y, int size, double ratio, int[] boundingBox)
    {
        //constor for moving objects much more complex
        this.x = x;
        this.y = y;
        this.size = size;
        this.ratio = ratio;
        this.boundingBox = boundingBox;
    }
    
    public ImageObject(int x, int y, Image image) {
        //constor for static objects tried to simplfy as much as possable
        this.x = x;
        this.y = y;
        this.image = image;
        size = image.getWidth(null)*2;
        ratio = image.getHeight(null)/image.getWidth(null);
        boundingBox = new int[]{x,y,image.getWidth(null),image.getHeight(null)};
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
    
    public void drawBoundingBox(Graphics g) {
        g.setColor(Color.black);
        
        double X = x-(boundingBox[2]/2);
        double Y = y-(boundingBox[3]/2);
        
        g.fillOval(x-5,y-5,10,10);
        g.drawRect((int)X,(int)Y,boundingBox[2],boundingBox[3]);
    }
    
    public void drawImageArea(Graphics g) {
        g.setColor(Color.red);
        
        double X = x-boundingBox[0]-(boundingBox[2]/2);
        double Y = y-boundingBox[1]-(boundingBox[3]/2);
        
        g.drawRect((int)X,(int)Y,getWidth(),getHeight());
    }

    public void drawImage(Graphics g) {
        //much comlacated maths -sawyer
        Image image = getImage();

        double X = x-boundingBox[0]-(boundingBox[2]/2);
        double Y = y-boundingBox[1]-(boundingBox[3]/2);
        
        g.drawImage(image,(int)X,(int)Y,getWidth(),getHeight(),null);
    }
    
    public int getColHeight() {return boundingBox[3];}
    
    public int getColWidth() {return boundingBox[2];}

    public boolean isColliding(ImageObject other) {
        boolean xLine = false, yLine = false;
        
        if ((other.getX()-other.getColWidth()/2 > x-getColWidth()/2 &&  other.getX()-other.getColWidth()/2 < x+getColWidth()/2) ||
            (other.getX()+other.getColWidth()/2 > x-getColWidth()/2 &&  other.getX()+other.getColWidth()/2 < x+getColWidth()/2))
            xLine = true;
        
        if ((other.getY()-other.getColHeight()/2 > y-getColHeight()/2 &&  other.getY()-other.getColHeight()/2 < y+getColHeight()/2) ||
            (other.getY()+other.getColHeight()/2 > y-getColHeight()/2 &&  other.getY()+other.getColHeight()/2 < y+getColHeight()/2))
            yLine = true;
            
        return xLine && yLine;
    }
}