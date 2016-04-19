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
}
