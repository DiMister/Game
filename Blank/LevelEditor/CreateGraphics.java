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
    int tileSize;
    
    public CreateGraphics(Tile[][] tiles, ArrayList<Enemy> enemies, Point playerSpawn, int tileSize)
    {
        //tommyinit
        map = tiles;
        this.enemies = enemies;
        this.playerSpawn = playerSpawn;
        this.tileSize = tileSize;
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
                    g.drawImage(temp.getImage(),tileSize*index,tileSize*i,tileSize,tileSize,null);
                }
                //g.drawString(""+i+","+index,30*index,30*i);
            }
        }
        
        //draw grid
        for(int index = 0; index < map.length; index++){
          for(int i = 0; i < map[0].length; i++){
                g.setColor(Color.gray);
                g.drawRect(tileSize*index,tileSize*i,tileSize,tileSize);
            }
        }
        
        //draw enemies
        for(Enemy e : enemies){
            Image image = e.getImage();
            g.drawImage(image,e.getX()-(image.getWidth(null)/2),e.getY()-(image.getHeight(null)/2),e.getWidth(),e.getHeight(),null);
        }
        
        if(playerSpawn != null) {
            g.setColor(Color.red);
            g.fillOval(playerSpawn.x-15,playerSpawn.y-15,30,30);
        }

    }
}
