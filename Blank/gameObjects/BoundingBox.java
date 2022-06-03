package gameObjects;

import java.awt.*;

public class BoundingBox
{
    private int rightX,y,width,height,leftX,x;
    public BoundingBox(Image image, int[] boundingBox, int size)
    {
        rightX = (int)(MoreMath.map(boundingBox[0],0,image.getWidth(null),0,size)+0.5);
        y = (int)(MoreMath.map(boundingBox[1],0,image.getWidth(null),0,size)+0.5);
        width = (int)(MoreMath.map(boundingBox[2],0,image.getWidth(null),0,size)+0.5);
        height = (int)(MoreMath.map(boundingBox[3],0,image.getWidth(null),0,size)+0.5);
        leftX = (int)(MoreMath.reverse(rightX+width,0,size)+0.5);
        x = rightX;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public void left() {x = leftX;}

    public void right() {x = rightX;}

    public boolean isColliding(BoundingBox other, int mx, int my, int ox, int oy) {
        boolean xLine = false, yLine = false;

        if ((ox-other.getWidth()/2 > mx-width/2 &&  ox-other.getWidth()/2 < my+width/2) ||
        (ox+other.getWidth()/2 > mx-width/2 &&  ox+other.getWidth()/2 < mx+width/2))
            xLine = true;

        if ((oy-other.getHeight()/2 > my-height/2 &&  oy-other.getHeight()/2 < my+height/2) ||
        (oy+other.getHeight()/2 > my-height/2 &&  oy+other.getHeight()/2 < my+height/2))
            yLine = true;

        return xLine && yLine;
    }
    
    public void removeCollision(ImageObject mover, int mx, int my) {
        int colX = collisionX(mover, mx);
        int colY = collisionY(mover, my);
        BoundingBox other = mover.getBoundingBox();
        
        if (colX == 0 || colY == 0)
            return;
        
        if (Math.abs(colX) >= Math.abs(colY)) {
            if (colY < 0) mover.setY(my-height/2-other.getHeight()/2);
            else mover.setY(my+height/2+other.getHeight()/2);
        } 
        else {
            if (colX < 0) mover.setX(mx-width/2-other.getWidth()/2);
            else mover.setX(mx+width/2+other.getWidth()/2);
        }
    }
    
    public int collisionX(ImageObject mover, int mx) {
        BoundingBox other = mover.getBoundingBox();
        int result = 0;
        int moverLeftWall = mover.getX()-other.getWidth()/2, mLeftWall = mx-width/2,
            moverRightWall = mover.getX()+other.getWidth()/2, mRightWall = mx+width/2;
        
        if ((moverLeftWall > mLeftWall &&  moverLeftWall < mRightWall) ||
            (moverRightWall > mLeftWall &&  moverRightWall < mRightWall) || 
            (moverRightWall >= mRightWall && moverLeftWall <= mLeftWall)) {
            if (mx <= mover.getX()) result = mRightWall-moverLeftWall;
            else result = (moverRightWall-mLeftWall)*-1;
        }
        
        return result;
    }
    
    public int collisionY(ImageObject mover,int my) {
        BoundingBox other = mover.getBoundingBox();
        int result = 0;
        int moverUpWall = mover.getY()-other.getHeight()/2, mUpWall = my-height/2,
            moverDownWall = mover.getY()+other.getHeight()/2, mDownWall = my+height/2;
        
        if ((moverUpWall > mUpWall &&  moverUpWall < my+height/2) ||
            (moverDownWall > mUpWall &&  moverDownWall < mDownWall) || 
            (moverDownWall >= mDownWall && moverUpWall <= mUpWall)) {
            if (my <= mover.getY()) result = (my+height/2)-(mover.getY()-other.getHeight()/2);
            else result = ((mover.getY()+other.getHeight()/2)-(my-height/2))*-1;
        }
        
        return result;
    }
}
