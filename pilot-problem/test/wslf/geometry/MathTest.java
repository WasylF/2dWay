package wslf.geometry;

import org.junit.Test;
import static org.junit.Assert.*;

import static wslf.geometry.Constants.*;

/**
 *
 * @author Wsl_F
 */
public class MathTest {

    public MathTest() {
    }

    /**
     * Test of determinant method, of class Math.
     */
    @Test
    public void testDeterminant_4args() {
        System.out.println("determinant");
        double a11 = 1.0;
        double a12 = 2.0;
        double a21 = 10.0;
        double a22 = 20.0;
        double expResult = 0.0;
        double result = Math.determinant(a11, a12, a21, a22);
        assertEquals(expResult, result, EPS);
    }

    /**
     * Test of determinant method, of class Math.
     */
    @Test
    public void testDeterminant_4args2() {
        System.out.println("determinant");
        double a11 = 1.0;
        double a12 = 0.0;
        double a21 = 2.0;
        double a22 = 2.0;
        double expResult = 2;
        double result = Math.determinant(a11, a12, a21, a22);
        assertEquals(expResult, result, EPS);
    }

    /**
     * Test of isDeterminantZero method, of class Math.
     */
    @Test
    public void testIsDeterminantZero_4args() {
        System.out.println("isDeterminantZero");
        double a11 = 28.0;
        double a12 = 15.0;
        double a21 = 56.0;
        double a22 = 30.0;
        boolean expResult = true;
        boolean result = Math.isDeterminantZero(a11, a12, a21, a22);
        assertEquals(expResult, result);
    }

    /**
     * Test of determinant method, of class Math.
     */
    @Test
    public void testDeterminant_Point_Point() {
        System.out.println("determinant");
        Point p1 = new Point(2, 3);
        Point p2 = new Point(5, 6);
        double expResult = -3;
        double result = Math.determinant(p1, p2);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of isDeterminantZero method, of class Math.
     */
    @Test
    public void testIsDeterminantZero_Point_Point() {
        System.out.println("isDeterminantZero");
        Point p1 = new Point(25, 30);
        Point p2 = new Point(30, 35);
        boolean expResult = false;
        boolean result = Math.isDeterminantZero(p1, p2);
        assertEquals(expResult, result);
    }

}
