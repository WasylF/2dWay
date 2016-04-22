package wslf.geometry;

import static java.lang.Math.*;
import static wslf.geometry.Constants.*;

/**
 *
 * @author Wsl_F
 */
public class Math {

    /**
     * <pre>
     * | a11 a12 |
     * |         |
     * | a21 a22 |
     * </pre>
     *
     * @return value of determinant
     */
    static double determinant(double a11, double a12, double a21, double a22) {
        return a11 * a22 - a12 * a21;
    }

    /**
     * <pre>
     * | a11 a12 |
     * |         |
     * | a21 a22 |
     * </pre>
     *
     * @return if value of determinant close to 0 then return true
     */
    static boolean isDeterminantZero(double a11, double a12, double a21, double a22) {
        return abs(determinant(a11, a12, a21, a22)) < EPS;
    }

    /**
     * <pre>
     * | p1.x   p1.y|
     * |            |
     * | p2.x   p2.y|
     * </pre>
     *
     * @param p1 Point
     * @param p2 Point
     * @return value of determinant
     */
    static double determinant(Point p1, Point p2) {
        return determinant(p1.x, p1.y, p2.x, p2.y);
    }

    /**
     * <pre>
     * | p1.x p1.y|
     * |          |
     * | p2.x p2.y|
     * </pre>
     *
     *
     * @param p1 Point
     * @param p2 Point
     * @return if value of determinant close to 0 then return true
     */
    static boolean isDeterminantZero(Point p1, Point p2) {
        return abs(determinant(p1.x, p1.y, p2.x, p2.y)) < EPS;
    }

    /**
     * signum function
     *
     * <br> if val == 0 -> 0
     * <br> if val > 0 -> 1
     * <br> if val  < 0 -> -1
     *
     * @param val value
     * @return signum(val)
     */
    static int sgn(double val) {
        if (abs(val) < EPS) {
            return 0;
        }

        if (val < 0) {
            return -1;
        }

        return 1;
    }

    /**
     * Returns the hypotenuse of a triangle with sides {@code x} and {@code y} -
     * sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>)<br/>
     * avoiding intermediate overflow or underflow.
     *
     * <ul>
     * <li> If either argument is infinite, then the result is positive
     * infinity.</li>
     * <li> else, if either argument is NaN then the result is NaN.</li>
     * </ul>
     *
     * @param x a value
     * @param y a value
     * @return sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>)
     */
    public static double hypot(final double x, final double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y)) {
            return Double.POSITIVE_INFINITY;
        } else if (Double.isNaN(x) || Double.isNaN(y)) {
            return Double.NaN;
        } else {

            final int expX = getExponent(x);
            final int expY = getExponent(y);
            if (expX > expY + 27) {
                // y is neglectible with respect to x
                return abs(x);
            } else if (expY > expX + 27) {
                // x is neglectible with respect to y
                return abs(y);
            } else {

                // find an intermediate scale to avoid both overflow and underflow
                final int middleExp = (expX + expY) / 2;

                // scale parameters without losing precision
                final double scaledX = scalb(x, -middleExp);
                final double scaledY = scalb(y, -middleExp);

                // compute scaled hypotenuse
                final double scaledH = sqrt(scaledX * scaledX + scaledY * scaledY);

                // remove scaling
                return scalb(scaledH, middleExp);

            }

        }
    }

}
