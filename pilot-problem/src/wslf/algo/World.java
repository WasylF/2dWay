package wslf.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static java.lang.Double.min;
import static java.lang.Math.PI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import wslf.geometry.*;

/**
 *
 * @author Wsl_F
 */
public class World {

    /**
     * array that contains all barriers in the world
     */
    final Polygon[] barriers;

    public Polygon[] getBarriers() {
        return barriers;
    }

    public World(Polygon[] barriers) {
        this.barriers = barriers;
    }

    public World(List<Polygon> barriers) {
        Polygon[] polygons = new Polygon[barriers.size()];
        polygons = barriers.toArray(polygons);
        this.barriers = polygons;
    }

    /**
     *
     * @return all vertices of all polygons (barriers) in the world
     */
    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();
        for (Polygon barrier : barriers) {
            points.addAll(barrier.getPointsList());
        }
        return points;
    }

    /**
     *
     * @return all segments (edges) of all polygons (barriers) in the world
     */
    public ArrayList<Segment> getSegments() {
        ArrayList<Segment> segments = new ArrayList<>();
        for (Polygon barrier : barriers) {
            segments.addAll(barrier.getSegmentsList());
        }
        return segments;
    }

    /**
     *
     * @param point point
     * @return number of barrier that contains {@code point} or -1 if point
     * outside
     */
    public int getBarrierNumber(Point point) {
        for (int i = 0; i < barriers.length; i++) {
            if (barriers[i].contains(point)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Get polygon by number, or null if doesn't exist
     *
     * @param i number of barrier
     * @return barrier № {@code i}
     */
    public Polygon getBarrier(int i) {
        if (i < 0 || i >= barriers.length) {
            return null;
        }

        return barriers[i];
    }

    public static World readWorld(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName)).useLocale(Locale.US);
            int numberOfBarriers = sc.nextInt();
            Polygon[] barriers = new Polygon[numberOfBarriers];

            for (int i = 0; i < numberOfBarriers; i++) {
                int numberOfVertices = sc.nextInt();
                Point[] vertices = new Point[numberOfVertices];

                for (int j = 0; j < numberOfVertices; j++) {
                    double x = sc.nextDouble();
                    double y = sc.nextDouble();
                    vertices[j] = new Point(x, y);
                }

                barriers[i] = new Polygon(vertices);
            }
            return new World(barriers);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static World generateWorld(int numberOfBarriers) {
        final int blockSizeX = 16;
        final int blockSizeY = 16;

        Polygon[] barriers = new Polygon[numberOfBarriers];

        for (int i = 0; i < numberOfBarriers; i++) {
            Point[] vertices = generateTriangle(blockSizeX, blockSizeY);

            for (Point vertex : vertices) {
                vertex.setX(((double) blockSizeX) * i + vertex.getX());
            }
            barriers[i] = new Polygon(vertices);

        }
        return new World(barriers);
    }

    private static Point[] generateTriangle(int blockSizeX, int blockSizeY) {
        Random random = new Random();
        Point[] vertices = new Point[3];
        vertices[0] = new Point(1, 1);
        vertices[1] = new Point(blockSizeX - 2, 1);
        vertices[2] = new Point(blockSizeX / 2, blockSizeY - 2);

        return vertices;
    }

    public static void printWorld(String fileName, World world) {
        try {
            PrintWriter pw = new PrintWriter(new File(fileName));
            pw.println(world.barriers.length);
            for (Polygon barrier : world.barriers) {
                pw.println(barrier.getSize());
                for (Point vertex : barrier.getPointsList()) {
                    pw.print(vertex.getX() + " " + vertex.getY() + " ");
                }
                pw.println();
            }
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static World generateWorld(int blockNumX, int blockNumY, int minX, int maxX, int minY, int maxY) {
        LinkedList<Polygon> barriers = new LinkedList<>();

        int maxBlockX = maxX - minX;
        maxBlockX /= blockNumX;
        int maxBlockY = maxY - minY;
        maxBlockY /= blockNumY;

        for (int x = 0; x < blockNumX; x++) {
            for (int y = 0; y < blockNumY; y++) {
                if (y % 2 == 0) {
                    barriers.add(generatePolygon(maxBlockX, maxBlockY, maxBlockX * x, maxBlockY * y));
                } else {
                    if (y == 0) {
                        continue;
                    }
                    barriers.add(generatePolygon(maxBlockX, maxBlockY, (int) (maxBlockX * (x - 0.55)), (int) (maxBlockY * (y))));
                }
            }
        }

        return new World(barriers);
    }

    private static Polygon generatePolygon(int maxX, int maxY, int marginX, int marginY) {
        double centerX = marginX + maxX / 2;
        double centerY = marginY + maxY / 2;

        final int anglesNum = 30;
        boolean[] angles = new boolean[anglesNum];
        angles[0] = true;
        final int[] numberOfVertices = {3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 7, 8, 9, 10};
        Random random = new Random();

        int n = random.nextInt(numberOfVertices.length);
        n = numberOfVertices[n];

        for (int i = 1; i < n; i++) {
            int angle;
            do {
                angle = random.nextInt(anglesNum - 1) + 1;
            } while (angles[angle]);
            angles[angle] = true;
        }

        Point[] vertices = new Point[n];
        int radius = maxX < maxY ? maxX / 2 : maxY / 2;
        int maxRand = radius * 10;
        int index = 0;
        for (int angle = anglesNum - 1; angle >= 0; angle--) {
            if (angles[angle]) {
                Vector v = new Vector(2.0 * angle * PI / anglesNum);
                v.normalize();
                double leng = maxRand / 20 + (double) random.nextInt(maxRand) / 20.0;
                v.multiplyScalar(leng * 1.4);
                vertices[index++] = new Point(centerX + v.getX(), centerY + v.getY());
            }
        }

        Polygon polygon = new Polygon(vertices);
        if (polygon.area() < (maxX * maxY) / (min(1.2 * n, 5))) {
            polygon = generatePolygon(maxX, maxY, marginX, marginY);
        }
        
        return polygon;
    }
}
