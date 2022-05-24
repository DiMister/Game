package LevelEditor;

 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class WaterTile extends Tile
{
    
    public WaterTile()
    {
        super(Toolkit.getDefaultToolkit().getImage("images/water.png"));
    }

    public String toString(){return "v";}
}
