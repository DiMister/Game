package gameObjects;
import java.awt.*;


public class Complex extends ImageObject 
{
    protected double speed, dirX=0, dirY=0;
    protected boolean attacking = false, hit = true ;
    protected BoundingBox attack;
    protected int health;
    
    public Complex(int x, int y, int size, Image image, int[] boundingBox, double speed, int[] attackBounds, int health)
    {
        super(x,y,size,image,boundingBox);
        attack = new BoundingBox(image,attackBounds,size);
        this.speed = speed;
        this.health = health;
    }

    public void move() {
        //System.out.println(dirX);
        x+=dirX;
        y+=dirY;
        
    }
    
    public void attack() {
        attacking = true;
    }
    
    public void hit() {
        health--;
        hit = true;
    }
    
    public boolean isHitting(ImageObject obj) {
        if(attacking) return attack.isColliding(obj.getBoundingBox(),getX(),getY(),obj.getX(),obj.getY());
        return false;
    }
    
    public void drawAttack(Graphics g) {
        g.setColor(Color.black);

        double X = x-boundingBox.getX()-(boundingBox.getWidth()/2)+attack.getX();
        double Y = y-boundingBox.getY()-(boundingBox.getHeight()/2)+attack.getY();

        g.drawRect((int)X,(int)Y,attack.getWidth(),attack.getHeight());
    }
}
