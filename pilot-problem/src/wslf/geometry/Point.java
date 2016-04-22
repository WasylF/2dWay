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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

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

    @Override
    public int hashCode() {
        long X = (long) (x + EPS);
        long Y = (long) (y + EPS);
        X = X * BIG_PRIME ^ X * LOW_PRIME;
        Y = Y * BIG_PRIME ^ Y * LOW_PRIME;

        long hash = LOW_PRIME;
        hash = (LOW_PRIME * hash + (X ^ (Y >>> 32))) % BIG_PRIME;
        hash = (LOW_PRIME * hash + (Y ^ (X >>> 32))) % BIG_PRIME;

        return (int) (hash % Integer.MAX_VALUE);
    }

    public boolean equals(Point p) {
        return abs(p.x - x) < EPS && abs(p.y - y) < EPS;
    }

    public double distance(Point p) {
        return wslf.geometry.Math.hypot(x - p.x, y - p.y);
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
        if (x < p.x || (abs(x - p.x) < EPS && y < p.y)) {
            return -1;
        }
        return 1;
    }

    /**
     * compare points by clockwise. if points have the same angle than sorts by
     * distance to the {@code heatingPoint}
     *
     * @param p second point
     * @param heatingPoint center of clocks
     * @param revers false - in clockwise order, true in counterclockwise order
     * @return -1 if {@code this} earlier than {@code p}, 1 if {@code p} earlier
     * than {@code this}, 0 if points the same.
     */
    public int compareByClockwise(Point p, Point heatingPoint, boolean revers) {
        if (equals(p)) {
            return 0;
        }
        if (equals(heatingPoint)) {
            return -1;
        }
        if (p.equals(heatingPoint)) {
            return 1;
        }
        Vector v1 = new Vector(heatingPoint, this);
        Vector v2 = new Vector(heatingPoint, p);
        int sgn = v1.sgnMultiplyVectors(v2);
        if (sgn == 0) {
            if (v1.isUnidirectional(v2)) {
                //Manhattan Distance for Unidirectional vectors doesn't affect order
                double d1 = abs(v1.x) + abs(v1.y);
                double d2 = abs(v2.x) + abs(v2.y);
                if (abs(d1 - d2) < EPS) {
                    return 0;
                }
                return d1 < d2 ? -1 : 1;
            } else {
                //Opposite
                return v1.x >= 0 != revers ? -1 : 1;
            }
        }

        return revers ? -sgn : sgn;
    }

    /**
     * swaps points
     *
     * @param p second point
     */
    public void swap(Point p) {
        if (this == p) {
            return;
        }
        double xt = p.x;
        double yt = p.y;

        p.x = this.x;
        p.y = this.y;

        this.x = xt;
        this.y = yt;
    }
}
