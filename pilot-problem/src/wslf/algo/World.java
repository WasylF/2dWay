package wslf.algo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
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

}
