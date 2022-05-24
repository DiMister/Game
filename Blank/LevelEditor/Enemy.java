package LevelEditor;
import java.awt.Image;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Enemy extends Moves
{
    boolean dead = false;
    private Image image;
    
    public Enemy(int x,int y)
    {
        super(x,y,1,5,60);
        image = Toolkit.getDefaultToolkit().getImage("images/Skeleton Enemy/idle1.png");
    }
    
    public void dead(){dead = true;}
    
    public boolean getDead() {
        return dead;
    }
    
    
    public Image getImage() {
        return image;
    }
    
    public String toString(){return "s-"+getX()+"-"+getY();}
}