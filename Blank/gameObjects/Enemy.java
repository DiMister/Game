package gameObjects;

import java.awt.*;
import java.util.concurrent.TimeUnit;


public class Enemy extends Complex implements Runnable
{
    boolean facingRight = true, dead = false;
    
    public Enemy(int x,int y)
    {
        super(x,y,200,FileMangement.getImage("Skeleton Enemy/idle/idle1"),new int[]{22,16,16,33},1,new int[]{38,16,25,33},5);
        Thread anamator = new Thread(this);
        anamator.start();
        startRandomMovement();
    }
    
    public void startRandomMovement() {
        Thread sort = new Thread(){
            public void run(){
                while(true){
                    try{TimeUnit.SECONDS.sleep(3);}
                    catch(InterruptedException e) {} 
                    dirX=0;
                    dirY=0;
                    
                    int randomDir = (int)(Math.random() * 4);
                    if(randomDir == 0) {
                        dirX = speed;
                        facingRight = true;
                        boundingBox.right();
                        attack.right();
                    }else if(randomDir == 1) {
                        dirX = -speed;
                        facingRight = false;
                        boundingBox.left();
                        attack.left();
                    }else if(randomDir == 2) dirY = speed;
                    else if(randomDir == 3) dirY = -speed;
                    randomDir = (int)(Math.random() * 5);
                    //if(randomDir == 0) attacking = true;
                    attack();
                }
            }
        };
        sort.start();
    }
    
    public boolean isDead() {
        return dead;
    }
    @Override
    public String toString(){return "\"s\"";}
    
    @Override
    public void run() {
        Image[] images = FileMangement.createImageList("images/Skeleton Enemy/idle");
        String current = "idle";
        int index = 0;
        while(true){
            try{TimeUnit.MILLISECONDS.sleep(100);}
            catch (InterruptedException ie){ie.printStackTrace();}
            
            if(health <= 0) {
                if(facingRight)images = FileMangement.createImageList("images/Skeleton Enemy/death");
                else images = FileMangement.createImageListFlip("images/Skeleton Enemy/death");
                index = 0;
                current = "death";
                while(index < images.length-1) {
                    try{TimeUnit.MILLISECONDS.sleep(200);}
                    catch (InterruptedException ie){ie.printStackTrace();}
                    dirX = 0;
                    dirY = 0;
                    setImage(images[index]);
                    index++;
                }
                dead = true;
            }if(hit) {
                if(facingRight)images = FileMangement.createImageList("images/Skeleton Enemy/hit");
                else images = FileMangement.createImageListFlip("images/Skeleton Enemy/hit");
                index = 0;
                current = "hit";
                while(index < images.length-1) {
                    try{TimeUnit.MILLISECONDS.sleep(200);}
                    catch (InterruptedException ie){ie.printStackTrace();}
                    dirX = 0;
                    dirY = 0;
                    setImage(images[index]);
                    index++;
                }
                hit = false;
            }else if(attacking){
                if(facingRight)images = FileMangement.createImageList("images/Skeleton Enemy/Attack");
                else images = FileMangement.createImageListFlip("images/Skeleton Enemy/Attack");
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
                    images = FileMangement.createImageList("images/Skeleton Enemy/walk");
                    current = "runRight";
                    index = 0;
                }else if(dirX == 0 && dirY == 0 && !current.equals("idle")){
                    images = FileMangement.createImageList("images/Skeleton Enemy/idle");
                    current = "idle";
                    index = 0;
                }
            }else{
                 if((dirX < 0 || Math.abs(dirY) > 0) && !current.equals("runLeft")){
                    images = FileMangement.createImageListFlip("images/Skeleton Enemy/walk");
                    current = "runLeft";
                    index = 0;
                }else if(dirX == 0 && dirY == 0 && !current.equals("idle")){
                    images = FileMangement.createImageListFlip("images/Skeleton Enemy/idle");
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