import gameObjects.*;

public class Map
{
    private static final int tileSize = 40;
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
}
