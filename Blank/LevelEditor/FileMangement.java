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

    public static void saveFile(Tile[][] map, ArrayList<Enemy> enemies, Point playerSpawn, String fileName) {
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
            bwriter.newLine();
            bwriter.write("p-"+playerSpawn.x+"-"+playerSpawn.y);
            bwriter.close();                       //Must always close the file when finished writing to it.  
        }
        catch(IOException ex) {}
    }

    public static ArrayList<String> readFile(String fileName) {
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
        
        return lines;
    }
    
    public static Image[] createImageList(String drectory) {
        File file = new File(drectory);
        String[] fileNames = file.list();
        
        Image[] result = new Image[fileNames.length];
        for(int index = 0; index < fileNames.length; index++) {
            try{
                System.out.println(fileNames[index]);
                FileInputStream fis = new FileInputStream(drectory+"/"+fileNames[index]);
                Image image = ImageIO.read(fis);
                
                result[index] = image;
            }
            catch (IOException ex){System.out.println("Error with creating image list");}
        }
        return result;
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

    
