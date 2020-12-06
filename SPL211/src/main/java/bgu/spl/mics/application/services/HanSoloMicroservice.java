package java.bgu.spl.mics.application.services;


import java.bgu.spl.mics.Callback;
import java.bgu.spl.mics.Event;
import java.bgu.spl.mics.ExampleCallBack;
import java.bgu.spl.mics.application.messages.AttackEvent;
import java.bgu.spl.mics.MicroService;
import java.bgu.spl.mics.application.messages.TerminationBroadcast;

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
        // subscribe to handle attack events
        //subscribeEvent(Event.AttackEvent.getClass(), new ExampleCallBack());
        TerminationBroadcast a = new TerminationBroadcast();
        ExampleCallBack c = new ExampleCallBack();
        subscribeBroadcast(a.getClass() , c);
        AttackEvent attackEvent = new AttackEvent();
        subscribeEvent(attackEvent.getClass(), c);
    }
}
