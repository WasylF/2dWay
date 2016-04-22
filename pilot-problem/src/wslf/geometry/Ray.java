package wslf.geometry;

import java.io.Serializable;
import static java.lang.Math.abs;
import java.util.Objects;

/**
 *
 * @author Wsl_F
 */
public class Ray implements Serializable {

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

    public void setVector(Vector vector) {
        this.v = vector;
    }

    public void setPoint(Point point) {
        this.p = point;
    }

    public Point getPoint() {
        return p;
    }

    public Vector getVector() {
        return v;
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
        return p.equals(point) || abs(v.getAngle(new Vector(this.p, point))) < Constants.EPS_ANGLE;
    }

    /**
     * find intersection of Ray and segment. If doesn't exists - null. if
     * segment {@code sg} is "collinear" {@code this} ray, return closest point.
     *
     * @param sg segment
     * @return
     */
    public Point getIntersection(Segment sg) {
        LineABC line = new LineABC(p, v);
        Point point = sg.getIntersection(line);
        // process "collinear" segment
        if (point == null) {
            boolean cont1 = contains(sg.a);
            boolean cont2 = contains(sg.b);
            if (cont1 || cont2) {
                if (cont1 && cont2) {
                    point = p.distance(sg.a) < p.distance(sg.b) ? sg.a : sg.b;
                } else {
                    point = new Point(p);
                }
            } else {
                return null;
            }
        }
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
        if (p.equals(point)) {
            return 0;
        }

        Vector v2 = new Vector(p, point);
        return v.getAngle(v2);
    }

    /**
     * return angle between this ray and vector that begin in ray's begin and
     * ends at @point
     *
     * @param point end of vector
     * @return angle in radians from [0; 2*pi)
     */
    public double getAngle2PI(Point point) {
        double angle = getAngle(point);
        if (angle < 0) {
            angle += 2 * java.lang.Math.PI;
        }
        return angle;
    }
}
