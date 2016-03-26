package wslf.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import wslf.geometry.Point;
import wslf.geometry.Segment;

/**
 *
 * @author Wsl_F
 */
public class Visibility {

    /**
     * list of all edges of all barriers in the world
     */
    private final ArrayList<Segment> segments;
    /**
     * number of polygon (barrier) that contains point (as vertex)
     */
    private final HashMap<Point, Integer> pointsToPolygon;
    /**
     * list of all points (vertexes) of all polygons (barriers)
     */
    private final ArrayList<Point> vertices;

    private ArrayList<ArrayList<Integer>> visGraph;
    /**
     * total number of all vertexes in all polygons
     */
    private int nVertex;
    /**
     * world with barriers
     */
    private final World world;

    public Visibility(World world) {
        this.segments = new ArrayList<>();
        this.pointsToPolygon = new HashMap<>();
        this.vertices = new ArrayList<>();
        this.world = world;
    }

    private void buildVisability() {
        nVertex = 0;
        for (int i = 0; i < world.barriers.length; i++) {
            Segment[] sg = world.barriers[i].toSegments();
            for (int j = 0; j < sg.length; j++) {
                sg[j].orders();
                segments.add(sg[j]);
            }

            LinkedList<Point> points = new LinkedList<>();
            world.barriers[i].toPointsList(points);

            vertices.addAll(points);
            for (Point point : points) {
                pointsToPolygon.put(point, i);
            }

            nVertex += world.barriers[i].getSize();
        }
        Collections.sort(segments);

        visGraph = new ArrayList<>(vertices.size());
    }

    /**
     * find all points, that visible from vertex № v
     *
     * @param v number of Point (vertex) in vertices
     */
    private void buildVisability(int v) {

    }
}
