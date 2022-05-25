import LevelEditor.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class GameGraphics extends JPanel
{
    Tile[][] map;
    ArrayList<Enemy> enemies;
    Player player;
    int tileSize,screenH,screenW;
    
    public GameGraphics(Tile[][] tiles, ArrayList<Enemy> enemies, Player player, int tileSize, Dimension ss)
    {
        //tommyinit
        map = tiles;
        this.enemies = enemies;
        this.player = player;
        this.tileSize = tileSize;
        screenH = ss.height;
        screenW = ss.width;
        setBackground(Color.black);
    }
    

    public void paint (Graphics g)         
    { 
        super.paint(g); 
        //displaces entire graph (for fun put this in front of super.paint(g)
        g.translate((screenW/2)-(map.length*tileSize/2),(screenH/2)-(map[0].length*tileSize/2)-50);
        
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
        
        //draw enemies
        for(Enemy e : enemies){
            newDrawImage(g,e);
        }
        
        //draw player
        
        newDrawImage(g,player);
        

    }
    
    private void newDrawImage(Graphics g, Base object) {
        Image image = object.getImage();

        double x = object.getX()-(image.getWidth(null)/2);
        double y = object.getY()-(image.getHeight(null)/2);
        
        //System.out.println(object.getWidth() + "," + object.getHeight());
        g.drawImage(image,(int)x,(int)y,object.getWidth(),object.getHeight(),null);
    }

}
