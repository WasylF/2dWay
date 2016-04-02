package wslf.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Wsl_F
 */
public class Polygon {

    Point[] vertexes;

    public Polygon(Point[] vertexes) {
        this.vertexes = vertexes.clone();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[" + vertexes.length + ":");

        for (int i = 0; i < vertexes.length; i++) {
            s.append(vertexes[i]);
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
        hash = 73 * hash + Arrays.deepHashCode(this.vertexes);
        return hash;
    }

    public boolean equals(Polygon p) {
        if (getSize() != p.getSize()) {
            return false;
        }
        int shift = 0;

        while (shift < vertexes.length) {
            if (vertexes[shift].equals(p.vertexes[0])) {
                break;
            }
            shift++;
        }

        if (shift == vertexes.length) {
            return false;
        }

        for (int i = 1; i < vertexes.length; i++) {
            if (!vertexes[(i + shift) % vertexes.length].equals(p.vertexes[i])) {
                return false;
            }
        }

        return true;
    }

    public int getSize() {
        return vertexes.length;
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
        for (int i = 0; i + 1 < vertexes.length; i++) {
            Vector v = new Vector(vertexes[i + 1].x - vertexes[i].x, vertexes[i + 1].y - vertexes[i].y);
            Vector vP = new Vector(p.x - vertexes[i].x, p.y - vertexes[i].y);
            switch (v.sgnMultiplyVectors(vP)) {
                case 0:
                    return true;
                case 1:
                    plus++;
            }
        }

        Vector v = new Vector(vertexes[0].x - vertexes[vertexes.length - 1].x, vertexes[0].y - vertexes[vertexes.length - 1].y);
        Vector vP = new Vector(p.x - vertexes[vertexes.length - 1].x, p.y - vertexes[vertexes.length - 1].y);
        switch (v.sgnMultiplyVectors(vP)) {
            case 0:
                return true;
            case 1:
                plus++;
        }

        return (plus == 0 || plus == vertexes.length);

    }

    /**
     *
     * @return array of polygon's vertexes
     */
    public Point[] toPoints() {
        return vertexes.clone();
    }

    /**
     * returns list of polygon's vertexes
     *
     * @param list result
     */
    public void toPointsList(List<Point> list) {
        list = Arrays.asList(vertexes);
    }

    /**
     *
     * @return list of polygon's vertexes
     */
    public ArrayList<Point> getPointsList() {
        ArrayList<Point> list = new ArrayList<>();
        list.addAll(Arrays.asList(vertexes));
        return list;
    }

    /**
     * @return array of sequential segments
     */
    public Segment[] toSegments() {
        Segment[] segments = new Segment[vertexes.length];

        segments[vertexes.length - 1] = new Segment(vertexes[vertexes.length - 1], vertexes[0]);

        for (int i = vertexes.length - 2; i >= 0; i--) {
            segments[i] = new Segment(vertexes[i], vertexes[i + 1]);
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
        ArrayList<Segment> list= new ArrayList<>();
        list.addAll(Arrays.asList(toSegments()));
        return list;
    }
}
