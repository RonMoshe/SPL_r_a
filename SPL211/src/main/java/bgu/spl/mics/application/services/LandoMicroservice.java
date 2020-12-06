package java.bgu.spl.mics.application.services;

import java.bgu.spl.mics.ExampleCallBack;
import java.bgu.spl.mics.MicroService;
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
        TerminationBroadcast a = new TerminationBroadcast();
        ExampleCallBack c = new ExampleCallBack();
        subscribeBroadcast(a.getClass() , c);
        BombDestroyerEvent b = new BombDestroyerEvent();
        subscribeEvent(b.getClass(), c);
    }
}
