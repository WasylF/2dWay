package wslf.geometry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import static wslf.geometry.Constants.EPS;
import static wslf.geometry.Math.*;

/**
 *
 * @author Wsl_F
 */
public class PointTest {

    public PointTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Point.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Point instance = new Point(1, 2);
        String expResult = " (1.0,2.0) ";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Point.
     */
    @Test
    public void testEquals_Object() {
        System.out.println("equals");
        Object obj = new Point(34, 56);
        Point instance = new Point(34, 56);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Point.
     */
    @Test
    public void testEquals_Point() {
        System.out.println("equals");
        Point p = new Point(875, 345);
        Point instance = new Point(875, 346);
        boolean expResult = false;
        boolean result = instance.equals(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of distance method, of class Point.
     */
    @Test
    public void testDistance() {
        System.out.println("distance");
        Point p = new Point(10, 123);
        Point instance = new Point(10, 125);
        double expResult = 2;
        double result = instance.distance(p);
        assertEquals(expResult, result, EPS);
    }

    /**
     * Test of getSymmetric method, of class Point.
     */
    @Test
    public void testGetSymmetric() {
        System.out.println("getSymmetric");
        Point middle = new Point(10, 10);
        Point instance = new Point(10, 5);
        Point expResult = new Point(10, 15);
        Point result = instance.getSymmetric(middle);
        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(0, 1);
        Point p = new Point(1, 0);

        int expResult = -1;
        int result = instance.compareByClockwise(p, heatingPoint, false);

        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise2() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(1, -1);
        Point p = new Point(1, 0);

        int expResult = 1;
        int result = instance.compareByClockwise(p, heatingPoint, false);

        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise3() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(0, 1);
        Point p = new Point(0, 2);

        int expResult = -1;
        int result = instance.compareByClockwise(p, heatingPoint, false);

        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise4() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(0, 1);
        Point p = new Point(0, 0.5);

        int expResult = 1;
        int result = instance.compareByClockwise(p, heatingPoint, false);

        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise5() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(1, 1);
        Point p = new Point(-1, -1);

        int expResult = -1;
        int result = instance.compareByClockwise(p, heatingPoint, false);

        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise6() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(7, 8);
        Point p = new Point(7, 8);

        int expResult = 0;
        int result = instance.compareByClockwise(p, heatingPoint, false);

        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise7() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(0, 1);
        Point p = new Point(-0.1, 1);

        int expResult = -1;
        int result = instance.compareByClockwise(p, heatingPoint, false);

        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise8() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(-0.1, 1);
        Point p = new Point(0, 1);

        int expResult = 1;
        int result = instance.compareByClockwise(p, heatingPoint, false);

        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise9() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(0, 1);
        Point p = new Point(-0.1, 1);

        int expResult = -1;
        int result = instance.compareByClockwise(p, heatingPoint, true);

        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise10() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(-0.1, 1);
        Point p = new Point(0, 1);

        int expResult = 1;
        int result = instance.compareByClockwise(p, heatingPoint, true);

        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise11() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(-1, 1);
        Point p = new Point(-2, 1);

        int expResult = -1;
        int result = instance.compareByClockwise(p, heatingPoint, true);

        assertEquals(expResult, result);
    }

    @Test
    public void testCompareByClockwise12() {
        System.out.println("compareByClockwise");
        Point heatingPoint = new Point(0, 0);
        Point instance = new Point(-1, 100);
        Point p = new Point(-1, 200);

        int expResult = 1;
        int result = instance.compareByClockwise(p, heatingPoint, true);

        assertEquals(expResult, result);
    }

    @Test
    public void testSwap() {
        System.out.println("swap");
        Point instance = new Point(0, 0);
        Point p = new Point(1, 1);
        instance.swap(p);

        assertEquals(new Point(1, 1), instance);
        assertEquals(new Point(0, 0), p);
    }
}
