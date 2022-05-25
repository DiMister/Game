package LevelEditor;

import java.awt.*;
import java.util.concurrent.TimeUnit;


public class Base
{
    int x,y,size; 
    double ratio;
    Image image;
    
    
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

    public Thread setupAnamationThread(String imageDrectory) {
        Thread result = new Thread(){
            public void run(){
                Image[] images = FileMangement.createImageList(imageDrectory);
                int index = 0;
                while(true){
                    try{TimeUnit.MILLISECONDS.sleep(100);}
                    catch (InterruptedException ie){ie.printStackTrace();}
                    
                    setImage(images[index]);
                    index++;
                    if(index >= images.length) index = 0;
                }
            }
        };
        return result;
    }
}