package LevelEditor;

import java.io.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class FileMangement
{

    public static void saveFile(Tile[][] map, ArrayList<Enemy> enemies, String fileName) {
        FileWriter fwriter; 
        BufferedWriter bwriter;

        File thefile = new File(fileName+".txt");  
        try
        {
            fwriter = new FileWriter(thefile);
            bwriter = new BufferedWriter(fwriter); 
            
            //for each tile in tile map adds letter in a grid in file
            for(Tile[] row : map){
                String line = "";
                for(Tile tile : row){
                    if(tile != null){
                        //all tile toString() print a letter
                        line+= tile;
                    }else
                        line+= "-";

                }
                bwriter.write(line);
                bwriter.newLine();
            }
            bwriter.newLine();
            for(Enemy emy: enemies){
                bwriter.write(""+emy);
                bwriter.newLine();
            }
            bwriter.close();                       //Must always close the file when finished writing to it.  
        }
        catch(IOException ex) {}
    }

    public static Tile[][] readMapFile(String fileName) {
        ArrayList<String> lines = new ArrayList<String>();
            
        try{
            FileInputStream fstream = new FileInputStream(fileName+".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String line;
            
            //adds lines of text from file to String[]
            while((line = br.readLine()) != null){
                lines.add(line);
            }
            br.close();
        }
        catch (IOException ex){}
        
        int split = lines.size();
        for(int index = 0; index < lines.size(); index++) {
            if(lines.get(index).equals("")) split = index;
        }
        
        Tile[][] map = new Tile[split][lines.get(0).length()];
        
        //reads each char and finds the tile that corsonds to it
        for(int index = 0; index < map.length; index++){
          for(int i = 0; i < map[0].length; i++){
                map[index][i] = findTile(lines.get(index).charAt(i));
            }
        }
        
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for(int index = split; index < lines.size(); index++) {
            String line = lines.get(index);
            String[] temp = new String[3];
            i = 0;
            for(char x : line) {
                if(x != '-') temp[i]+=x;
                else i++;
            }
            enemies.add(new Enemy(temp[0],temp[1],temp[2]);
        }
        
        return map;
    }
    
    private static Tile findTile(char car) {
        if(car == 'n') return new NormalTile();
        if(car == 'w') return new WallTile();
        if(car == 'l') return new LavaTile();
        if(car == 'v') return new WaterTile();
        if(car == 's') return new SpikeTile(1);
        return null;
    }
    
    private static Enemy findEnemy(char car) {
        
    }
    
    public static void splitImage(String fileName, int row, int col) {

        File file = new File(fileName); // I have bear.jpg in my working directory
        BufferedImage image = null;
        try{
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis); //reading the image file
            
    
            int rows = row; //You should decide the values for rows and cols variables
            int cols = col;
            int chunks = rows * cols;
    
            int chunkWidth = image.getWidth() / cols; // determines the chunk width and height
            int chunkHeight = image.getHeight() / rows;
            int count = 0;
            BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < cols; y++) {
                    //Initialize the image array with image chunks
                    imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
    
                    // draws the image chunk
                    Graphics2D gr = imgs[count++].createGraphics();
                    gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                    gr.dispose();
                }
            }
            System.out.println("Splitting done");
    
            //writing mini images into image files
            for (int i = 0; i < imgs.length; i++) {
                ImageIO.write(imgs[i], "png", new File("img" + i + ".png"));
            }
            System.out.println("Mini images created");
        }
        catch (IOException ex){System.out.println("Error with split image");}
    }
}

    
