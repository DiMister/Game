package LevelEditor;

 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class LavaTile extends Tile
{
    
    public LavaTile()
    {
        super(Toolkit.getDefaultToolkit().getImage("images/lava.png"), 4);
    }

    public String toString(){return "l";}
}
