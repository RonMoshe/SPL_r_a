package java.bgu.spl.mics.application.services;

import java.bgu.spl.mics.BombDestroyerCallback;
import java.bgu.spl.mics.ExampleCallBack;
import java.bgu.spl.mics.MicroService;
import java.bgu.spl.mics.TerminationCallback;
import java.bgu.spl.mics.application.messages.BombDestroyerEvent;
import java.bgu.spl.mics.application.messages.TerminationBroadcast;

/**
 * LandoMicroservice
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class LandoMicroservice  extends MicroService {

    private long duration;

    public LandoMicroservice(long duration) {
        super("Lando");
        this.duration = duration;
    }

    @Override
    protected void initialize() {


        // subscribe to handle bomb destroyer events
        BombDestroyerEvent bombDestroyerEvent = new BombDestroyerEvent();
        BombDestroyerCallback bombDestroyerCallback = new BombDestroyerCallback(this, duration);
        subscribeEvent(bombDestroyerEvent.getClass(), bombDestroyerCallback);

    }

    public long getDuration() {
        return duration;
    }


}
