package java.bgu.spl.mics.application.passiveObjects;


import java.bgu.spl.mics.MicroService;
import java.bgu.spl.mics.application.services.C3POMicroservice;
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
        // find a better way to do this
        if(m instanceof HanSoloMicroservice || m instanceof C3POMicroservice)
            totalAttacks++;
    }

    public void setC3POFinish(long c3POFinish) {
        C3POFinish = c3POFinish;
    }

    public int getTotalAttacks() {
        return totalAttacks;
    }

    public void setHanSoloFinish(long hanSoloFinish) {
        HanSoloFinish = hanSoloFinish;
    }

    public void setC3POTerminate(long c3POTerminate) {
        C3POTerminate = c3POTerminate;
    }

    public void setHanSoloTerminate(long hanSoloTerminate) {
        HanSoloTerminate = hanSoloTerminate;
    }

    public static void setInstance(Diary instance) {
        Diary.instance = instance;
    }

    public void setLandoTerminate(long landoTerminate) {
        LandoTerminate = landoTerminate;
    }

    public void setLeiaTerminate(long leiaTerminate) {
        LeiaTerminate = leiaTerminate;
    }

    public void setR2D2Deactivate(long r2D2Deactivate) {
        R2D2Deactivate = r2D2Deactivate;
    }

    public void setR2D2Terminate(long r2D2Terminate) {
        R2D2Terminate = r2D2Terminate;
    }


}
