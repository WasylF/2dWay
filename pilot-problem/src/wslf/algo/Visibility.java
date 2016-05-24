package wslf.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import wslf.geometry.*;

import static wslf.geometry.Constants.*;
import static java.lang.Math.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
import javafx.util.Pair;

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
    private final ArrayList<ArrayList<Pair<Integer, Double>>> visGraph;

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
        for (int i = nVertex + 1; i >= 0; i--) {
            visGraph.add(new ArrayList<>());
        }
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
        vertices.add(new Point());
        vertices.add(new Point());

        pointNumber = new HashMap<>();
        for (int i = 0; i < nVertex; i++) {
            if (!pointNumber.containsKey(vertices.get(i))) {
                pointNumber.put(vertices.get(i), i);
            }
        }
    }

    private void matchSegmentsToVertices() {
        vertexToSegments = new ArrayList<>(nVertex);
        for (int i = nVertex + 1; i >= 0; i--) {
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

            if (abs(d1 - d2) < EPS
                    || Segment.samePoints(segments.get(sg1), segments.get(sg2))) {
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
        int vertSize = vertices.size();
        pointOrder = new Integer[vertSize];
        for (int i = 0; i < pointOrder.length; i++) {
            pointOrder[i] = vertSize;
        }
        for (int i = 0; i < pointsSize; i++) {
            pointOrder[points.get(i)] = i;
        }

        addUnvisible(heatingPoint, points, ray, reversed, unvisiblePoints);

        Point firstPoint = vertices.get(points.get(0));
        if (firstPoint.equals(heatingPoint)) {
            if (points.size() == 1) {
                points.clear();
                return;
            }
            ray.setVector(new Vector(0, 1));
        } else {
            // rotate ray on first point 
            ray.setVector(new Vector(heatingPoint, firstPoint));
        }

        for (int i = 0; i < segments.size(); i++) {
            if (segments.get(i).contains(firstPoint) || ray.isIntersects(segments.get(i))) {
                if (!segments.get(i).contains(heatingPoint)) {
                    status.add(i);
                }
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

        boolean addToVisible;
        printDebug(i, status, points.size() - 1, curPointN, curPoint);

        if (status.isEmpty() || heatingPoint.equals(curPoint)) {
            addToVisible = true;
        } else {
            Segment segment = new Segment(heatingPoint, curPoint);
            Segment closest = segments.get(status.first());
            Point intersection = segment.getIntersection(closest);
            addToVisible = intersection == null || intersection.equals(heatingPoint);
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
        if (!heatingPoint.equals(curPoint)) {
            for (Integer sg : vertexToSegments.get(curPointN)) {
                if (segments.get(sg).getA().equals(curPoint)) {
                    status.add(sg);
                }
            }
        }
    }

    private LinkedList<Integer> getVisible(Point heatingPoint, ArrayList<Integer> points, boolean reversed) {
        //result
        LinkedList<Integer> visible = new LinkedList<>();
        if (points.isEmpty()) {
            return visible;
        }
        if (points.size() == 1) {
            visible.add(points.get(0));
            return visible;
        }

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

        int pointsSize = points.size();
        points.add(points.get(pointsSize - 1));
        for (int i = 0; i < pointsSize; i++) {
            processVertex(i, heatingPoint, ray, points, status, visible, unvisiblePoints);
        }
        points.remove(pointsSize);

        return visible;
    }

    /**
     * calculate some unvisible points
     *
     * @param heatingPoint view point
     * @param points list of vertices numbers ordered by clockwise or
     * anticlockwise
     * @param ray view ray
     * @param reversed determine order: clockwise or anticlockwise. true -
     * @param unvisiblePoints set of some unvisible vertices
     */
    private void addUnvisible(Point heatingPoint, ArrayList<Integer> points,
            Ray ray, boolean reversed, TreeSet<Integer> unvisiblePoints) {
        int pointsSize = points.size();
        Vector vPrev, vCur;
        if (heatingPoint.equals(vertices.get(points.get(0)))) {
            vCur = new Vector(heatingPoint, vertices.get(points.get(1)));
            vCur.rotateVector(PI / 360);
        } else {
            vCur = new Vector(heatingPoint, vertices.get(points.get(0)));
        }
        for (int i = 1; i < pointsSize; i++) {
            vPrev = vCur;
            vCur = new Vector(heatingPoint, vertices.get(points.get(i)));
            if (vPrev.isCollinear(vCur)) {
                unvisiblePoints.add(points.get(i));
            }
        }

        // if heating point is vertex of some barrier 
        int bNumber = world.getBarrierNumber(heatingPoint);
        if (bNumber >= 0) {
            Polygon barrier = world.getBarrier(bNumber);
            LinkedList<Integer> neighbors
                    = getPointsNumbers(barrier.getAdjacentVertices(heatingPoint));

            if (!neighbors.isEmpty()) {
                Vector toBegin = new Vector(heatingPoint, vertices.get(neighbors.getFirst()));
                Vector toEnd = new Vector(heatingPoint, vertices.get(neighbors.getLast()));
                double angle = toBegin.getAngle2PI(toEnd);

                for (Integer vertex : points) {
                    if (heatingPoint.equals(vertices.get(vertex))) {
                        continue;
                    }
                    Vector v = new Vector(heatingPoint, vertices.get(vertex));
                    if (toBegin.getAngle2PI(v) <= angle) {
                        unvisiblePoints.add(vertex);
                    }
                }
            }
        }

    }

    /**
     * "convert" collection from coordinates to numbers of vertices
     *
     * @param points collection of points
     * @return list of numbers of vertices
     */
    private LinkedList<Integer> getPointsNumbers(Collection<Point> points) {
        LinkedList<Integer> numbers = new LinkedList<>();
        for (Point point : points) {
            numbers.add(pointNumber.get(point));
        }
        return numbers;
    }

    public LinkedList<Point> getPointsByNumbers(Collection<Integer> numbers) {
        LinkedList<Point> points = new LinkedList<>();
        for (int numb : numbers) {
            points.add(vertices.get(numb));
        }
        return points;
    }

    /**
     * find visible vertices from vertex number {@code heatingPoint}
     *
     * @param heatingPoint number of view point in verticies
     * @return list of numbers of visible vertices
     */
    public LinkedList<Integer> getVisible(int heatingPoint) {
        Point point = vertices.get(heatingPoint);
        int barrierNumb = world.getBarrierNumber(point);
        Polygon barrier = world.getBarrier(barrierNumb);

        TreeSet<Integer> visible = new TreeSet<>();
        if (barrier != null) {
            LinkedList<Point> adjacentVertices = barrier.getAdjacentVertices(point);
            visible.addAll(getPointsNumbers(adjacentVertices));
        }
        visible.addAll(getVisible(point));
        visible.remove(heatingPoint);

        return new LinkedList<>(visible);
    }

    /**
     * builds visibility graph
     *
     * @return visibility graph
     */
    public ArrayList<ArrayList<Pair<Integer, Double>>> buildVisibilityGraph() {
        for (int i = nVertex - 1; i >= 0; i--) {
            System.err.println("build: " + i);
            addPointToVisGraph(i);
        }

        return visGraph;
    }

    /**
     * update {@code visGraph} to add new point
     *
     * @param index index for {@code point} in visibility graph
     */
    private LinkedList<Integer> addPointToVisGraph(int index) {
        // delete adjacent list for previous point
        visGraph.get(index).clear();

        LinkedList<Integer> visibles = getVisible(index);
        // add all visible points for current point
        for (int visible : visibles) {
            double dist = vertices.get(index).distance(vertices.get(visible));
            visGraph.get(index).add(new Pair<>(visible, dist));
        }

        return visibles;
    }

    /**
     * update {@code visGraph} to add new point
     *
     * @param index index for {@code point} in visibility graph
     */
    private void addSeparatePointToVisGraph(int index) {
        LinkedList<Integer> visibles = addPointToVisGraph(index);

        // add current point to adjacents lists for all visible points
        for (int vertex : visibles) {
            double dist = vertices.get(index).distance(vertices.get(vertex));
            visGraph.get(vertex).add(new Pair<>(index, dist));
        }
    }

    /**
     * update {@code visGraph} to add new start and finish points
     *
     * @param start new start point
     * @param finish new finish point
     * @return
     */
    public ArrayList<ArrayList<Pair<Integer, Double>>> updateVisibilityGraph(Point start, Point finish) {
        // delete connections to previous start/finish points
        visGraph.stream().forEach((list) -> {
            while (!list.isEmpty() && list.get(list.size() - 1).getKey() >= nVertex) {
                list.remove(list.size() - 1);
            }
        });

        Point prevStart = vertices.get(nVertex);
        Point prevFinish = vertices.get(nVertex + 1);
        if (prevStart != null) {
            pointNumber.remove(prevStart);
        }
        if (prevFinish != null) {
            pointNumber.remove(prevFinish);
        }

        vertices.set(nVertex, start);
        vertices.set(nVertex + 1, finish);
        pointNumber.put(start, nVertex);
        pointNumber.put(finish, nVertex + 1);

        addSeparatePointToVisGraph(nVertex);
        addSeparatePointToVisGraph(nVertex + 1);

        return visGraph;
    }

    private void printDebug(int i, TreeSet<Integer> status, int pointsSize, int curPointN, Point curPoint) {
        /*        System.out.println((i + 1) + ") of " + pointsSize);
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
         System.out.println("\n\n");*/
    }

}
