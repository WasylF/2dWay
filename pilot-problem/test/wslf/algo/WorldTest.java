package wslf.algo;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import wslf.geometry.Point;
import wslf.geometry.Polygon;
import wslf.geometry.Segment;

/**
 *
 * @author Wsl_F
 */
public class WorldTest {

    public WorldTest() {
    }

    /**
     * Test of getBarrierNumber method, of class World.
     */
    @Test
    public void testGetBarrierNumber() {
        System.out.println("getBarrierNumber");
        Point point = new Point(5, 3);
        World instance = VisibilityTest.getWorld2Polygons1();
        int expResult = -1;
        int result = instance.getBarrierNumber(point);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBarrierNumber method, of class World.
     */
    @Test
    public void testGetBarrierNumber_2() {
        System.out.println("getBarrierNumber");
        Point point = new Point(5, -5);
        World instance = VisibilityTest.getWorld3Polygons1();
        int expResult = 1;
        int result = instance.getBarrierNumber(point);
        assertEquals(expResult, result);
    }
}
