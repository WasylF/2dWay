package wslf.geometry;

import java.io.Serializable;

/**
 *
 * @author Wsl_F
 */
public class Constants implements Serializable {

    /**
     * epsilon value for distances
     */
    public static final double EPS = 1e-8;
    /**
     * epsilon angle in radians
     */
    public static final double EPS_ANGLE = 1e-3;
    /**
     * Prime number for hash code
     */
    public static final long BIG_PRIME = 5915587277L;
    /**
     * Prime number for hash code
     */
    public static final long LOW_PRIME = 97;
}
