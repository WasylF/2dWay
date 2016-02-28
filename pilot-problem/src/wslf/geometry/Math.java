package wslf.geometry;

import static java.lang.Math.*;
import static wslf.geometry.Constants.*;

/**
 *
 * @author Wsl_F
 */
public class Math {

    /**
     * | a11    a12 |
     * |            |
     * | a21    a22 |
     * 
     * @return  value of  determinant
     */
    static double determinant(double a11, double a12, double a21, double a22) {
        return a11 * a22 - a12 * a21;
    }
    
    /**
     * | a11    a12 |
     * |            |
     * | a21    a22 |
     * 
     * @return  if value of  determinant close to 0 then return true 
     */
    static boolean isDeterminantZero(double a11, double a12, double a21, double a22) {
        return abs(determinant(a11, a12, a21, a22)) < EPS;
    }
    
    /**
     * | p1.x   p1.y|
     * |            |
     * | p2.x   p2.y|
     * 
     * 
     * @param p1 Point
     * @param p2 Point
     * @return value of  determinant
     */
    static double determinant(Point p1, Point p2) {
        return determinant(p1.x, p1.y, p2.x, p2.y);
    }

    /**
     * | p1.x   p1.y|
     * |            |
     * | p2.x   p2.y|
     * 
     * 
     * @param p1 Point
     * @param p2 Point
     * @return if value of  determinant close to 0 then return true 
     */
    static boolean isDeterminantZero(Point p1, Point p2) {
        return abs(determinant(p1.x, p1.y, p2.x, p2.y)) < EPS;
    }
}
