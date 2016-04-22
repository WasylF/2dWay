
/**
 *
 * @author Wsl_F
 */
import parcs.*;
import wslf.algo.*;
import wslf.geometry.*;

public class Algo implements AM {

    @Override
    public void run(AMInfo info) {
        // считывание данных
        VisGraph visGraph = (VisGraph) info.parent.readObject();

        visGraph.run();

        info.parent.write(visGraph);
    }
}
