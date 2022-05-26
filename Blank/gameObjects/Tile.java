package gameObjects;

 
 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Tile
{
    private Image image;
    private int id;
    private boolean walkable;
    //normal id is 0, water id is 1, wall id is 2, spike id is 3, lava id is 4
    
    public Tile(Image image, int id)
    {
        this.image = image;
        this.id = id;
        
        if (id == 0) walkable = true;
        else if (id == 1) walkable = false;
        else if (id == 2) walkable = false;
        else if (id == 3) walkable = false;
        else if (id == 4) walkable = false;
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
    
    public boolean isWalkable() {return walkable;}

    public String toString(){return "";}
}
