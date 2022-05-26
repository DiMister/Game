package pathing;

import java.awt.*;

public class PathingGrid
{
    private Point gridWorldSize, position;
    private float nodeRadius, nodeDiameter;
    private Node[][] grid;
    private int gridSizeX, gridSizeY;
    
    private boolean debug = true;
    
    public PathingGrid(Point _position, Point _gridWorldSize, float _nodeRadius)
    {
        position = _position;
        gridWorldSize = _gridWorldSize;
        nodeRadius = _nodeRadius;
        
        nodeDiameter = nodeRadius*2;
        gridSizeX = (int)(gridWorldSize.getX()/nodeDiameter);
        gridSizeY = (int)(gridWorldSize.getY()/nodeDiameter);
    }
    
    public void draw(Graphics g) {
        if (!debug) return;
        
        g.drawRect(position.x-gridWorldSize.x/2,position.y-gridWorldSize.y/2,
            position.x+gridWorldSize.x/2,position.y+gridWorldSize.y/2);
    }
}
