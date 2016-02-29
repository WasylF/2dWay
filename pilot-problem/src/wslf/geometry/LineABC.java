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
        this.a = 0;
        this.b = 0;
        this.c = 0;
    }

    public LineABC(Point A, Point B) {
        if (A.equals(B)) {
            throw new IllegalArgumentException("Points the same");
        }

        a = B.y - A.y;
        b = A.x - B.x;
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

    public boolean equals(LineABC line) {
        return isDeterminantZero(a, b, line.a, line.b)
                && isDeterminantZero(a, c, line.a, line.c)
                && isDeterminantZero(b, c, line.b, line.c);
    }

    public boolean isParallel(LineABC line) {
        return isDeterminantZero(a, b, line.a, line.b);
    }

    /**
     * calculate intersection point of 2 lines
     *
     * @param line second line
     * @return intersection point if exists, 
     * if lines the same or parallel - null
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
