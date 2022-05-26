package gameObjects;

 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class WaterTile extends Tile
{
    
    public WaterTile()
    {
        super(Toolkit.getDefaultToolkit().getImage("images/water.png"), 1);
    }

    public String toString(){return "v";}
}
