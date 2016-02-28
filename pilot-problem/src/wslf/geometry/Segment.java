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

}
