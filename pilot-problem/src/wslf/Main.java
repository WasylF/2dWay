package wslf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import wslf.algo.Visibility;
import wslf.algo.World;

/**
 *
 * @author Wsl_F
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //generate(500);
        long startTime = System.currentTimeMillis();
        World world = World.readWorld("W_200.txt");

        Visibility visibility = new Visibility(world);
        ArrayList<ArrayList<Integer>> graph
                = visibility.buildVisibilityGraph();
        print("output.txt", graph);
        double estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Successfully finished in: " + estimatedTime / 1000 + " seconds");
    }

    public static void generate(int n) {
        World world_ = World.generateWorld(n);
        World.printWorld("W_" + n + ".txt", world_);

    }

    public static void print(String fileName, ArrayList<ArrayList<Integer>> visGraph) {
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
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
