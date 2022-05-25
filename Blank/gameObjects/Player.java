package gameObjects;
import java.awt.*;

public class Player extends Movement
{
    private Thread idle, attack;
    public Player(int x, int y)
    {
        super(x,y,200,Toolkit.getDefaultToolkit().getImage("images/Player/idle/Warrior_Idle_1.png"),0.6875,1);       
        idle = setupAnamationThread("images/Player/idle");
        attack = setupAnamationThread("images/Player/attack");
    }
    
    public void playIdle() {idle.start();}
    
    public void playAttack() {attack.start();}
    
    public void moveInput(String dir) {
        System.out.println(dir);
        dirY = 0;
        dirX = 0;
        if(dir == "Up")
            dirY = -speed;
        else if(dir == "Down")
            dirY = speed;
        else if(dir == "Left")
            dirX = -speed;
        else if(dir == "Right")
            dirX = speed;
    }
    
    public String toString(){return "p-"+getX()+"-"+getY();}
    

    
}
