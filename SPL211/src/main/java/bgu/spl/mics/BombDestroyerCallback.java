package java.bgu.spl.mics;

import java.bgu.spl.mics.application.messages.TerminationBroadcast;
import java.bgu.spl.mics.application.passiveObjects.Diary;

public class BombDestroyerCallback implements Callback {

    private MicroService microService;
    private final long duration;

    public BombDestroyerCallback(MicroService m, long duration){
        this.microService = m;
        this.duration = duration;
    }

    @Override
    public void call(Object c) {

        // stop thread for given time duration to imitate destroying the bomb
        try {
            microService.wait(duration);
        }catch (Exception x){}

        // once bomb has been destroyed all threads must be terminated. Termination broadcast signals threads to terminate
        TerminationBroadcast terminationBroadcast = new TerminationBroadcast();
        microService.sendBroadcast(terminationBroadcast);
        microService.terminate();

        // create record
        Diary diary = Diary.getInstance();
        diary.setRecording();
    }
}
