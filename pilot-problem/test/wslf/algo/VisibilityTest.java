/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wslf.algo;

import java.util.Arrays;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
     * Test of getVisible method, of class Visibility.
     */
    @Test
    public void testGetVisible() {
        System.out.println("getVisible");
        Point heatingPoint = new Point(-5, -5);
        //(0,0), (5,5), (0,10), (5,12.5), (10, 10), (12.5, 5), (10, 0)
        Point[] verticies = {new Point(0, 0), new Point(5, 5), new Point(0, 10), new Point(5, 12.5), new Point(10, 10), new Point(12.5, 5), new Point(10, 0)};
        Polygon polygon = new Polygon(verticies);
        Polygon[] barriers = {polygon};
        World world = new World(barriers);
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
        //(0,0), (5,5), (0,10), (5,12.5), (10, 10), (12.5, 5), (10, 0)
        Point[] verticies = {new Point(0, 0), new Point(5, 5), new Point(0, 10), new Point(5, 12.5), new Point(10, 10), new Point(12.5, 5), new Point(10, 0)};
        Polygon polygon = new Polygon(verticies);
        Polygon[] barriers = {polygon};
        World world = new World(barriers);
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
        //(0,0), (5,5), (0,10), (5,12.5), (10, 10), (12.5, 5), (10, 0)
        Point[] verticies = {new Point(0, 0), new Point(5, 5), new Point(0, 10), new Point(5, 12.5), new Point(10, 10), new Point(12.5, 5), new Point(10, 0)};
        Polygon polygon = new Polygon(verticies);
        Polygon[] barriers = {polygon};
        World world = new World(barriers);
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
        //(0,0), (5,5), (0,10), (5,12.5), (10, 10), (12.5, 5), (10, 0)
        Point[] verticies = {new Point(0, 0), new Point(5, 5), new Point(0, 10), new Point(5, 12.5), new Point(10, 10), new Point(12.5, 5), new Point(10, 0)};
        Polygon polygon = new Polygon(verticies);
        Polygon[] barriers = {polygon};
        World world = new World(barriers);
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
        //(0,0), (5,5), (0,10), (5,12.5), (10, 10), (12.5, 5), (10, 0)
        Point[] verticies = {new Point(0, 0), new Point(5, 5), new Point(0, 10), new Point(5, 12.5), new Point(10, 10), new Point(12.5, 5), new Point(10, 0)};
        Polygon polygon = new Polygon(verticies);
        Polygon[] barriers = {polygon};
        World world = new World(barriers);
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
        //(0,0), (5,5), (0,10), (5,12.5), (10, 10), (12.5, 5), (10, 0)
        Point[] verticies = {new Point(0, 0), new Point(5, 5), new Point(0, 10), new Point(5, 12.5), new Point(10, 10), new Point(12.5, 5), new Point(10, 0)};
        Polygon polygon = new Polygon(verticies);
        Polygon[] barriers = {polygon};
        World world = new World(barriers);
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
        //(0,0), (5,5), (0,10), (5,12.5), (10, 10), (12.5, 5), (10, 0)
        Point[] verticies = {new Point(0, 0), new Point(5, 5), new Point(0, 10), new Point(5, 12.5), new Point(10, 10), new Point(12.5, 5), new Point(10, 0)};
        Polygon polygon = new Polygon(verticies);
        Polygon[] barriers = {polygon};
        World world = new World(barriers);
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
        //(0,0), (5,5), (0,10), (5,12.5), (10, 10), (12.5, 5), (10, 0)
        Point[] verticies = {new Point(0, 0), new Point(5, 5), new Point(0, 10), new Point(5, 12.5), new Point(10, 10), new Point(12.5, 5), new Point(10, 0)};
        Polygon polygon = new Polygon(verticies);
        Polygon[] barriers = {polygon};
        World world = new World(barriers);
        Visibility instance = new Visibility(world);

        LinkedList<Integer> expResult = new LinkedList<>(Arrays.asList(0, 2, 3, 4));
        LinkedList<Integer> result = instance.getVisible(heatingPoint);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

}
