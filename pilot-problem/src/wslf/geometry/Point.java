package wslf.geometry;

import static wslf.geometry.Constants.*;
import static java.lang.Math.*;

/**
 *
 * @author Wsl_F
 */
public class Point implements Comparable<Point> {

    double x;
    double y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public String toString() {
        return " (" + x + "," + y + ") ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return equals((Point) obj);
    }

    public boolean equals(Point p) {
        return (abs(p.x - x) + abs(p.y - y) < EPS);
    }

    public double distance(Point p) {
        return hypot(x - p.x, y - p.y);
    }

    /**
     * AX - segment, M belongs AX && AM=MX A - this point, M - middle
     *
     * @param middle
     * @return X
     */
    public Point getSymmetric(Point middle) {
        return new Point(2 * middle.x - x, 2 * middle.y - y);
    }

    @Override
    public int compareTo(Point p) {
        if (equals(p)) {
            return 0;
        }
        if (x < p.x || (abs(x - p.x) < EPS && y < p.x)) {
            return -1;
        }
        return 1;
    }
}
