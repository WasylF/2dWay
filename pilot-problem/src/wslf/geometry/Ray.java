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
}
