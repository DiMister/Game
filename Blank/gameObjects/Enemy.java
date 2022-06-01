package gameObjects;

import java.awt.*;
import java.util.concurrent.TimeUnit;


public class Enemy extends Moving implements Runnable
{
    boolean facingRight = true;
    boolean attacking = false;
    
    public Enemy(int x,int y)
    {
        super(x,y,200,1,new int[]{80,50,40,100},1);
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
                    }else if(randomDir == 1) {
                        dirX = -speed;
                        facingRight = false;
                    }else if(randomDir == 2) dirY = speed;
                    else if(randomDir == 3) dirY = -speed;
                    randomDir = (int)(Math.random() * 5);
                    if(randomDir == 0) attacking = true;
                }
            }
        };
        sort.start();
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
            
            if(attacking){
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