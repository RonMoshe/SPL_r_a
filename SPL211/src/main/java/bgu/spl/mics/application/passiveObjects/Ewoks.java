package java.bgu.spl.mics.application.passiveObjects;


/**
 * Passive object representing the resource manager.
 * <p>
 * This class must be implemented as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private methods and fields to this class.
 */
public class Ewoks {
    // private instance, so that it can be
    // accessed by only by getInstance() method
    private static Ewoks instance;

    private Ewoks()
    {
        // private constructor
    }

    //synchronized method to control simultaneous access
    synchronized public static Ewoks getInstance()
    {
        if (instance == null)
        {
            // if instance is null, initialize
            instance = new Ewoks();
        }
        return instance;
    }
}


