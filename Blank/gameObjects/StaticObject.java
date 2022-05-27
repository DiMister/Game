package gameObjects;

import java.awt.*;

public class StaticObject extends ImageObject
{
    String type;
    int id;
    public StaticObject(int x,int y,String type,int id)
    {
        super(x,y,Toolkit.getDefaultToolkit().getImage("images/objects/"+type+"/"+id+".png"));
        this.type = type;
        this.id = id;
    }
    
    @Override
    public String toString(){return type.substring(0,1)+id+"-"+getX()+"-"+getY();}


}
