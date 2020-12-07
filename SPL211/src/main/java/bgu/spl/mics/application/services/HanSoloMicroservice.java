package java.bgu.spl.mics.application.services;


import java.bgu.spl.mics.*;
import java.bgu.spl.mics.application.messages.AttackEvent;
import java.bgu.spl.mics.application.messages.TerminationBroadcast;
import java.bgu.spl.mics.application.passiveObjects.Attack;

/**
 * HanSoloMicroservices is in charge of the handling {@link AttackEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class HanSoloMicroservice extends MicroService {

    enum Event {
        AttackEvent,
        DeactivationEvent,
        BombDestroyerEvent
    }

    public HanSoloMicroservice() {
        super("Han");
    }


    @Override
    protected void initialize() {
        // subscribe to termination broadcast
        TerminationBroadcast a = new TerminationBroadcast();
        TerminationCallback terminationCallback = new TerminationCallback(this);
        subscribeBroadcast(a.getClass() , terminationCallback);

        // subscribe to handle attack events
        AttackEvent attackEvent = new AttackEvent();
        AttackCallback attackCallback = new AttackCallback(this);
        subscribeEvent(attackEvent.getClass(), attackCallback);


    }
}
