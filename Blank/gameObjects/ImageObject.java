package gameObjects;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

public class ImageObject
{
    protected int size; 
    protected double ratio,x,y;
    protected Image image;
    protected BoundingBox boundingBox;

    public ImageObject(int x, int y, int size, Image image, int[] boundingBox)
    {
        //constor for moving objects much more complex
        MediaTracker tracker = new MediaTracker(new Component(){}); // not sure if this is the right thing to do.
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {e.printStackTrace();}
        
        this.x = x;
        this.y = y;
        this.size = size;
        ratio = image.getHeight(null)/(double)image.getWidth(null);
        this.boundingBox = new BoundingBox(image,boundingBox,size);
    }

    public ImageObject(int x, int y, Image image) {
        //constor for static objects tried to simplfy as much as possable
        
        //this is to wait for image to load fully so getWidth isn't null
        MediaTracker tracker = new MediaTracker(new Component(){}); // not sure if this is the right thing to do.
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {e.printStackTrace();}
        
        this.x = x;
        this.y = y;
        this.image = image;
        size = image.getWidth(null)*2;
        ratio = image.getHeight(null)/(double)image.getWidth(null);
        //System.out.println(ratio+" "+size);
        boundingBox = new BoundingBox(image,new int[]{0,0,image.getWidth(null),image.getHeight(null)},size);
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public void setX(double newX) {x = newX;}

    public int getX() {return (int)(x+0.5);}

    public void setY(double newY) {y = newY;}
    
    public int getY() {return (int)(y+0.5);}
    
    

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

        double X = x-(boundingBox.getWidth()/2);
        double Y = y-(boundingBox.getHeight()/2);

        g.fillOval((int)x-5,(int)y-5,10,10);
        g.drawRect((int)X,(int)Y,boundingBox.getWidth(),boundingBox.getHeight());
    }

    public void drawImageArea(Graphics g) {
        g.setColor(Color.red);

        double X = x-boundingBox.getX()-(boundingBox.getWidth()/2);
        double Y = y-boundingBox.getY()-(boundingBox.getHeight()/2);

        g.drawRect((int)X,(int)Y,getWidth(),getHeight());
    }

    public void drawImage(Graphics g) {
        //much comlacated maths -sawyer
        Image image = getImage();

        double X = x-boundingBox.getX()-(boundingBox.getWidth()/2);
        double Y = y-boundingBox.getY()-(boundingBox.getHeight()/2);

        g.drawImage(image,(int)X,(int)Y,getWidth(),getHeight(),null);
    }

    public BoundingBox getBoundingBox() {return boundingBox;}

    public boolean isColliding(ImageObject other) {
        return boundingBox.isColliding(other.getBoundingBox(), getX(), getY(), other.getX(), other.getY());
    }
}