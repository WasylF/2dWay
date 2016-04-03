package wslf.geometry;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wsl_F
 */
public class PolygonTest {

    public PolygonTest() {
    }

    /**
     * Test of toString method, of class Polygon.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Point[] vertexes = {new Point(0, 0), new Point(0, 3), new Point(4, 0)};
        Polygon instance = new Polygon(vertexes);
        String expResult = "[3: (0.0,0.0)  (0.0,3.0)  (4.0,0.0) ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Polygon.
     */
    @Test
    public void testEquals_Object() {
        System.out.println("equals");
        Point[] vertexes1 = {new Point(0, 3), new Point(4, 0), new Point(0, 0)};
        Point[] vertexes2 = {new Point(0, 0), new Point(0, 3), new Point(4, 0)};
        Object obj = new Polygon(vertexes1);
        Polygon instance = new Polygon(vertexes2);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Polygon.
     */
    @Test
    public void testEquals_Polygon() {
        System.out.println("equals");
        Point[] vertexes1 = {new Point(0, 3), new Point(4, 0), new Point(0, 0), new Point(0, 2)};
        Point[] vertexes2 = {new Point(0, 0), new Point(0, 3), new Point(4, 0), new Point(0, 2)};
        Polygon p = new Polygon(vertexes1);
        Polygon instance = new Polygon(vertexes2);
        boolean expResult = false;
        boolean result = instance.equals(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSize method, of class Polygon.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        Point[] vertexes1 = {new Point(0, 3), new Point(4, 0), new Point(0, 0), new Point(0, 2)};
        Polygon instance = new Polygon(vertexes1);
        int expResult = 4;
        int result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Polygon.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Point[] vertexes1 = {new Point(0, 0), new Point(4, 0), new Point(4, 4), new Point(0, 4)};
        Point p = new Point(1, 1);
        Polygon instance = new Polygon(vertexes1);
        boolean expResult = true;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Polygon.
     */
    @Test
    public void testContains2() {
        System.out.println("contains");
        Point[] vertexes1 = {new Point(0, 0), new Point(4, 0), new Point(4, 4), new Point(0, 4)};
        Point p = new Point(5, 5);
        Polygon instance = new Polygon(vertexes1);
        boolean expResult = false;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Polygon.
     */
    @Test
    public void testContains3() {
        System.out.println("contains");
        Point[] vertexes1 = {new Point(0, 0), new Point(4, 0), new Point(4, 4), new Point(0, 4)};
        Point p = new Point(0, 0);
        Polygon instance = new Polygon(vertexes1);
        boolean expResult = true;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Polygon.
     */
    @Test
    public void testContains4() {
        System.out.println("contains");
        Point[] vertexes1 = {new Point(0, 0), new Point(4, 0), new Point(4, 4),
            new Point(2, 6), new Point(0, 4)};
        Point p = new Point(2, 5);
        Polygon instance = new Polygon(vertexes1);
        boolean expResult = true;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Polygon.
     */
    @Test
    public void testContains5() {
        System.out.println("contains");
        Point[] vertexes1 = {new Point(0, 0), new Point(4, 0), new Point(4, 4),
            new Point(0, 4)};
        Point p = new Point(2, 5);
        Polygon instance = new Polygon(vertexes1);
        boolean expResult = false;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Polygon.
     */
    @Test
    public void testContains6() {
        System.out.println("contains");
        Point[] vertexes1 = {new Point(0, 0), new Point(4, 0), new Point(4, 4),
            new Point(2, 6), new Point(0, 4)};
        Point p = new Point(2, 7);
        Polygon instance = new Polygon(vertexes1);
        boolean expResult = false;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }

}
