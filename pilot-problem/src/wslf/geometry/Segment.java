package wslf.geometry;

import static java.lang.Math.*;
import java.util.Objects;
import static wslf.geometry.Math.*;

/**
 *
 * @author Wsl_F
 */
public class Segment implements Comparable<Segment> {

    Point a;
    Point b;

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Segment(Point a, Point b) {
        this.a = new Point(a);
        this.b = new Point(b);
    }

    public Segment(double x1, double y1, double x2, double y2) {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
    }

    @Override
    public String toString() {
        return "[" + a + ";" + b + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        return equals((Segment) obj);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.a);
        hash = 41 * hash + Objects.hashCode(this.b);
        return hash;
    }

    public boolean equals(Segment s) {
        return (a.equals(s.a) && b.equals(s.b))
                || (a.equals(s.b) && b.equals(s.a));
    }

    public double length() {
        return a.distance(b);
    }

    /**
     * if this segment contains Point p return true else return false
     *
     * @param p Point
     * @return
     */
    public boolean contains(Point p) {
        if (p == null) {
            return false;
        }
        if (a.equals(p) || b.equals(p)) {
            return true;
        }
        Vector v1 = new Vector(a, p);
        Vector v2 = new Vector(b, p);

        return v1.isCollinear(v2) && !v1.isUnidirectional(v2);
    }

    /**
     * if segments intersects return true else return false
     *
     * @param segm second segment
     * @return
     */
    public boolean isIntersect(Segment segm) {
        if (contains(segm.a) || contains(segm.b)
                || segm.contains(a) || segm.contains(b)) {
            return true;
        }

        Vector ab = new Vector(a, b);
        Vector v1 = new Vector(a, segm.a);
        Vector v2 = new Vector(a, segm.b);

        if (ab.sgnMultiplyVectors(v1) == ab.sgnMultiplyVectors(v2)) {
            return false; // if line AB doesn't intersect segment CD
        }

        ab = new Vector(segm.a, segm.b);
        v1 = new Vector(segm.a, a);
        v2 = new Vector(segm.a, b);

        if (ab.sgnMultiplyVectors(v1) == ab.sgnMultiplyVectors(v2)) {
            return false; // if line CD doesn't intersect segment AB
        }

        v1 = new Vector(a, b);
        return !ab.isCollinear(v1);
    }

    /**
     * Calculate intersection of two segments
     *
     * @param segm second segment
     * @return intersection point if exists, else - null
     */
    public Point getIntersection(Segment segm) {
        if (!isIntersect(segm)) {
            return null;
        }

        LineABC line1 = new LineABC(this);
        LineABC line2 = new LineABC(segm);

        Point p = line1.getIntersection(line2);
        return p;
    }

    /**
     * Calculate intersection of segment and line
     *
     * @param line line
     * @return intersection point if exists, else - null
     */
    public Point getIntersection(LineABC line) {
        LineABC line1 = new LineABC(this);

        Point p = line.getIntersection(line1);
        if (!contains(p)) {
            p = null;
        }
        return p;
    }

    /**
     * swaps points if {@code a} on right side of {@code b}
     */
    public void orders() {
        if (a.compareTo(b) == 1) {
            a.swap(b);
        }
    }

    /**
     * swaps points if {@code b} earlier than {@code a} by clockwise
     *
     * @param heatingPoint center of clock
     */
    public void ordersByClockwise(Point heatingPoint) {
        ordersByClockwise(heatingPoint, false);
    }

    /**
     * swaps points if {@code b} earlier than {@code a} by counterclockwise
     *
     * @param heatingPoint center of clock
     */
    public void ordersByCounterclockwise(Point heatingPoint) {
        ordersByClockwise(heatingPoint, true);
    }

    /**
     * swaps points if {@code b} earlier than {@code a} by clockwise or
     * counterclockwise
     *
     * @param heatingPoint center of clock
     * @param reversed true - counterclockwise, false - clockwise
     */
    public void ordersByClockwise(Point heatingPoint, boolean reversed) {
        Vector view = new Vector(0, 1);
        Vector hA = new Vector(heatingPoint, a);
        Vector hB = new Vector(heatingPoint, b);

        if (hA.isUnidirectional(hB)) {
            // closest point should be first
            if (heatingPoint.distance(b) < heatingPoint.distance(a)) {
                a.swap(b);
            }
            return;
        }

        if (view.isCollinear(hA) && view.isCollinear(hB)) {
            if (view.isUnidirectional(hA)) {
                a.swap(b);
            }
            return;
        }

        double angleA = view.getAngle(hA);
        double angleB = view.getAngle(hB);

        // if segment AB doesn't intersect "view line"
        if (sgn(angleA) == sgn(angleB)) {
            angleA = abs(angleA);
            angleB = abs(angleB);

            if (angleB < angleA != reversed) {
                a.swap(b);
            }
            return;
        }

        //segment AB intersect "view line"
        // make angleB!=0
        if (abs(angleB) < Constants.EPS_ANGLE) {
            double t = angleA;
            angleA = angleB;
            angleB = t;
            a.swap(b);
        }

        Ray viewRay = new Ray(heatingPoint, view);
        if (viewRay.isIntersects(this)) {
            if (angleB < 0 == reversed) {
                a.swap(b);
            }
            return;
        } else {
            if (angleB > 0 == reversed) {
                a.swap(b);
            }
        }
    }

    @Override
    public int compareTo(Segment sg) {
        int c1 = a.compareTo(sg.a);
        int c2 = b.compareTo(sg.b);
        if (c1 == 0 && c2 == 0) {
            return 0;
        }
        if (c1 == -1 || (c1 == 0 && c2 == -1)) {
            return -1;
        }

        return 1;
    }
}
