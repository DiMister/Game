package gameObjects;

import java.awt.*;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

public class StaticObject extends ImageObject
{
    String type;
    int id;
    
    public StaticObject(int x,int y,String type,int id) 
    {
        super(x,y,Toolkit.getDefaultToolkit().getImage("images/Objects/"+type+"/"+id+".png"));
        this.type = type;

    }
    
    public String getType() {
        return type;
    }
    
    public int getID() {
        return id;
    }
    
    @Override
    public String toString(){return "\""+type+"-"+id+"\"";}


}
