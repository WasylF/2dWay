package wslf.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import wslf.geometry.*;

import static wslf.geometry.Constants.*;
import static java.lang.Math.*;
import java.util.TreeSet;

/**
 *
 * @author Wsl_F
 */
public class Visibility {

    /**
     * list of all edges of all barriers in the world
     */
    private ArrayList<Segment> segments;

    /**
     * list of all points (vertexes) of all polygons (barriers)
     */
    private ArrayList<Point> vertices;

    /**
     * total number of all vertexes in all polygons
     */
    private int nVertex;
    /**
     * world with barriers
     */
    private final World world;

    /**
     * graph of visibility. visGraph[i] - list of numbers of points those
     * visible from vertex №i. Coordinates of vertex №i is vertices[i]
     */
    private final ArrayList<ArrayList<Integer>> visGraph;

    /**
     * match vertex to it number (id) in vertices
     */
    private HashMap<Point, Integer> pointNumber;

    /**
     * vertexToSegments[i] - list of segment's numbers that begins or ends in
     * vertxe №i
     */
    private ArrayList<ArrayList<Integer>> vertexToSegments;

    public Visibility(World world) {
        this.world = world;
        getSegments();
        enumarateVertices();
        matchSegmentsToVertices();

        // 2 aditional for start and finish points
        visGraph = new ArrayList<>(nVertex + 2);

    }

    private void getSegments() {
        this.segments = new ArrayList<>();//world.getSegments();

        for (Polygon barrier : world.barriers) {
            Segment[] sg = barrier.toSegments();
            for (Segment sg1 : sg) {
                sg1.orders();
                segments.add(sg1);
            }
        }
    }

    private void enumarateVertices() {
        this.vertices = world.getPoints();
        nVertex = vertices.size();

        pointNumber = new HashMap<>();
        for (int i = 0; i < nVertex; i++) {
            if (!pointNumber.containsKey(vertices.get(i))) {
                pointNumber.put(vertices.get(i), i);
            }
        }
    }

    private void matchSegmentsToVertices() {
        vertexToSegments = new ArrayList<>(nVertex);
        for (int i = 0; i < nVertex; i++) {
            vertexToSegments.add(new ArrayList<>());
        }

        for (int i = 0; i < segments.size(); i++) {
            vertexToSegments.get(pointNumber.get(segments.get(i).getA())).add(i);
            vertexToSegments.get(pointNumber.get(segments.get(i).getB())).add(i);
        }
    }
}
