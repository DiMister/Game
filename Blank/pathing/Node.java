package pathing;

public class Node
{
    
    private boolean walkable;
    private int x,y;
    
    public Node(boolean _walkable, int _x, int _y)
    {
        walkable = _walkable;
        x = _x;
        y = _y;
    }
    
    public boolean isWalkable() {return walkable;}
    
    public int getX() {return x;}
    
    public int getY() {return y;}
}
