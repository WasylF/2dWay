package wslf.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Wsl_F
 */
public class Polygon {

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
        int plus;
        plus = 0;
        for (int i = 0; i + 1 < vertices.length; i++) {
            Vector v = new Vector(vertices[i + 1].x - vertices[i].x, vertices[i + 1].y - vertices[i].y);
            Vector vP = new Vector(p.x - vertices[i].x, p.y - vertices[i].y);
            switch (v.sgnMultiplyVectors(vP)) {
                case 0:
                    return true;
                case 1:
                    plus++;
            }
        }

        Vector v = new Vector(vertices[0].x - vertices[vertices.length - 1].x, vertices[0].y - vertices[vertices.length - 1].y);
        Vector vP = new Vector(p.x - vertices[vertices.length - 1].x, p.y - vertices[vertices.length - 1].y);
        switch (v.sgnMultiplyVectors(vP)) {
            case 0:
                return true;
            case 1:
                plus++;
        }

        return (plus == 0 || plus == vertices.length);

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
}
