package wslf.geometry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wsl_F
 */
public class SegmentTest {

    public SegmentTest() {
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

}
