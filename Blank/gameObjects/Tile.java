package gameObjects;

 
 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Tile
{
    private Image image;
    private int id;
    //normal id is 0, water id is 1, wall id is 2, spike id is 3, lava id is 4
    
    public Tile(Image image, int id)
    {
        this.image = image;
        this.id = id;
    }
    
    public Image getImage() {
        return image;
    }
    
    public int id() {return id;}
    
    public boolean equals(Tile other) {
        return id == other.id();
    };
    
    public boolean equals(int newID) {
        return id == newID;
    };

    public String toString(){return "";}
}
