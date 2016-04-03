package wslf.geometry;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wsl_F
 */
public class LineABCTest {

    public LineABCTest() {
    }

    /**
     * Test of toString method, of class LineABC.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LineABC instance = new LineABC(1, 2, 3);
        String expResult = "<1.0 x  +  2.0 y  +  3.0>";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class LineABC.
     */
    @Test
    public void testEquals_Object() {
        System.out.println("equals");
        Object obj = new LineABC(2, 4, 6);
        LineABC instance = new LineABC(1, 2, 3);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class LineABC.
     */
    @Test
    public void testEquals_LineABC() {
        System.out.println("equals");
        LineABC line = new LineABC(1, 2, 3);
        LineABC instance = new LineABC(1, 2, 4);
        boolean expResult = false;
        boolean result = instance.equals(line);
        assertEquals(expResult, result);
    }

    /**
     * Test of isParallel method, of class LineABC.
     */
    @Test
    public void testIsParallel() {
        System.out.println("isParallel");
        LineABC line = new LineABC(1, 2, 10);
        LineABC instance = new LineABC(2, 4, 10);
        boolean expResult = true;
        boolean result = instance.isParallel(line);
        assertEquals(expResult, result);
    }

    /**
     * Test of isParallel method, of class LineABC.
     */
    @Test
    public void testIsParallel2() {
        System.out.println("isParallel");
        LineABC line = new LineABC(1, 2, 10);
        LineABC instance = new LineABC(2, 5, 10);
        boolean expResult = false;
        boolean result = instance.isParallel(line);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIntersection method, of class LineABC.
     */
    @Test
    public void testGetIntersection() {
        System.out.println("getIntersection");
        LineABC line = new LineABC(1, 1, 0);
        LineABC instance = new LineABC(1, -1, 0);
        Point expResult = new Point(0, 0);
        Point result = instance.getIntersection(line);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIntersection method, of class LineABC.
     */
    @Test
    public void testGetIntersection2() {
        System.out.println("getIntersection");
        LineABC line = new LineABC(new Point(2, 5), new Point(5, 7));
        LineABC instance = new LineABC(new Point(2, 9), new Point(5, 7));
        Point expResult = new Point(5, 7);
        Point result = instance.getIntersection(line);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIntersection method, of class LineABC.
     */
    @Test
    public void testGetIntersection3() {
        System.out.println("getIntersection");
        LineABC line = new LineABC(new Point(2, 5), new Point(5, 7));
        LineABC instance = new LineABC(new Point(2, 6), new Point(5, 8));
        Point expResult = null;
        Point result = instance.getIntersection(line);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIntersection method, of class LineABC.
     */
    @Test
    public void testGetIntersection4() {
        System.out.println("getIntersection");
        LineABC line = new LineABC(new Point(2, 5), new Point(5, 7));
        LineABC instance = new LineABC(new Point(2, 5), new Point(5, 7));
        Point expResult = null;
        Point result = instance.getIntersection(line);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIntersection method, of class LineABC.
     */
    @Test
    public void testGetIntersection5() {
        System.out.println("getIntersection");
        LineABC line = new LineABC(new Point(23, 45), new Point(85, 47));
        LineABC instance = new LineABC(new Point(2, -10), new Point(23, 45));
        Point expResult = new Point(23, 45);
        Point result = instance.getIntersection(line);
        assertEquals(expResult, result);
    }

}
