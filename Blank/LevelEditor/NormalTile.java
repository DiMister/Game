package LevelEditor;

 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class NormalTile extends Tile
{
    
    public NormalTile()
    {
        super(Toolkit.getDefaultToolkit().getImage("images/normal.png"));
    }

    public String toString(){return "n";}
}
