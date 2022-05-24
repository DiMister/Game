package LevelEditor;

 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class SpikeTile extends Tile
{
    int dam;
    public SpikeTile(int dam)
    {
        super(Toolkit.getDefaultToolkit().getImage("images/spike.png"));
        this.dam = dam;
    }

    public String toString(){return "s";}
}
