package java.bgu.spl.mics;

import java.bgu.spl.mics.application.messages.BombDestroyerEvent;
import java.bgu.spl.mics.application.messages.TerminationBroadcast;
import java.bgu.spl.mics.application.passiveObjects.Diary;

public class BombDestroyerCallback implements Callback {

    private MicroService microService;
    private long duration;

    public BombDestroyerCallback(MicroService m, long duration){
        this.microService = m;
        this.duration = duration;
    }

    @Override
    public void call(Object c) {
        try {
            microService.wait(duration);
        }catch (Exception x){}
        TerminationBroadcast terminationBroadcast = new TerminationBroadcast();
        microService.sendBroadcast(terminationBroadcast);
        Diary diary = Diary.getInstance();


    }
}
