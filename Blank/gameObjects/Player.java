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
                while(index < images.length-1) {
                    try{TimeUnit.MILLISECONDS.sleep(50);}
                    catch (InterruptedException ie){ie.printStackTrace();}
                    dirX = 0;
                    dirY = 0;
                    setImage(images[index]);
                    index++;
                }
                attacking = false;
            }else if(facingRight){
                if((dirX > 0 || Math.abs(dirY) > 0) && !current.equals("runRight")){
                    images = FileMangement.createImageList("images/Player/run");
                    current = "runRight";
                    index = 0;
                }else if(firction && !current.equals("idle")){
                    images = FileMangement.createImageList("images/Player/idle");
                    current = "idle";
                    index = 0;
                }
            }else{
                 if((dirX < 0 || Math.abs(dirY) > 0) && !current.equals("runLeft")){
                    images = FileMangement.createImageListFlip("images/Player/run");
                    current = "runLeft";
                    index = 0;
                }else if(firction && !current.equals("idle")){
                    images = FileMangement.createImageListFlip("images/Player/idle");
                    current = "idle";
                    index = 0;
                }
            }
            
            setImage(images[index]);
            index++;
            if(index >= images.length) index = 0;
        }
    }

}
