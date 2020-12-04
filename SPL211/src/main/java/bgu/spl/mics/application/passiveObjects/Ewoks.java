package java.bgu.spl.mics.application.passiveObjects;


import java.util.ArrayList;

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

    private ArrayList<Ewok> ewokList;

    private Ewoks(/*int amount*/) // how to initialize ewok array properly
    {
        // Collection of ewok
        ewokList = new ArrayList<Ewok>();
        /*for(int i = 0; i < amount; i++){
            ewokList.add(new Ewok(i+1));
        }*/
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

    // decide what would be most efficient way
    private boolean acquire(){
        for(int i = 0; i < ewokList.size(); i++){
            if(ewokList.get(i).getAvailable()){
                ewokList.get(i).acquire();
                return true;
            }
        }
        return false;
    }
}


