package wslf.algo;

import java.util.ArrayList;
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

    public World(Polygon[] barriers) {
        this.barriers = barriers;
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

}
