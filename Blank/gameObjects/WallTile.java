package gameObjects;

 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class WallTile extends Tile
{
    
    public WallTile()
    {
       super(Toolkit.getDefaultToolkit().getImage("images/wall.png"), 2);
    }

    public String toString(){return "w";}
}
