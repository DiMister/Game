package LevelEditor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class CreateGraphics extends JPanel
{
    Tile[][] map;
    ArrayList<Enemy> enemies;

    public CreateGraphics(Tile[][] tiles, ArrayList<Enemy> enemies)
    {
        //tommyinit
        map = tiles;
        this.enemies = enemies;
        setBackground(Color.gray);
    }
    
    public void updateReset(Tile[][] tiles, ArrayList<Enemy> enemies)
    {
        map = tiles;
        this.enemies = enemies;
    }

    public void paint (Graphics g)         
    { 
        super.paint(g); 
        //displaces entire graph (for fun put this in front of super.paint(g)
        //g.translate(disX,disY);
        
        //draws tiles from tile map
        for(int index = 0; index < map.length; index++){
          for(int i = 0; i < map[0].length; i++){
                Tile temp = map[index][i];
                //skip if null
                if(temp != null) {
                    g.drawImage(temp.getImage(),30*index,30*i,30,30,null);
                }
                //g.drawString(""+i+","+index,30*index,30*i);
            }
        }
        
        //draw enemies
        for(Enemy e : enemies){
            g.drawImage(e.getImage(),e.getX(),e.getY(),e.getSize(),e.getSize(),null);
        }
        
        //draw grid
        for(int index = 0; index < map.length; index++){
          for(int i = 0; i < map[0].length; i++){
                g.setColor(Color.black);
                g.drawRect(30*index,30*i,30,30);
            }
        }
    }
}
