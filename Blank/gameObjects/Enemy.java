package gameObjects;

import java.awt.*;

public class Enemy extends Movement
{
    private Thread attack, idle;
    public Enemy(int x,int y)
    {
        super(x,y,200,Toolkit.getDefaultToolkit().getImage("images/Skeleton Enemy/idle/idle1.png"),1,1);
        idle = setupAnamationThread("images/Skeleton Enemy/idle");
        attack = setupAnamationThread("images/Skeleton Enemy/attack");
    }
    
    public void playIdle() {idle.start();}
    
    public void playAttack() {attack.start();}
    
    public String toString(){return "s-"+getX()+"-"+getY();}
    
}