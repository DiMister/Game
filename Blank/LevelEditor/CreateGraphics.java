package LevelEditor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class CreateGraphics extends JPanel
{
    Tile[][] map;
    ArrayList<Enemy> enemies;
    Point playerSpawn;
    
    public CreateGraphics(Tile[][] tiles, ArrayList<Enemy> enemies, Point playerSpawn)
    {
        //tommyinit
        map = tiles;
        this.enemies = enemies;
        this.playerSpawn = playerSpawn;
        setBackground(Color.black);
    }
    
    public void updateReset(Tile[][] tiles, ArrayList<Enemy> enemies, Point playerSpawn)
    {
        map = tiles;
        this.enemies = enemies;
        this.playerSpawn = playerSpawn;
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
        
        //draw grid
        for(int index = 0; index < map.length; index++){
          for(int i = 0; i < map[0].length; i++){
                g.setColor(Color.gray);
                g.drawRect(30*index,30*i,30,30);
            }
        }
        
        //draw enemies
        for(Enemy e : enemies){
            g.drawImage(e.getImage(),e.getX(),e.getY(),e.getSize(),e.getSize(),null);
        }
        
        if(playerSpawn != null) {
            g.setColor(Color.red);
            g.fillOval(playerSpawn.x,playerSpawn.y,30,30);
        }

    }
}
