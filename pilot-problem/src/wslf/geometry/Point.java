package wslf.geometry;

import static wslf.geometry.Constants.*;

/**
 *
 * @author Wsl_F
 */
public class Point {

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return equals((Point) o);
    }

    public boolean equals(Point p) {
        return (Math.abs(p.x - x) + Math.abs(p.y - y) < EPS);
    }

    public double distance(Point p) {
        return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
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
}
