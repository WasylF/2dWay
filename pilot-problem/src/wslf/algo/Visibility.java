package wslf.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import wslf.geometry.*;

import static wslf.geometry.Constants.*;
import static java.lang.Math.*;
import java.util.HashSet;
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
     * pointOrder[i] - index in sorted sequence of point №i
     */
    private Integer[] pointOrder;

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

    /**
     * orders points in all segments by clockwise, with centre in
     * {@code heatingPoint}
     *
     * @param heatingPoint center of clocks
     */
    private void ordersSegmentsClockwise(Point heatingPoint, boolean reversed) {
        for (Segment segment : segments) {
            segment.ordersByClockwise(heatingPoint, reversed);
        }
    }

    /**
     * comparator for sorting points on clockwise
     */
    public class PointsAngleComparator implements Comparator<Integer> {

        private final Point heatingPoint;
        private final boolean reversed;

        public PointsAngleComparator(Ray ray, boolean rev) {
            this.heatingPoint = ray.getPoint();
            this.reversed = rev;
        }

        @Override
        public int compare(Integer p1, Integer p2) {
            return vertices.get(p1).compareByClockwise(vertices.get(p2), heatingPoint, reversed);
        }

    };

    /**
     * comparator for sorting segments by distance from ray's begin to
     * intersection. ray should intersects segments.
     */
    public class SegmentsDistComparator implements Comparator<Integer> {

        public SegmentsDistComparator(Ray ray, boolean reversed) {
            this.ray = ray;
            this.reversed = reversed;
        }

        private Ray ray;
        private boolean reversed;

        @Override
        public int compare(Integer sg1, Integer sg2) {
            if (sg1.equals(sg2) || segments.get(sg1).equals(segments.get(sg2))) {
                return 0;
            }

            double d1 = ray.distToIntersection(segments.get(sg1));
            double d2 = ray.distToIntersection(segments.get(sg2));

            // if ray doesn't intersect one of segments
            if (d1 < 0 || d2 < 0) {
                return d1 < 0 ? 1 : -1;
            }

            if (abs(d1 - d2) < EPS) {
                Segment segm1 = segments.get(sg1);
                Segment segm2 = segments.get(sg2);
                if (segm1.isIntersect(new Segment(segm2.getA(), ray.getPoint()))
                        && segm1.isIntersect(new Segment(segm2.getB(), ray.getPoint()))) {
                    return -1;
                }
                if (segm2.isIntersect(new Segment(segm1.getA(), ray.getPoint()))
                        && segm2.isIntersect(new Segment(segm1.getB(), ray.getPoint()))) {
                    return 1;
                }

                Integer p1A = pointNumber.get(segments.get(sg1).getA());
                Integer p1B = pointNumber.get(segments.get(sg1).getB());
                Integer p2A = pointNumber.get(segments.get(sg2).getA());
                Integer p2B = pointNumber.get(segments.get(sg2).getB());
                d1 = pointOrder[p1A] + pointOrder[p1B];
                d2 = pointOrder[p2A] + pointOrder[p2B];
                if (abs(d1 - d2) < EPS) {
                    return 0;
                }
            }

            return d1 < d2 ? -1 : 1;
        }

    };

    /**
     * find visible vertices from {@code heatingPoint}
     *
     * @param heatingPoint view point
     * @return list of numbers of visible vertices
     */
    public LinkedList<Integer> getVisible(Point heatingPoint) {
        HashSet<Integer> visible = new HashSet<>();

        ordersSegmentsClockwise(heatingPoint, true);
        visible.addAll(getVisibleLeft(heatingPoint));

        ordersSegmentsClockwise(heatingPoint, false);
        visible.addAll(getVisibleRight(heatingPoint));

        return new LinkedList<>(visible);
    }

    /**
     * find visible vertices from {@code heatingPoint}
     *
     * @param heatingPoint view point
     * @return list of numbers of visible vertices
     */
    public LinkedList<Integer> getVisibleRight(Point heatingPoint) {
        // clockwise sorted points
        ArrayList<Integer> points = new ArrayList<>();
        for (Integer pN : pointNumber.values()) {
            if (heatingPoint.getX() <= vertices.get(pN).getX()) {
                points.add(pN);
            }
        }

        if (points.isEmpty()) {
            return new LinkedList<>();
        }
        return getVisible(heatingPoint, points, false);
    }

    /**
     * find visible vertices from {@code heatingPoint}
     *
     * @param heatingPoint view point
     * @return list of numbers of visible vertices
     */
    private LinkedList<Integer> getVisibleLeft(Point heatingPoint) {
        // reverse clockwise sorted points
        ArrayList<Integer> points = new ArrayList<>();
        for (Integer pN : pointNumber.values()) {
            if (heatingPoint.getX() >= vertices.get(pN).getX()) {
                points.add(pN);
            }
        }

        if (points.isEmpty()) {
            return new LinkedList<>();
        }
        return getVisible(heatingPoint, points, true);
    }

    private void initPoints_Status(Point heatingPoint, ArrayList<Integer> points,
            TreeSet<Integer> status, Ray ray, boolean reversed,
            TreeSet<Integer> unvisiblePoints) {

        Collections.sort(points, new PointsAngleComparator(ray, reversed));
        int pointsSize = points.size();
        int vertSize = vertices.size() + 10;

        pointOrder = new Integer[vertSize];
        for (int i = 0; i < pointOrder.length; i++) {
            pointOrder[i] = vertSize;
        }

        pointOrder[points.get(0)] = 0;
        for (int i = 1; i < pointsSize; i++) {
            pointOrder[points.get(i)] = i;
            Vector vPrev = new Vector(heatingPoint, vertices.get(points.get(i - 1)));
            Vector vCur = new Vector(heatingPoint, vertices.get(points.get(i)));
            if (vPrev.isCollinear(vCur)) {
                unvisiblePoints.add(points.get(i));
            }
        }

        Point firstPoint = vertices.get(points.get(0));
        // rotate ray on first point 
        ray.setVector(new Vector(heatingPoint, firstPoint));

        for (int i = 0; i < segments.size(); i++) {
            if (ray.isIntersects(segments.get(i)) || segments.get(i).contains(firstPoint)) {
                status.add(i);
            }
        }
    }

    private void processVertex(int i, Point heatingPoint, Ray ray,
            ArrayList<Integer> points,
            TreeSet<Integer> status,
            LinkedList<Integer> visible,
            TreeSet<Integer> unvisiblePoints) {
        int curPointN = points.get(i);
        Point curPoint = vertices.get(curPointN);

        Segment segment = new Segment(heatingPoint, curPoint);
        boolean addToVisible;
        printDebug(i, status, points.size() - 1, curPointN, curPoint);

        if (status.isEmpty()) {
            addToVisible = true;
        } else {
            Segment closest = segments.get(status.first());
            addToVisible = !segment.isIntersect(closest);
            addToVisible |= abs(heatingPoint.distance(curPoint) - ray.distToIntersection(closest)) < EPS;
            addToVisible &= !unvisiblePoints.contains(curPointN);
        }

        if (addToVisible) {
            visible.add(curPointN);
        }

        // erase "incoming" edges
        for (Integer sg : vertexToSegments.get(curPointN)) {
            if (segments.get(sg).getB().equals(curPoint)) {
                status.remove(sg);
            }
        }

        ray.setVector(new Vector(heatingPoint, vertices.get(points.get(i + 1))));

        // add "outgoing" edges
        for (Integer sg : vertexToSegments.get(curPointN)) {
            if (segments.get(sg).getA().equals(curPoint)) {
                status.add(sg);
            }
        }
    }

    private LinkedList<Integer> getVisible(Point heatingPoint, ArrayList<Integer> points, boolean reversed) {
        // vertical ray
        Ray ray = new Ray(heatingPoint, new Vector(0, 1));
        TreeSet<Integer> status = new TreeSet<>(new SegmentsDistComparator(ray, reversed));
        /**
         * There is possible that ray could contains few points at the same
         * time. in this case only closest point is visible.
         * {@code unvisiblePoints} contains other (not closest) points.
         */
        TreeSet<Integer> unvisiblePoints = new TreeSet<>();
        initPoints_Status(heatingPoint, points, status, ray, reversed, unvisiblePoints);

        LinkedList<Integer> visible = new LinkedList<>();
        int pointsSize = points.size();
        points.add(points.get(0));
        for (int i = 0; i < pointsSize; i++) {
            processVertex(i, heatingPoint, ray, points, status, visible, unvisiblePoints);
        }

        return visible;
    }

    private void printDebug(int i, TreeSet<Integer> status, int pointsSize, int curPointN, Point curPoint) {
        System.out.println((i + 1) + ") of " + pointsSize);
        System.out.println("curPoint: " + curPointN + " :  " + curPoint);

        System.out.println("current status:");
        for (Integer s : status) {
            System.out.println(s + ":  " + segments.get(s));
        }
        if (status.isEmpty()) {
            System.out.println("Status is empty!");
        } else {
            System.out.println("closest: " + status.first() + "  -  " + segments.get(status.first()));
        }
        System.out.println("\n\n");
    }

}
