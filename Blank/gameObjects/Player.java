package gameObjects;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;


public class Player extends Complex implements Runnable
{
    //private Thread idle, attack;
    private boolean facingRight = true;
    private double diaSpeed = Math.round(Math.sqrt(Math.pow(speed, 2)/2)*100)/100.0;
    public Player(int x, int y)
    {
        super(x,y,200,FileMangement.getImage("Player/idle/Warrior_Idle_1"),new int[]{20,12,12,30},4,new int[]{32,8,30,30},5); 
        Thread anamator = new Thread(this);
        anamator.start();

    }

    public void moveInput(int dir) {
        //1 is up, 2 is right, 3 is down, 4 is left
        if(attacking) return;
        if(hit) return;
        
        boolean movingX, movingY;
        
        if (dirX != 0) movingX = true;
        else movingX = false;
        
        if (dirY != 0) movingY = true;
        else movingY = false;
        
        if(dir == 1){
            if (movingX) {
                dirX = diaSpeed*(dirX/Math.abs(dirX));
                dirY = -diaSpeed;
            } else dirY = -speed;
            return;
        }
        
        if(dir == 2){
            facingRight = true;
            boundingBox.right();
            attack.right();
            if (movingY) {
                dirY = diaSpeed*(dirY/Math.abs(dirY));
                dirX = diaSpeed;
            } else dirX = speed;
            return;
        }
        
        if(dir == 3){
            if (movingX) {
                dirX = diaSpeed*(dirX/Math.abs(dirX));
                dirY = diaSpeed;
            } else dirY = speed;
            return;
        }
        
        if(dir == 4){
            facingRight = false;
            boundingBox.left();
            attack.left();
            if (movingY) {
                dirY = diaSpeed*(dirY/Math.abs(dirY));
                dirX = -diaSpeed;
            } else dirX = -speed;
            return;
        }
        
        if(dir == -1 || dir == -3){
            if (movingX)
                dirX = speed*(dirX/Math.abs(dirX));
            dirY = 0;
            return;
        }
        
        if(dir == -2 || dir == -4){
            if (movingY)
                dirY = speed*(dirY/Math.abs(dirY));
            dirX = 0;
            return;
        }    
    }
    

    @Override
    public String toString(){return "p-"+getX()+"-"+getY();}

    @Override
    public void run() {
        Image[] images = FileMangement.createImageList("images/Player/idle");
        String current = "idle";
        int index = 0;
        while(true){
            try{TimeUnit.MILLISECONDS.sleep(100);}
            catch (InterruptedException ie){ie.printStackTrace();}
            
            if(hit){
                if(facingRight)images = FileMangement.createImageList("images/Player/Death-Effect");
                else images = FileMangement.createImageListFlip("images/Player/Death-Effect");
                index = 0;
                dirX = 0;
                dirY = 0;
                current = "hit";
                while(index < images.length-1) {
                    try{TimeUnit.MILLISECONDS.sleep(100);}
                    catch (InterruptedException ie){ie.printStackTrace();}
                    
                    setImage(images[index]);
                    index++;
                }
                hit = false;
            }else if(attacking){
                if(facingRight)images = FileMangement.createImageList("images/Player/Attack");
                else images = FileMangement.createImageListFlip("images/Player/Attack");
                index = 0;
                dirX = 0;
                dirY = 0;
                current = "attack";
                while(index < images.length-1) {
                    try{TimeUnit.MILLISECONDS.sleep(50);}
                    catch (InterruptedException ie){ie.printStackTrace();}
                    
                    setImage(images[index]);
                    index++;
                }
                attacking = false;
            }else if(facingRight){
                if((dirX > 0 || Math.abs(dirY) > 0) && !current.equals("runRight")){
                    images = FileMangement.createImageList("images/Player/run");
                    current = "runRight";
                    index = 0;
                }else if(dirX == 0 && dirY == 0 && !current.equals("idle")){
                    images = FileMangement.createImageList("images/Player/idle");
                    current = "idle";
                    index = 0;
                }
            }else{
                 if((dirX < 0 || Math.abs(dirY) > 0) && !current.equals("runLeft")){
                    images = FileMangement.createImageListFlip("images/Player/run");
                    current = "runLeft";
                    index = 0;
                }else if(dirX == 0 && dirY == 0 && !current.equals("idle")){
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
