package wslf.geometry;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wsl_F
 */
public class RayTest {

    public RayTest() {
    }

    /**
     * Test of getAngle method, of class Ray.
     */
    @Test
    public void testGetAngle() {
        System.out.println("getAngle");
        Ray instance = new Ray(new Point(0, 0), new Vector(0, 1));
        Point point = new Point(1, 1);
        double expResult = -java.lang.Math.PI / 4;
        double result = instance.getAngle(point);
        assertEquals(expResult, result, Constants.EPS_ANGLE);
    }

    /**
     * Test of getAngle360 method, of class Ray.
     */
    @Test
    public void testGetAngle360() {
        System.out.println("getAngle360");
        Ray instance = new Ray(new Point(0, 0), new Vector(0, 1));
        Point point = new Point(1, 1);
        double expResult = 7 * java.lang.Math.PI / 4;
        double result = instance.getAngle360(point);
        assertEquals(expResult, result, Constants.EPS_ANGLE);
    }

}
