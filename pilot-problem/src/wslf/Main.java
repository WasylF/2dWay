package wslf;

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
        long startTime = System.currentTimeMillis();
        // TODO code application logic here
        World world = World.generateWorld(500);
        World.printWorld("W.txt", world);
    //    World world2 = World.readWorld("W.txt");

        Visibility visibility = new Visibility(world);
        visibility.buildVisibilityGraph();
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Successfully finished in: "+estimatedTime+" milliseconds");
    }

}
