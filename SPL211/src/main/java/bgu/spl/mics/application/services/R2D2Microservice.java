package java.bgu.spl.mics.application.services;

import java.bgu.spl.mics.DeactivationCallback;
import java.bgu.spl.mics.MicroService;
import  java.bgu.spl.mics.application.messages.DeactivationEvent;

/**
 * R2D2Microservices is in charge of the handling {@link DeactivationEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link DeactivationEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class R2D2Microservice extends MicroService {

    private long duration;

    public R2D2Microservice(long duration) {
        super("R2D2");
        this.duration = duration;
    }


    @Override
    protected void initialize() {

        // subscribe to handle deactivation events
        DeactivationEvent deactivationEvent = new DeactivationEvent();
        DeactivationCallback deactivationCallback = new DeactivationCallback(this, duration);
        subscribeEvent(deactivationEvent.getClass(), deactivationCallback);

    }


}
