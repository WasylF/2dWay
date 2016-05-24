package wslf.geometry;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Wsl_F
 */
public class Polygon {

    // vertices sorted by clockwise
    Point[] vertices;

    public Polygon(Point[] vertexes) {
        this.vertices = vertexes.clone();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[" + vertices.length + ":");

        for (int i = 0; i < vertices.length; i++) {
            s.append(vertices[i]);
        }

        s.append("]");

        return s.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        return equals((Polygon) obj);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Arrays.deepHashCode(this.vertices);
        return hash;
    }

    public double area() {
        double S;
        S = vertices[vertices.length - 1].x * vertices[0].y - vertices[0].x * vertices[vertices.length - 1].y;
        for (int i = vertices.length - 2; i >= 0; i--) {
            S += vertices[i].x * vertices[i + 1].y - vertices[i + 1].x * vertices[i].y;
        }

        S = abs(S) / 2;
        return S;
    }

    public boolean equals(Polygon p) {
        if (getSize() != p.getSize()) {
            return false;
        }
        int shift = 0;

        while (shift < vertices.length) {
            if (vertices[shift].equals(p.vertices[0])) {
                break;
            }
            shift++;
        }

        if (shift == vertices.length) {
            return false;
        }

        for (int i = 1; i < vertices.length; i++) {
            if (!vertices[(i + shift) % vertices.length].equals(p.vertices[i])) {
                return false;
            }
        }

        return true;
    }

    public int getSize() {
        return vertices.length;
    }

    /**
     * check does the point belongs (inside or in border) to this polygon
     *
     * @param p Point
     * @return true if point inside or on border of polygon
     */
    public boolean contains(Point p) {
        for (Point v : vertices) {
            if (v.equals(p)) {
                return true;
            }
        }

        int in = 0;
        final int rpTimes = 5;
        for (int rep = 0; rep < rpTimes; rep++) {
            int intersectionNum = 0;
            Ray ray = new Ray(p, new Vector(java.lang.Math.random(), java.lang.Math.random()));
            for (Segment sgm : toSegments()) {
                if (ray.isIntersects(sgm)) {
                    intersectionNum++;
                }
            }
            for (Point v : vertices) {
                if (ray.contains(v)) {
                    intersectionNum--;
                }
            }

            if (intersectionNum % 2 == 1) {
                in++;
            }
        }

        return in == rpTimes;
    }

    /**
     *
     * @return array of polygon's vertices
     */
    public Point[] toPoints() {
        return vertices.clone();
    }

    /**
     * returns list of polygon's vertices
     *
     * @param list result
     */
    public void toPointsList(List<Point> list) {
        list.clear();
        list.addAll(Arrays.asList(vertices));
    }

    /**
     *
     * @return list of polygon's vertices
     */
    public ArrayList<Point> getPointsList() {
        ArrayList<Point> list = new ArrayList<>();
        list.addAll(Arrays.asList(vertices));
        return list;
    }

    /**
     * @return array of sequential segments
     */
    public Segment[] toSegments() {
        Segment[] segments = new Segment[vertices.length];

        segments[vertices.length - 1] = new Segment(vertices[vertices.length - 1], vertices[0]);

        for (int i = vertices.length - 2; i >= 0; i--) {
            segments[i] = new Segment(vertices[i], vertices[i + 1]);
        }

        for (Segment segment : segments) {
            segment.orders();
        }

        return segments;
    }

    /**
     * return list of sequential segments
     *
     * @param list result
     */
    public void toSegments(List<Segment> list) {
        list.clear();
        list.addAll(Arrays.asList(toSegments()));
    }

    /**
     *
     * @return list of sequential segments
     */
    public ArrayList<Segment> getSegmentsList() {
        ArrayList<Segment> list = new ArrayList<>();
        list.addAll(Arrays.asList(toSegments()));
        return list;
    }

    /**
     * return index of vertex or -1 if doesn't exist
     *
     * @param point probably vertex
     * @return index
     */
    public int getVertexNumber(Point point) {
        for (int i = vertices.length - 1; i >= 0; i--) {
            if (vertices[i].equals(point)) {
                return i;
            }
        }

        return -1;
    }

    /**
     *
     * @param point vertex
     * @return list that contains two adjacent vertices or empty list if
     * {@code point} isn't vertex
     */
    public LinkedList<Point> getAdjacentVertices(Point point) {
        LinkedList<Point> adjacentVertices = new LinkedList<>();
        int index = getVertexNumber(point);
        if (index == 0) {
            adjacentVertices.add(vertices[vertices.length - 1]);
            adjacentVertices.add(vertices[1]);
            return adjacentVertices;
        }
        if (index == vertices.length - 1) {
            adjacentVertices.add(vertices[vertices.length - 2]);
            adjacentVertices.add(vertices[0]);
            return adjacentVertices;
        }

        if (index > 0 && index < vertices.length) {
            adjacentVertices.add(vertices[index - 1]);
            adjacentVertices.add(vertices[index + 1]);
            return adjacentVertices;
        }

        return adjacentVertices;
    }

}
