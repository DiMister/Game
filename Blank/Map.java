import gameObjects.*;
import java.awt.*;

public class Map
{
    private static final int tileSize = 80;
    private Tile[][] map;
    
    public Map(String[] lines)
    {
        map = new Tile[lines.length][lines[0].length()];
        
        //reads each char and finds the tile that corsonds to it
        for(int index = 0; index < map.length; index++){
          for(int i = 0; i < map[0].length; i++){
                map[index][i] = findTile(lines[index].charAt(i));
            }
        }
    }

    private Tile findTile(char car) {
        if(car == 'n') return new NormalTile();
        if(car == 'w') return new WallTile();
        if(car == 'l') return new LavaTile();
        if(car == 'v') return new WaterTile();
        if(car == 's') return new SpikeTile(1);
        return null;
    }
    
    public int getRows() {return map.length;}
    
    public int getCols() {return map[0].length;}
    
    public void draw(Graphics g) {
        for(int index = 0; index < map.length; index++){
          for(int i = 0; i < map[0].length; i++){
                Tile temp = map[index][i];
                //skip if null
                if(temp != null) {
                    g.drawImage(temp.getImage(),tileSize*index,tileSize*i,tileSize,tileSize,null);
                }
            }
        }
    }
    
    public void drawGrid(Graphics g) {
        for(int index = 0; index < map.length; index++){
          for(int i = 0; i < map[0].length; i++){
                g.setColor(Color.gray);
                g.drawRect(tileSize*index,tileSize*i,tileSize,tileSize);
            }
        }
    }
}
