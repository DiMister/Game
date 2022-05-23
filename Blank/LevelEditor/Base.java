package LevelEditor;

public class Base
{
    private int x,y,size;
    public Base(int x, int y)
    {
        this.x = x;
        this.y = y;
        size = 30;
    }
    
    public Base(int x, int y, int size)
    {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getSize() {
        return size;
    }

}