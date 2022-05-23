package LevelEditor;

 
 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Tile
{
    private Image image;
    public Tile(Image image)
    {
        this.image = image;
    }
    
    public Image getImage() {
        return image;
    }

    public String toString(){return "";}
}
