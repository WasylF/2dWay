package wslf.geometry;

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
}
