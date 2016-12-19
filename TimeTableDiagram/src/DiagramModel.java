import Shape.Circle;
import Shape.Line;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexLin on 12/18/16.
 */
public class DiagramModel {

    private Circle circle;

    // break the circle into sectors or "pies"
    private List<Point> sectors = new ArrayList<>();

    // lines connecting sectors points
    private List<Line> lines = new ArrayList<>();

    private final int screenHeight;
    private final int screenWidth;
    private final int multiplier;

    public DiagramModel(int screenHeight, int screenWidth, int circleRadius, int numberOfSectors, int multiplier) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.multiplier = multiplier;

        circle = new Circle(screenWidth, screenHeight, circleRadius);
        sectors = generateSectorPoints(circle, numberOfSectors);
        lines = generateLines(sectors);
    }

    private List<Point> generateSectorPoints(Circle circle, int numberOfSectors){
        List<Point> sectorPoints = new ArrayList<>(numberOfSectors);
        final double degreeToIncrement = 360 / numberOfSectors;
        double currentDegree = 0;

        for (int i = 0; i < numberOfSectors; ++i) {
            double angle = Math.toRadians(currentDegree);
            double x = circle.getOriginX() + circle.getRadius() * Math.cos(angle);
            double y = circle.getOriginY() + circle.getRadius() * Math.sin(angle);

            sectorPoints.add(new Point((int)x, (int)y));

            currentDegree = currentDegree + degreeToIncrement;
        }
        return sectorPoints;
    }

    private List<Line> generateLines(List<Point> points){
        List<Line> lines = new ArrayList<>(points.size()/2);
        for (int i = 1; i < points.size(); ++i){
            int endPointIndex = (multiplier * i) % points.size();
            lines.add(new Line(points.get(i), points.get(endPointIndex)));
        }
       return lines;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public List<Point> getSectors() {
        return sectors;
    }

    public List<Line> getLines() {
        return lines;
    }

    public Circle getCircle() {
        return circle;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }
}
