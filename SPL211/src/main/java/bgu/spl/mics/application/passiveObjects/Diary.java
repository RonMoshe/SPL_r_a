package java.bgu.spl.mics.application.passiveObjects;


import java.bgu.spl.mics.MicroService;

/**
 * Passive data-object representing a Diary - in which the flow of the battle is recorded.
 * We are going to compare your recordings with the expected recordings, and make sure that your output makes sense.
 * <p>
 * Do not add to this class nothing but a single constructor, getters and setters.
 */
public class Diary {

    private static Diary instance;
    // recording of program logs
    private String recording;

    // number of total attacks
    private int totalAttacks;

    // finish operation of specific microservices
    private long HanSoloFinish;
    private long C3POFinish;
    private long R2D2Deactivate;

    // termination
    private long LeiaTerminate;
    private long HanSoloTerminate;
    private long C3POTerminate;
    private long R2D2Terminate;
    private long LandoTerminate;

    private Diary()
    {
        //instance = this.getInstance();
        totalAttacks = 0;
        recording = "";
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

    public void setRecording(){
        long attackEndDifference = (HanSoloFinish-C3POFinish);
        long attackTerminateDifference = (LandoTerminate - Math.max(HanSoloFinish, C3POFinish));
        this.recording = "There are " + totalAttacks + " attacks. \n" + "HanSolo and C3PO finish their tasks " + attackEndDifference
                + " milliseconds one after the other. \n" + "All threads terminate " + attackTerminateDifference + " milliseconds later.";
    }

    public void addAttack(MicroService m){
        // check that microservice can add attack
        // find a better way to do this
        if(m.getName().equals("Han") || m.getName().equals("C3PO"))
            totalAttacks++;
    }

    public int getTotalAttacks() { return totalAttacks; }

    // Attack Callback uses this method to update the end time of the last attack for one of the attacker types(Han/C3PO)
    public void setAttackerFinish(MicroService m, long finishTime){
        if(m.getName().equals("Han"))
            setHanSoloFinish(finishTime);
        if(m.getName().equals("C3PO"))
            setC3POFinish(finishTime);
    }

    // set finish time functions for different microservices
    public void setC3POFinish(long c3POFinish) {
        C3POFinish = c3POFinish;
    }

    public void setHanSoloFinish(long hanSoloFinish) {
        HanSoloFinish = hanSoloFinish;
    }

    // set deactivation time for R2D2
    public void setR2D2Deactivate(long r2D2Deactivate) {
        R2D2Deactivate = r2D2Deactivate;
    }

    // set termination time for different microservices - termination signaled by the same broadcast for all microservices
    public void setC3POTerminate(long c3POTerminate) {
        C3POTerminate = c3POTerminate;
    }

    public void setHanSoloTerminate(long hanSoloTerminate) {
        HanSoloTerminate = hanSoloTerminate;
    }

    public void setLandoTerminate(long landoTerminate) {
        LandoTerminate = landoTerminate;
    }

    public void setLeiaTerminate(long leiaTerminate) {
        LeiaTerminate = leiaTerminate;
    }

    public void setR2D2Terminate(long r2D2Terminate) {
        R2D2Terminate = r2D2Terminate;
    }






}
