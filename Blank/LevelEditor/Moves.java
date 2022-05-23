package LevelEditor;

public abstract class Moves extends Base
{
    private int speed,hp;
    public Moves(int x,int y,int speed,int hp)
    {
       super(x,y);
       this.speed = speed;
       this.hp = hp;
    }
    
    public void hit(){
      hp--;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public int getHp() {
        return hp;
    }
}