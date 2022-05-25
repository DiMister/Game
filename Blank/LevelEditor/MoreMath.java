package LevelEditor;

public class MoreMath
{
    public static double map(double x, double oldMin, double oldMax, double newMin, double newMax) {
        return (((x - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;
    }
    
}
