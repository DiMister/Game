package gameObjects;
import java.awt.*;
import java.util.concurrent.TimeUnit;


public class Player extends Movement implements Runnable
{
    //private Thread idle, attack;
    private boolean facingRight = true, attacking = false;
    public Player(int x, int y)
    {
        super(x,y,200,Toolkit.getDefaultToolkit().getImage("images/Player/idle/Warrior_Idle_1.png"),0.6875,4);       
    }
    
    public void attack() {
        attacking = true;
    }

    public void moveInput(String dir) {
        
        
        if(dir.equals("Up")){
            dirY = -speed;
        }else if(dir.equals("Down")){
            dirY = speed;
        }else if(dir.equals("Left")){
            dirX = -speed;
            facingRight = false;
        }else if(dir.equals("Right")){
            dirX = speed;
            facingRight = true;
        }
        firction = false;
    }

    public String toString(){return "p-"+getX()+"-"+getY();}

    @Override
    public void run() {
        Image[] images = FileMangement.createImageList("images/Player/idle");
        String current = "idle";
        int index = 0;
        while(true){
            try{TimeUnit.MILLISECONDS.sleep(100);}
            catch (InterruptedException ie){ie.printStackTrace();}
            
            if(attacking){
                if(facingRight)images = FileMangement.createImageList("images/Player/Attack");
                else images = FileMangement.createImageListFlip("images/Player/Attack");
                index = 0;
                current = "attack";
                while(index < images.length) {
                    try{TimeUnit.MILLISECONDS.sleep(100);}
                    catch (InterruptedException ie){ie.printStackTrace();}
                    
                    setImage(images[index]);
                    index++;
                }
                attacking = false;
            }else if(Math.abs(dirX) > 0.1 && !current.equals("run")){
                if(facingRight)images = FileMangement.createImageList("images/Player/Run");
                else images = FileMangement.createImageListFlip("images/Player/Run");
                current = "run";
                index = 0;
            }else if(!current.equals("idle") && firction) {
                if(facingRight)images = FileMangement.createImageList("images/Player/idle");
                else images = FileMangement.createImageListFlip("images/Player/idle");
                current = "idle";
                index = 0;
            }
            
            try{
                setImage(images[index]);
            }catch(ArrayIndexOutOfBoundsException e){e.printStackTrace();}
            index++;
            if(index >= images.length) index = 0;
        }
    }

}
