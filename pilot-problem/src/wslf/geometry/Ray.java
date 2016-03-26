package wslf.geometry;

/**
 *
 * @author Wsl_F
 */
public class Ray {

    Point p;
    Vector v;

    Ray() {
        p = new Point();
        v = new Vector();
    }

    Ray(Point p, Vector v) {
        this.p = p;
        this.v = v;
    }

    @Override
    public String toString() {
        return "< " + p.toString() + "   " + v.toString() + " >";
    }

    public boolean equals(Ray r) {
        return (p.equals(r.p) && v.equals(r.v));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != Ray.class) {
            return false;
        }

        return equals((Ray) obj);
    }

    /**
     * does Ray contains point. Works with null point
     *
     * @param point point
     * @return
     */
    public boolean contains(Point point) {
        if (point == null) {
            return false;
        }
        return v.getAngle(new Vector(this.p, point)) < Constants.EPS_ANGLE;
    }

    /**
     * find intersection of Ray and segment. If doesn't exists - null
     *
     * @param sg segment
     * @return
     */
    public Point getIntersection(Segment sg) {
        LineABC line = new LineABC(p, v);
        Point point = sg.getIntersection(line);
        if (!sg.contains(point) || !contains(point)) {
            point = null;
        }
        return point;
    }

    /**
     * distance from Ray begins to intersection Point
     *
     * @param sg segment
     * @return distance or -1 if intersection doesn't esist
     */
    public double distToIntersection(Segment sg) {
        Point point = getIntersection(sg);
        if (point == null) {
            return -1;
        }
        return p.distance(point);
    }

    public boolean isIntersects(Segment sg) {
        Point point = getIntersection(sg);
        return point != null;
    }
}
