package wslf.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wsl_F
 */
public class VisGraph implements Serializable {

    public LinkedList<Integer> verticiesNumb;
    /**
     * graph of visibility. visGraph[i] - list of numbers of points those
     * visible from vertex №i. Coordinates of vertex №i is vertices[i]
     */
    public ArrayList<ArrayList<Integer>> visGraph;
    /**
     * world with barriers
     */
    public World world;

    public VisGraph subTask(List<Integer> vNumb) {
        verticiesNumb = (LinkedList<Integer>) vNumb;
        return this;
    }

    public void run() {
        Visibility visibility = new Visibility(world);
        visGraph = visibility.buildVisibilityGraph(verticiesNumb);
    }

    public VisGraph(String fileName) {
        this.world = World.readWorld(fileName);
        this.verticiesNumb = null;
        this.visGraph = null;
    }

    public int getSize() {
        return world.getPoints().size();
    }

    public void add(VisGraph v) {
        for (int i = v.visGraph.size() - 1; i >= 0; i--) {
            visGraph.get(i).addAll(v.visGraph.get(i));
        }
    }

    public void save(String fileName) {
        try {
            PrintWriter pw = new PrintWriter(new File(fileName));
            int size = visGraph.size();
            pw.println(size);
            for (int i = 0; i < size; i++) {
                pw.println(visGraph.get(i).size());
                for (int v : visGraph.get(i)) {
                    pw.print(v + " ");
                }
                pw.println();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VisGraph.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
