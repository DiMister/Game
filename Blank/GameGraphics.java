import gameObjects.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class GameGraphics extends JPanel
{
    Map map;
    ArrayList<Enemy> enemies;
    Player player;
    int screenH,screenW;
    
    public GameGraphics(Map tiles, ArrayList<Enemy> enemies, Player player, Dimension ss)
    {
        //tommyinit
        map = tiles;
        this.enemies = enemies;
        this.player = player;
        screenH = ss.height;
        screenW = ss.width;
        setBackground(Color.black);
    }
    

    public void paint (Graphics g)         
    { 
        super.paint(g); 
        //displaces entire graph (for fun put this in front of super.paint(g)
        g.translate((screenW/2)-(player.getX()),(screenH/2)-(player.getY()));
        
        //draws tiles from tile map
        map.draw(g);
        map.drawGrid(g);
        
       
        //draw enemies
        for(Enemy e : enemies){
            e.drawImage(g);
            //e.drawBoundingBox(g);
            //e.drawImageArea(g);
        }
        
        //draw player
        
        player.drawImage(g);
        //player.drawBoundingBox(g);
        //player.drawImageArea(g);
        

    }
    
    
    
}
