package java.bgu.spl.mics.application.services;

import java.bgu.spl.mics.DeactivationCallback;
import java.bgu.spl.mics.ExampleCallBack;
import java.bgu.spl.mics.MicroService;
import java.bgu.spl.mics.TerminationCallback;
import  java.bgu.spl.mics.application.messages.DeactivationEvent;
import java.bgu.spl.mics.application.messages.TerminationBroadcast;

/**
 * R2D2Microservices is in charge of the handling {@link DeactivationEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link DeactivationEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class R2D2Microservice extends MicroService {

    public R2D2Microservice(long duration) {
        super("R2D2");
    }


    @Override
    protected void initialize() {

        // subscribe to termination broadcast
        TerminationBroadcast terminationBroadcast = new TerminationBroadcast();
        TerminationCallback terminationCallback = new TerminationCallback(this);
        subscribeBroadcast(terminationBroadcast.getClass() , terminationCallback);

        // subscribe to handle deactivation events
        DeactivationEvent deactivationEvent = new DeactivationEvent();
        DeactivationCallback deactivationCallback = new DeactivationCallback(this);
        subscribeEvent(deactivationEvent.getClass(), deactivationCallback);
    }
}
