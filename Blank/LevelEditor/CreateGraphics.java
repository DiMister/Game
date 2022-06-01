package LevelEditor;

import gameObjects.*;
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
    ArrayList<StaticObject> objects;
    
    public CreateGraphics(Tile[][] tiles, ArrayList<Enemy> enemies, ArrayList<StaticObject> objects, Point playerSpawn, int tileSize)
    {
        //tommyinit
        map = tiles;
        this.enemies = enemies;
        this.playerSpawn = playerSpawn;
        this.tileSize = tileSize;
        this.objects = objects;
        setBackground(Color.black);
    }
    
    public void updateReset(Tile[][] tiles, ArrayList<Enemy> enemies, ArrayList<StaticObject> objects, Point playerSpawn)
    {
        map = tiles;
        this.enemies = enemies;
        this.objects = objects;
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
            }
        }
        
        //draw grid
        for(int index = 0; index < map.length; index++){
          for(int i = 0; i < map[0].length; i++){
                g.setColor(Color.gray);
                g.drawRect(tileSize*index,tileSize*i,tileSize,tileSize);
            }
        }
        
        for(StaticObject obj : objects) {
            //g.setColor(Color.green);
            //g.fillRect(obj.getX()-obj.getWidth()/2,obj.getY()-obj.getHeight()/2,obj.getWidth(),obj.getHeight());
            obj.drawImage(g);
        }
        
        //draw enemies
        for(Enemy e : enemies){
            g.setColor(Color.red);
            g.fillRect(e.getX()-100,e.getY()-100,e.getWidth(),e.getHeight());
        }
        
        if(playerSpawn != null) {
            g.setColor(Color.red);
            g.fillOval(playerSpawn.x-15,playerSpawn.y-15,30,30);
        }

    }
}
