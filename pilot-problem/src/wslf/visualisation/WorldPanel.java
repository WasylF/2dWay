package wslf.visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JPanel;

import wslf.algo.World;
//import wslf.geometry.*;

/**
 *
 * @author Wsl_F
 */
public class WorldPanel extends JPanel {

    private Polygon[] barriers;
    private int panelX;
    private int panelY;

    public WorldPanel() {
        super();
    }

    private World world;

    public void setWorld(World world) {
        this.world = world;

        Dimension panelSize = getSize();
        panelX = panelSize.width;
        panelY = panelSize.height;

        barriers = getBarriers();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g2d);

        for (Polygon barrier : barriers) {
            g2d.setColor(Color.GREEN);
            g2d.fillPolygon(barrier);
        }
    }

    private static final int margin = 10;

    private Polygon[] getBarriers() {
        wslf.geometry.Polygon[] barriers = world.getBarriers();
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;

        for (wslf.geometry.Polygon barrier : barriers) {
            for (wslf.geometry.Point vertex : barrier.toPoints()) {
                double x = vertex.getX();
                double y = vertex.getY();

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
        }

        double coef = getCoef(maxX, minX, maxY, minY);

        Polygon[] drawPolygon = new Polygon[barriers.length];

        for (int i = 0; i < barriers.length; i++) {
            wslf.geometry.Polygon barrier = barriers[i];
            drawPolygon[i] = new Polygon();
            for (wslf.geometry.Point vertex : barrier.toPoints()) {
                int x = getCoordinate(vertex.getX() - minX, coef);
                int y = panelY - getCoordinate(vertex.getY() - minY, coef);
                drawPolygon[i].addPoint(x, y);
            }
        }

        return drawPolygon;
    }

    private int getCoordinate(double d, double coef) {
        return (int) (d * coef) + margin;
    }

    private double getCoef(double maxX, double minX, double maxY, double minY) {
        double lenX = maxX - minX;
        double lenY = maxY - minY;

        double coefX = (panelX - 2 * margin) / lenX;
        double coefY = (panelY - 2 * margin) / lenY;

        return coefX < coefY ? coefX : coefY;
    }
}
