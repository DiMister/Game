package gameObjects;

public class MoreMath
{
    public static double map(double x, double oldMin, double oldMax, double newMin, double newMax) {
        return (((x - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;
    }
    
    public static double reverse(double num, double min, double max) {
        return (max + min) - num;
    }   
}
