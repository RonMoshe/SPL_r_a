package java.bgu.spl.mics.application.passiveObjects;


import java.bgu.spl.mics.MicroService;
import java.bgu.spl.mics.application.services.HanSoloMicroservice;

/**
 * Passive data-object representing a Diary - in which the flow of the battle is recorded.
 * We are going to compare your recordings with the expected recordings, and make sure that your output makes sense.
 * <p>
 * Do not add to this class nothing but a single constructor, getters and setters.
 */
public class Diary {

    private static Diary instance;
    private String recording;
    private int totalAttacks;
    private long HanSoloFinish;
    private long C3POFinish;
    private long R2D2Deactivate;
    private long LeiaTerminate;
    private long HanSoloTerminate;
    private long C3POTerminate;
    private long R2D2Terminate;
    private long LandoTerminate; // use System.currentTimeMillis();



    private Diary()
    {

        recording = "";
        instance = this.getInstance();
        totalAttacks = 0;
        //HanSoloFinish = ;

    }

    //synchronized method to control simultaneous access
    synchronized public static Diary getInstance()
    {
        if (instance == null)
        {
            // if instance is null, initialize
            instance = new Diary();
        }
        return instance;
    }

    public String getRecording(){return recording;}

    public void setRecording(String recording){this.recording = this.recording + "\n" + recording;}

    public void addAttack(MicroService m){
        // check that microservice can add attack
        //if(m.getClass() )
        totalAttacks++;
    }

}
