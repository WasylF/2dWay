package wslf.visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

import wslf.algo.World;
//import wslf.geometry.*;

/**
 *
 * @author Wsl_F
 */
public class WorldPanel extends JPanel {

    private Polygon[] barriers;
    private ArrayList<wslf.geometry.Point> way;
    private int panelX;
    private int panelY;
    private double coef;
    double minX = Double.POSITIVE_INFINITY;
    double minY = Double.POSITIVE_INFINITY;
    double maxX = Double.NEGATIVE_INFINITY;
    double maxY = Double.NEGATIVE_INFINITY;

    public WorldPanel() {
        super();
    }

    private World world;

    public void setWorld(World world) {
        this.world = world;

        Dimension panelSize = getSize();
        panelX = panelSize.width;
        panelY = panelSize.height;

        way = new ArrayList<>();
        updateMinMaxXY();

        barriers = getBarriers();
    }

    public void setWay(List<wslf.geometry.Point> way) {
        this.way = new ArrayList<>(way);
        updateMinMaxXY();
        barriers = getBarriers();
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("paintComponent");
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g2d);

        for (Polygon barrier : barriers) {
            g2d.setColor(Color.GREEN);
            g2d.fillPolygon(barrier);
            for (int i = barrier.npoints - 1; i >= 0; i--) {
                int x = barrier.xpoints[i];
                int y = barrier.ypoints[i];
                g2d.fill(getPoint(x, y, 3, 3));
            }
        }
        int n = way.size();
        for (int i = 1; i < n; i++) {
            g2d.setColor(Color.BLACK);
            int x1 = getCoordinateX(way.get(i - 1).getX());
            int x2 = getCoordinateX(way.get(i).getX());
            int y1 = getCoordinateY(way.get(i - 1).getY());
            int y2 = getCoordinateY(way.get(i).getY());
            g2d.drawLine(x1, y1, x2, y2);
            g2d.fill(getPoint(x1, y1, 3, 3));
        }

        if (n >= 2) {
            wslf.geometry.Point start = way.get(0);
            wslf.geometry.Point finish = way.get(n - 1);

            g2d.fill(getPoint(getCoordinateX(start.getX()), getCoordinateY(start.getY()), 7, 7));
            g2d.fill(getPoint(getCoordinateX(finish.getX()), getCoordinateY(finish.getY()), 7, 7));
        }
    }

    private Ellipse2D getPoint(double x, double y, double width, double height) {
        double newX = x - width / 2.0;
        double newY = y - height / 2.0;

        Ellipse2D ellipse = new Ellipse2D.Double(newX, newY, width, height);

        return ellipse;
    }
    private static final int margin = 10;

    private void updateMinMaxXY() {
        minX = Double.POSITIVE_INFINITY;
        minY = Double.POSITIVE_INFINITY;
        maxX = Double.NEGATIVE_INFINITY;
        maxY = Double.NEGATIVE_INFINITY;

        wslf.geometry.Polygon[] polygons = world.getBarriers();

        for (wslf.geometry.Polygon barrier : polygons) {
            for (wslf.geometry.Point vertex : barrier.toPoints()) {
                updateMinMax(vertex);
            }
        }

        for (wslf.geometry.Point point : way) {
            updateMinMax(point);
        }

        coef = getCoef(maxX, minX, maxY, minY);
    }

    private void updateMinMax(wslf.geometry.Point point) {
        double x = point.getX();
        double y = point.getY();

        if (x < minX) {
            minX = x;
        }
        if (x > maxX) {
            maxX = x;
        }

        if (y < minY) {
            minY = y;
        }
        if (y > maxY) {
            maxY = y;
        }
    }

    private Polygon[] getBarriers() {
        wslf.geometry.Polygon[] barriers = world.getBarriers();

        Polygon[] drawPolygon = new Polygon[barriers.length];

        for (int i = 0; i < barriers.length; i++) {
            wslf.geometry.Polygon barrier = barriers[i];
            drawPolygon[i] = new Polygon();
            for (wslf.geometry.Point vertex : barrier.toPoints()) {
                int x = getCoordinateX(vertex.getX());
                int y = getCoordinateY(vertex.getY());
                drawPolygon[i].addPoint(x, y);
            }
        }

        return drawPolygon;
    }

    private int getCoordinateX(double x) {
        return (int) ((x - minX) * coef) + margin;
    }

    private int getCoordinateY(double y) {
        return panelY - ((int) ((y - minY) * coef) + margin);
    }

    private double getCoef(double maxX, double minX, double maxY, double minY) {
        double lenX = maxX - minX;
        double lenY = maxY - minY;

        double coefX = (panelX - 2 * margin) / lenX;
        double coefY = (panelY - 2 * margin) / lenY;

        return coefX < coefY ? coefX : coefY;
    }
}
