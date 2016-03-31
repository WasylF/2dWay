package wslf.geometry;

import java.util.Objects;

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

    public Ray(Point p, Vector v) {
        this.p = new Point(p);
        this.v = new Vector(v);
    }

    public Ray(Ray ray) {
        this.p = new Point(ray.p);
        this.v = new Vector(ray.v);
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.p);
        hash = 37 * hash + Objects.hashCode(this.v);
        return hash;
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

    /**
     * return angle between this ray and vector that begin in ray's begin and
     * ends at @point
     *
     * @param point end of vector
     * @return angle in radians from [-pi; pi]
     */
    public double getAngle(Point point) {
        Vector v2 = new Vector(p, point);
        return v.getAngle(v2);
    }

    /**
     * return angle between this ray and vector that begin in ray's begin and
     * ends at @point
     *
     * @param point end of vector
     * @return angle in radians from [0; 2*pi]
     */
    public double getAngle360(Point point) {
        double angle = getAngle(point);
        if (angle < 0) {
            angle += 2 * java.lang.Math.PI;
        }
        return angle;
    }
}
