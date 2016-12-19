package Shape;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexLin on 12/18/16.
 */
public class Circle {

    // lines to connect the sectors
    private List<List<Point>> lines = new ArrayList<>();

    private final int screenWidth;
    private final int screenHeight;
    private final double originY;
    private final double originX;

    private final int offsetX;
    private final int offsetY;
    private final int radius;
    private final int diameter;

    public Circle(int screenWidth, int screenHeight, int radius) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

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
