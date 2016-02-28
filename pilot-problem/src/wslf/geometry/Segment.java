package wslf.geometry;

/**
 *
 * @author Wsl_F
 */
public class Segment {

    Point a;
    Point b;

    public Segment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Segment(double x1, double y1, double x2, double y2) {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
    }

    @Override
    public String toString() {
        return "[" + a + ";" + b + "]";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        return equals((Segment) obj);
    }

    public boolean equals(Segment s) {
        return (a.equals(s.a) && b.equals(b))
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
}
