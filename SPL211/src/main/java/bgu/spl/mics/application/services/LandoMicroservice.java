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

    public LandoMicroservice(long duration) {
        super("Lando");
    }

    @Override
    protected void initialize() {
        // subscribe to termination broadcast
        TerminationBroadcast terminationBroadcast = new TerminationBroadcast();
        TerminationCallback terminationCallback = new TerminationCallback(this);
        subscribeBroadcast(terminationBroadcast.getClass() , terminationCallback);

        // subscribe to handle bomb destroyer events
        BombDestroyerEvent bombDestroyerEvent = new BombDestroyerEvent();
        BombDestroyerCallback bombDestroyerCallback = new BombDestroyerCallback(this);
        subscribeEvent(bombDestroyerEvent.getClass(), bombDestroyerCallback);
    }
}
