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
        this.id = id;
        //if(Toolkit.getDefaultToolkit().getImage("images/Objects/"+type+"/"+id+".png") != null) System.out.println("Image Found!");
        //System.out.println(getWidth()+","+getHeight());
    }
    
    @Override
    public String toString(){return "\""+type.substring(0,1)+id+"\"";}


}
