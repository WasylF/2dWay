package wslf.geometry;

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
        StringBuilder s = new StringBuilder("[ " + vertexes.length + ": ");

        for (int i = 0; i < vertexes.length; i++) {
            s.append(vertexes[i] + "; ");
        }

        s.append(" ]");

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

    public boolean equals(Polygon p) {
        if (getSize() != p.getSize()) {
            return false;
        }
        int shift = 0;

        while (shift < vertexes.length) {
            if (vertexes[shift] == p.vertexes[0]) {
                break;
            }
        }

        if (shift == vertexes.length) {
            return false;
        }

        for (int i = 1; i < vertexes.length; i++) {
            if (vertexes[(i + shift) % vertexes.length] != p.vertexes[i]) {
                return false;
            }
        }

        return true;
    }

    public int getSize() {
        return vertexes.length;
    }
}
