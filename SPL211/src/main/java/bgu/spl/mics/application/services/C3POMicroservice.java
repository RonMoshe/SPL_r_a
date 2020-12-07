package java.bgu.spl.mics.application.services;

import java.bgu.spl.mics.*;
import java.bgu.spl.mics.application.messages.AttackEvent;
import java.bgu.spl.mics.application.messages.TerminationBroadcast;

/**
 * C3POMicroservices is in charge of the handling {@link AttackEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class C3POMicroservice extends MicroService {
	
    public C3POMicroservice() {
        super("C3PO");
    }

    @Override
    protected void initialize() {
        // subscribe to termination broadcast
        TerminationBroadcast terminationBroadcast = new TerminationBroadcast();
        TerminationCallback terminationCallback = new TerminationCallback(this);
        subscribeBroadcast(terminationBroadcast.getClass() , terminationCallback);

        // subscribe to handle attack events
        AttackEvent attackEvent = new AttackEvent();
        AttackCallback attackCallback = new AttackCallback(this);
        subscribeEvent(attackEvent.getClass(), attackCallback);
    }

    @Override
    protected  void close(){

    }

}
