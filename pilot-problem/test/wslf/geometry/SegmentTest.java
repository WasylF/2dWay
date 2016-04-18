package wslf.geometry;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wsl_F
 */
public class SegmentTest {

    public SegmentTest() {
    }

    /**
     * Test of toString method, of class Segment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Segment instance = new Segment(1, 2, 3, 4);
        String expResult = "[ (1.0,2.0) ; (3.0,4.0) ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Object() {
        System.out.println("equals");
        Object obj = new Segment(1, 2, 3, 4);
        Segment instance = new Segment(3, 4, 1, 2);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment() {
        System.out.println("equals");
        Segment s = new Segment(1, 2, 3, 4);
        Segment instance = new Segment(1.1, 2, 3, 4);
        boolean expResult = false;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment2() {
        System.out.println("equals");
        Segment s = new Segment(1, 2, 3, 4);
        Segment instance = new Segment(1, 2.1, 3, 4);
        boolean expResult = false;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment3() {
        System.out.println("equals");
        Segment s = new Segment(1, 2, 3, 4);
        Segment instance = new Segment(1, 2.1, 3, 4);
        boolean expResult = false;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment4() {
        System.out.println("equals");
        Segment s = new Segment(1, 2, 3, 4);
        Segment instance = new Segment(1, 2, 3.1, 4);
        boolean expResult = false;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment5() {
        System.out.println("equals");
        Segment s = new Segment(1, 2, 3, 4);
        Segment instance = new Segment(1, 2, 3, 4.1);
        boolean expResult = false;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment6() {
        System.out.println("equals");
        Segment s = new Segment(1.1, 2, 3, 4);
        Segment instance = new Segment(1, 2, 3, 4);
        boolean expResult = false;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment7() {
        System.out.println("equals");
        Segment s = new Segment(1, 2.1, 3, 4);
        Segment instance = new Segment(1, 2, 3, 4);
        boolean expResult = false;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment8() {
        System.out.println("equals");
        Segment s = new Segment(1, 2, 3.1, 4);
        Segment instance = new Segment(1, 2, 3, 4);
        boolean expResult = false;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment9() {
        System.out.println("equals");
        Segment s = new Segment(1, 2, 3, 4.1);
        Segment instance = new Segment(1, 2, 3, 4);
        boolean expResult = false;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment10() {
        System.out.println("equals");
        Segment s = new Segment(1, 2, 3, 4.1);
        Segment instance = new Segment(1, 2, 3, 4.1);
        boolean expResult = true;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment11() {
        System.out.println("equals");
        Segment s = new Segment(1, 2, 3, 4.1);
        Segment instance = new Segment(3, 4.1, 1, 2);
        boolean expResult = true;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals_Segment12() {
        System.out.println("equals");
        Segment s = new Segment(3, 4.5, 1, 2.1);
        Segment instance = new Segment(1, 2.1, 3, 4.5);
        boolean expResult = true;
        boolean result = instance.equals(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of length method, of class Segment.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        Segment instance = new Segment(0, 0, 0, 10);
        double expResult = 10.0;
        double result = instance.length();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of contains method, of class Segment.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Point p = new Point(2, 3);
        Segment instance = new Segment(1, 1, 3, 5);
        boolean expResult = true;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of isIntersect method, of class Segment.
     */
    @Test
    public void testIsIntersect() {
        System.out.println("isIntersect");
        Segment segm = new Segment(1, 1, 5, 5);
        Segment instance = new Segment(1, 5, 5, 1);
        boolean expResult = true;
        boolean result = instance.isIntersect(segm);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIntersection method, of class Segment.
     */
    @Test
    public void testGetIntersection() {
        System.out.println("getIntersection");
        Segment segm = new Segment(1, 1, 7, 7);
        Segment instance = new Segment(1, 7, 7, 1);
        Point expResult = new Point(4, 4);
        Point result = instance.getIntersection(segm);
        assertEquals(expResult, result);
    }

    @Test
    public void testOrdersByClockwise() {
        System.out.println("ordersByClockwise");
        Point first = new Point(1, 1);
        Point second = new Point(1, -1);
        Segment instance = new Segment(second, first);
        instance.ordersByClockwise(new Point(0, 0));
        assertEquals(first, instance.a);
        assertEquals(second, instance.b);
    }

    @Test
    public void testOrdersByClockwise2() {
        System.out.println("ordersByClockwise");
        Point first = new Point(1, 1);
        Point second = new Point(1, -1);
        Segment instance = new Segment(first, second);
        instance.ordersByClockwise(new Point(0, 0));
        assertEquals(first, instance.a);
        assertEquals(second, instance.b);
    }

}
