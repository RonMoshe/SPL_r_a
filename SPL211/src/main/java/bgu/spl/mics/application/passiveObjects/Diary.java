package java.bgu.spl.mics.application.passiveObjects;


/**
 * Passive data-object representing a Diary - in which the flow of the battle is recorded.
 * We are going to compare your recordings with the expected recordings, and make sure that your output makes sense.
 * <p>
 * Do not add to this class nothing but a single constructor, getters and setters.
 */
public class Diary {

    private static Diary instance;
    String recording;

    private Diary()
    {

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

    public void setRecording(String recording){this.recording = this.recording + "\n" + recording;}

}
