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
        g.translate((screenW/2)-(map.getRows()*tileSize/2)-(player.getX()/2),(screenH/2)-(map.getCols()*tileSize/2)-50-(player.getY()/2));
        
        //draws tiles from tile map
        map.draw(g);
        map.drawGrid(g);
        
       
        //draw enemies
        for(Enemy e : enemies){
            newDrawImage(g,e);
        }
        
        //draw player
        
        drawPlayer(g,player);
        

    }
    
    private void newDrawImage(Graphics g, Base object) {
        Image image = object.getImage();

        double x = object.getX()-(object.getWidth()/2);
        double y = object.getY()-(object.getHeight()/2);
        
        //System.out.println(object.getWidth() + "," + object.getHeight());
        g.drawImage(image,(int)x,(int)y,object.getWidth(),object.getHeight(),null);
    }

    private void drawPlayer(Graphics g, Base object) {
        Image image = object.getImage();

        double x = (map.getRows()*tileSize-object.getWidth()+player.getX())/2;
        double y = (map.getCols()*tileSize-object.getHeight()+player.getY())/2;
        
        //System.out.println(x + "," + y);
        g.drawImage(image,(int)x,(int)y,object.getWidth(),object.getHeight(),null);
    }
}
