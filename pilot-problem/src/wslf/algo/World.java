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

    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();
        for (Polygon barrier : barriers) {
            points.addAll(barrier.getPointsList());
        }
        return points;
    }

    public ArrayList<Segment> getSegments() {
        ArrayList<Segment> segments = new ArrayList<>();
        for (Polygon barrier : barriers) {
            segments.addAll(barrier.getSegmentsList());
        }
        return segments;
    }
}
