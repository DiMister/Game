package gameObjects;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

public class FileMangement
{

    public static void saveFileJSON(Tile[][] map, ArrayList<Enemy> enemies, ArrayList<StaticObject> objects, Point playerSpawn, String fileName) {
        JSONArray stringMap = new JSONArray();

        for(Tile[] row : map){
            String line = "";
            for(Tile tile : row){
                if(tile != null){
                    //all tile toString() print a letter
                    line+= tile;
                }else
                    line+= "-";
            }
            stringMap.add(line);
        }
        
        JSONArray enemyList = new JSONArray();
        
        for(Enemy e : enemies) {
            JSONObject enemy = new JSONObject();
            enemy.put("type",e);
            enemy.put("x",e.getX());
            enemy.put("y",e.getY());
            enemyList.add(enemy);
        }
        
        JSONArray objectList = new JSONArray();
        
        for(StaticObject obj : objects) {
            JSONObject object = new JSONObject();
            object.put("type",obj.getType());
            object.put("ID",obj.getID());
            object.put("x",obj.getX());
            object.put("y",obj.getY());
            objectList.add(object);
        }
        
        JSONObject player = new JSONObject();
        player.put("x",playerSpawn.x);
        player.put("y",playerSpawn.y);
        
        JSONObject json = new JSONObject();
        json.put("Map",stringMap);
        json.put("Enemies",enemyList);
        json.put("Player",player);
        json.put("Objects",objectList);
        
        try {
             FileWriter file = new FileWriter("saves/"+fileName+".json");
             file.write(json.toString());
             file.close();
             
        } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
        }
    }

    public static Image[] createImageList(String drectory) {
        File file = new File(drectory);
        String[] fileNames = file.list();

        Image[] result = new Image[fileNames.length];
        for(int index = 0; index < fileNames.length; index++) {
            try{
                FileInputStream fis = new FileInputStream(drectory+"/"+fileNames[index]);
                Image image = ImageIO.read(fis);

                //System.out.print(fileNames[index]+": ");
                //trim(ImageIO.read(new File(drectory+"/"+fileNames[index])));

                result[index] = image;
            }
            catch (IOException ex){System.out.println("Error with creating image list");}
        }
        return result;
    }

    public static Image[] createImageListFlip(String drectory) {
        File file = new File(drectory);
        String[] fileNames = file.list();

        Image[] result = new Image[fileNames.length];
        for(int index = 0; index < fileNames.length; index++) {
            try{
                //System.out.println(fileNames[index]);
                FileInputStream fis = new FileInputStream(drectory+"/"+fileNames[index]);
                BufferedImage image = ImageIO.read(fis);
                // Flip the image horizontally
                AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                tx.translate(-image.getWidth(null), 0);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                image = op.filter(image, null);

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

    public static BufferedImage trim(BufferedImage image) {
        int minY = 0, maxY = 0, minX = Integer.MAX_VALUE, maxX = 0;
        boolean isBlank, minYIsDefined = false;
        Raster raster = image.getRaster();

        for (int y = 0; y < image.getHeight(); y++) {
            isBlank = true;

            for (int x = 0; x < image.getWidth(); x++) {
                //Change condition to (raster.getSample(x, y, 3) != 0) 
                //for better performance
                if (raster.getPixel(x, y, (int[]) null)[3] != 0) {
                    isBlank = false;

                    if (x < minX) minX = x;
                    if (x > maxX) maxX = x;
                }
            }

            if (!isBlank) {
                if (!minYIsDefined) {
                    minY = y;
                    minYIsDefined = true;
                } else {
                    if (y > maxY) maxY = y;
                }
            }
        }

        System.out.println(minX+","+minY+" . "+ (maxX - minX + 1)+","+ (maxY - minY + 1));
        return image.getSubimage(minX, minY, maxX - minX + 1, maxY - minY + 1);
    }
}

