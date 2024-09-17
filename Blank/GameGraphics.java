import gameObjects.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class GameGraphics extends JPanel
{
    Map map;
    ArrayList<StaticObject> objects;
    Player player;
    int screenH,screenW;
    
    public GameGraphics(Map tiles, ArrayList<StaticObject> objects, Player player, Dimension ss)
    {
        //tommyinit
        map = tiles;
        this.player = player;
        this.objects = objects;
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
        //map.drawGrid(g);
        
       
       
        
        for(StaticObject obj : objects) {
            obj.drawImage(g);
            //obj.drawBoundingBox(g);
            //obj.drawImageArea(g);            
        }
        
        //draw player
        
        player.drawImage(g);
        //player.drawAttack(g);
        //player.drawBoundingBox(g);
        //player.drawImageArea(g);
        

    }
    
    
    
}
