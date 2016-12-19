package Shape;

import java.awt.Point;

public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public int x1() {
        return start.x;
    }

    public int x2() {
        return end.x;
    }

    public int y1(){
        return start.y;
    }

    public int y2(){
        return end.y;
    }
}
