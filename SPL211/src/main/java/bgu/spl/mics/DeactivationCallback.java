package java.bgu.spl.mics;

import java.bgu.spl.mics.application.messages.BombDestroyerEvent;
import java.bgu.spl.mics.application.passiveObjects.Diary;

import static java.lang.System.currentTimeMillis;

public class DeactivationCallback implements Callback {

    private MicroService microService;
    private long duration;

    public DeactivationCallback(MicroService m, long duration){
        this.microService = m;
        this.duration = duration;
    }

    @Override
    public void call(Object c) {

        // stop thread to imitate deactivation of shield
        try {
            microService.wait(duration);
        }catch (InterruptedException x){}

        // R2D2 has completed the deactivation. updata deactivation time in diary
        Diary diary = Diary.getInstance();
        diary.setR2D2Deactivate(currentTimeMillis());

        // creating and sending the bomb destroyer event to Lando
        BombDestroyerEvent bombDestroyerEvent = new BombDestroyerEvent();
        microService.sendEvent(bombDestroyerEvent);
    }
}
