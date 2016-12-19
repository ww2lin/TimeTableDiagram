package Shape;

/**
 * Created by AlexLin on 12/18/16.
 */
public class Circle {

    private final double originY;
    private final double originX;

    private final int offsetX;
    private final int offsetY;
    private final int radius;
    private final int diameter;

    public Circle(int screenWidth, int screenHeight, int radius) {
        originY = screenHeight / 2;
        originX = screenWidth / 2;

        this.offsetX = (int)originY - radius;
        this.offsetY = (int)originX - radius;
        this.radius = radius;
        this.diameter = radius * 2;

    }

    public double getOriginY() {
        return originY;
    }

    public double getOriginX() {
        return originX;
    }

    public int getRadius() {
        return radius;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getDiameter() {
        return diameter;
    }
}
