package wslf.geometry;

import static java.lang.Math.*;
import javax.swing.text.html.HTML;
import static wslf.geometry.Constants.*;

/**
 *
 * @author Wsl_F
 */
public class Vector extends Point {
    
    public Vector() {
        super();
    }
    
    public Vector(double x, double y) {
        super(x, y);
    }
    
    public Vector(Vector v) {
        super(v);
    }
    
    public Vector(Point begin, Point end) {
        this.x = end.x - begin.x;
        this.y = end.y - begin.y;
    }
    
    @Override
    public String toString() {
        return " ( " + x + " , " + y + " ) : |" + length() + "|";
    }

    /**
     * check equality
     *
     * @param obj object
     * @return true if vectors the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return equals((Vector) obj);
    }

    /**
     * Two vectors are equals if they have the same length and direction
     *
     * @param v second vector
     * @return
     */
    public boolean equals(Vector v) {
        return ((Point) this).equals((Point) v);
    }

    /**
     * check does this vector collinear to v
     *
     * @param v second vector
     * @return true if collinear
     */
    public boolean isCollinear(Vector v) {
        if (length() + v.length() < EPS) {
            return true;
        }
        
        if (length() * v.length() < EPS) {
            return false;
        }
        
        return abs(x * v.y - y * v.x) < EPS;
    }

    /**
     *
     * @return length of vector
     */
    public double length() {
        return hypot(x, y);
    }

    /**
     * makes length of vector equals to 1
     *
     * @return is it successful
     */
    public boolean normalize() {
        double length = length();
        if (length > EPS) {
            x /= length;
            y /= length;
            return true;
        }
        return false;
    }

    /**
     * multiplication of a vector by a scalar
     *
     * @param k scalar
     */
    public void multiplyScalar(double k) {
        x *= k;
        y *= k;
    }

    /**
     * Dot product of vectors
     *
     * @param v second vector
     * @return multiplication
     */
    public double multiplyScalar(Vector v) {
        return x * y + v.x * v.y;
    }

    /**
     * Cross product of vectors
     *
     * @param v second vector
     * @return
     */
    public double multiplyVectors(Vector v) {
        return x * v.y - y * v.x;
    }
}
