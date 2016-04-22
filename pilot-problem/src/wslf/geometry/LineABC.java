package wslf.geometry;

import static java.lang.Math.*;
import static wslf.geometry.Constants.*;
import static wslf.geometry.Math.*;

/**
 * a*x + b*y + c = 0
 *
 * @author Wsl_F
 */
public class LineABC {

    double a;
    double b;
    double c;

    public LineABC(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public LineABC() {
        this(0, 0, 0);
    }

    public LineABC(Point A, Point B) {
        a = B.y - A.y;
        b = A.x - B.x;

        if (abs(a) < EPS && abs(b) < EPS) {
            throw new IllegalArgumentException("LineABC: Points the same. Line couldn't be created");
        }

        c = -determinant(A, B);
    }

    public LineABC(Segment segm) {
        this(segm.a, segm.b);
    }

    public LineABC(Point p, Vector v) {
        this(p, new Point(p.x + v.x, p.y + v.y));
    }

    @Override
    public String toString() {
        return "<" + a + " x  +  " + b + " y  +  " + c + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        return equals((LineABC) obj);
    }

    @Override
    public int hashCode() {
        long A = (long) (a + EPS);
        long B = (long) (b + EPS);
        long C = (long) (c + EPS);
        A = A * BIG_PRIME ^ A * LOW_PRIME;
        B = B * BIG_PRIME ^ B * LOW_PRIME;
        C = C * BIG_PRIME ^ C * LOW_PRIME;

        long hash = LOW_PRIME;
        hash = (LOW_PRIME * hash + (A ^ (B >>> 32))) % BIG_PRIME;
        hash = (LOW_PRIME * hash + (B ^ (C >>> 32))) % BIG_PRIME;
        hash = (LOW_PRIME * hash + (C ^ (A >>> 32))) % BIG_PRIME;

        return (int) (hash % Integer.MAX_VALUE);
    }

    /**
     * chech do lines the same
     *
     * @param line second line
     * @return true, if lines the same
     */
    public boolean equals(LineABC line) {
        return isDeterminantZero(a, b, line.a, line.b)
                && isDeterminantZero(a, c, line.a, line.c)
                && isDeterminantZero(b, c, line.b, line.c);
    }

    /**
     * check does this line equals or parallel {@code line}
     *
     * @param line second line
     * @return true, if lines are parallel or the same
     */
    public boolean isParallel(LineABC line) {
        return isDeterminantZero(a, b, line.a, line.b);
    }

    /**
     * calculate intersection point of 2 lines
     *
     * @param line second line
     * @return intersection point if exists, if lines the same or parallel -
     * null
     */
    public Point getIntersection(LineABC line) {
        if (isParallel(line)) {
            return null;
        }

        double x = -determinant(c, b, line.c, line.b)
                / determinant(a, b, line.a, line.b);
        double y = -determinant(a, c, line.a, line.c)
                / determinant(a, b, line.a, line.b);

        return new Point(x, y);
    }
}
