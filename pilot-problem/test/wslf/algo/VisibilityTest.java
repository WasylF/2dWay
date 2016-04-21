package wslf.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import wslf.geometry.Point;
import wslf.geometry.Polygon;

/**
 *
 * @author Wsl_F
 */
public class VisibilityTest {

    public VisibilityTest() {
    }

    /**
     *
     * @return world for test cases 1 - 16
     */
    static World getWorld1_16() {
        //(0,0), (5,5), (0,10), (5,12.5), (10, 10), (12.5, 5), (10, 0)
        Point[] verticies = {new Point(0, 0), new Point(5, 5), new Point(0, 10), new Point(5, 12.5), new Point(10, 10), new Point(12.5, 5), new Point(10, 0)};
        Polygon polygon = new Polygon(verticies);
        Polygon[] barriers = {polygon};
        return new World(barriers);
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible1() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(-5, -5);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 2, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        boolean check = expResult.containsAll(result) && result.containsAll(expResult);
        assertTrue(check);
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(5, 7.5);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2_2() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(7.5, 5);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible3() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(5, -5);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible4() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(15, 5);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible5() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(20, 10);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(3, 4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible6() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(-5, 5);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 1, 2));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible7() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(-10, 21);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 2, 3, 4));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible8() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(-10, 20);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 2, 3));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible9() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(0, 15);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(2, 3));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible10() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, 15);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(3, 4, 5));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible11() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(20, 0);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(6, 5, 4));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible12() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(15, 0);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(6, 5));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible13() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(11, 0);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(6, 5));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible14() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(20, -10);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 6, 5));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible15() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(5, -10);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible16() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(6, -10);
        World world = getWorld1_16();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    //   (7,3), (5,5), (8,4), (9,7), (10,3), (14,4), (11,2), (15,-2), (9,1), (6,-1)
    /**
     *
     * @return world for test cases 17 - 51
     */
    static World getWorld17_51() {
        //(6,-1), (7,3), (5,5), (8,4), (9,7), (10,3), (14,4), (11,2), (15,-2), (9,1) 
        Point[] verticies = {new Point(6, -1), new Point(7, 3), new Point(5, 5), new Point(8, 4),
            new Point(9, 7), new Point(10, 3), new Point(14, 4), new Point(11, 2),
            new Point(15, -2), new Point(9, 1)};
        Polygon polygon = new Polygon(verticies);
        Polygon[] barriers = {polygon};
        return new World(barriers);
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible17() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(0, 0);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 1, 2));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible18() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(3, -3);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 1, 2, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible19() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(5, -5);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 2));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible20() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, -2);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible20_1() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, 0);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible21() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(17, 6);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(8, 6, 5, 4));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible22() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(17, 5);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(8, 7, 6, 5, 4));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible23() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(18, 5);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(8, 7, 6, 4));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible24() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(12, 2);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(8, 7, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible25() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(9, 100);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(2, 3, 4, 5, 6, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible26() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(9, 10);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(2, 3, 4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible27() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(8, 8);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(2, 3, 4, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible28() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, 10);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(2, 4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible29() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(9.9, 10);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(2, 3, 4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible30() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(5, 3);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 1, 2));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible31() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(9, 0);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible32() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(9, -1);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible33() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(9, -2);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible34() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(9, -5);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible35() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, 0);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible36() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, -1);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible37() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, -2);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible38() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, -5);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible39() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(11, -1);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible40() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(11, -4);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible41() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, 4);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible42() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, 5);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible43() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, 6.4);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible44() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(11, 4);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible45() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(11, 5.8);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible46() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(12.57, 5.23);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible47() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(13, 6.7);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible48() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(13.01, 3.99);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible49() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(13, 5);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible50() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(14, 5);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible51() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(14, 7);
        World world = getWorld17_51();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(4, 5, 6, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    ////////////////////TESTS WITH 2 POLYGONS BEGINS////////////////////////////
    static World getWorld2Polygons1() {
        //(6,-1), (7,3), (5,5), (8,4), (9,7), (10,3), (14,4), (11,2), (15,-2), (9,1) 
        Point[] verticies1 = {new Point(6, -1), new Point(7, 3), new Point(5, 5), new Point(8, 4),
            new Point(9, 7), new Point(10, 3), new Point(14, 4), new Point(11, 2),
            new Point(15, -2), new Point(9, 1)};
        Polygon polygon1 = new Polygon(verticies1);

        //(5,-5), (15,-5), (15,-10), (5,-10)
        Point[] verticies2 = {new Point(5, -5), new Point(15, -5), new Point(15, -10), new Point(5, -10)};
        Polygon polygon2 = new Polygon(verticies2);
        Polygon[] barriers = {polygon1, polygon2};
        return new World(barriers);
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons1() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(0, 0);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 1, 2, 10, 11, 13));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons2() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, -11);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(12, 13));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons3() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(15, -11);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(12, 13));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons4() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(15.1, -11);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(11, 12, 13, 8));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons5() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(5, -11);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(12, 13));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons6() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(4.9, -11);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(2, 10, 12, 13));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons7() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, -3);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 10, 11));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons8() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, -2.5);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 10, 11));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons9() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, -4);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 10, 11));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons10() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10.3, -3.8);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 10, 11));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons11() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(9, -2.1);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 10, 11));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons12() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(8, -4.8);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 10, 11));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons13() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(11, -4.1);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 10, 11));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons14() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(11, -3);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 10, 11));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons15() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(8, -2);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 10, 11));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons16() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(9, -2);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 10, 11));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons17() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(10, -2);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 9, 8, 10, 11));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons18() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(20, 0);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(11, 12, 8, 7, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons19() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(19, 0);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(11, 12, 8, 7, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons20() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(15, 2);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(8, 7, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons21() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(15, 3);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(8, 7, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons22() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(14, 2);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(8, 7, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible2Polygons23() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(14, 3);
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(8, 7, 6));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }
    //////////////////// TESTS WITH 2 POLYGONS ENDS ///////////////////////////

    //////////////////// TESTS by vertex num BEGIN ///////////////////////////
    @Test
    public void testGetVisibleByVertexNum1() {
        System.out.println("testGetVisibleByVertexNum");
        World world = getWorld2Polygons1();
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 1, 2, 8, 9, 10, 11));
        LinkedList<Integer> result = instance.getVisible(0);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }
    ///////////////////  TESTS by vertex num END  ///////////////////////////

    static World getWorld3Polygons1() {
        // (-7, -5), (-7, 7), (-2, 7), (-2, -5) 
        Point[] verticies1 = {new Point(-7, -5), new Point(-7, 7), new Point(-2, 7), new Point(-2, -5)};
        Polygon polygon1 = new Polygon(verticies1);

        //(5, -10), (5,-5), (10, -5), (10, -10)
        Point[] verticies2 = {new Point(5, -10), new Point(5, -5), new Point(10, -5), new Point(10, -10)};
        Polygon polygon2 = new Polygon(verticies2);

        // (10, 0), (15, 10), (20, 0), (15, -10)
        Point[] verticies3 = {new Point(10, 0), new Point(15, 10), new Point(20, 0), new Point(15, -10)};
        Polygon polygon3 = new Polygon(verticies3);

        Polygon[] barriers = {polygon1, polygon2, polygon3};
        return new World(barriers);
    }

    @Test
    public void testBuildVisibilityGraph() {
        System.out.println("testBuildVisibilityGraph");
        World world = getWorld3Polygons1();
        Visibility instance = new Visibility(world);

        ArrayList<ArrayList<Integer>> expResult = new ArrayList<>();
        expResult.add(new ArrayList<>(Arrays.asList(0, 1, 3, 4))); // 0
        expResult.add(new ArrayList<>(Arrays.asList(0, 1, 2, 9))); // 1
        expResult.add(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 9))); // 2
        expResult.add(new ArrayList<>(Arrays.asList(0, 2, 3, 4, 5, 8, 9))); // 3

        expResult.add(new ArrayList<>(Arrays.asList(0, 2, 3, 4, 5, 7))); // 4
        expResult.add(new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 8, 9))); // 5
        expResult.add(new ArrayList<>(Arrays.asList(2, 5, 6, 7, 8, 11))); // 6
        expResult.add(new ArrayList<>(Arrays.asList(4, 6, 7, 11))); // 7

        expResult.add(new ArrayList<>(Arrays.asList(2, 3, 5, 6, 8, 9, 11))); // 8
        expResult.add(new ArrayList<>(Arrays.asList(1, 2, 3, 5, 8, 9, 10))); // 9
        expResult.add(new ArrayList<>(Arrays.asList(9, 10, 11))); // 10
        expResult.add(new ArrayList<>(Arrays.asList(6, 7, 8, 10, 11))); // 11

        expResult.add(new ArrayList<>());
        expResult.add(new ArrayList<>());

        ArrayList<ArrayList<Integer>> result = instance.buildVisibilityGraph();

        for (int i = 0; i < 12; i++) {
            boolean f = result.get(i).containsAll(expResult.get(i));
            f &= expResult.get(i).containsAll(result.get(i));
            assertTrue(f);
        }

    }

}
