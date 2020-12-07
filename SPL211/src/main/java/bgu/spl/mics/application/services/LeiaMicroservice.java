package java.bgu.spl.mics.application.services;

import javax.security.auth.callback.Callback;
import java.bgu.spl.mics.ExampleCallBack;
import java.bgu.spl.mics.Message;
import java.bgu.spl.mics.TerminationCallback;
import java.bgu.spl.mics.application.messages.DeactivationEvent;
import java.bgu.spl.mics.application.messages.TerminationBroadcast;
import java.bgu.spl.mics.application.passiveObjects.Diary;
import java.util.ArrayList;
import java.util.List;

import java.bgu.spl.mics.MicroService;
import java.bgu.spl.mics.application.passiveObjects.Attack;
import java.bgu.spl.mics.application.messages.AttackEvent;

import static java.lang.System.currentTimeMillis;

/**
 * LeiaMicroservices Initialized with Attack objects, and sends them as  {@link AttackEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class LeiaMicroservice extends MicroService {
	private Attack[] attacks;
	
    public LeiaMicroservice(Attack[] attacks) {
        super("Leia");
		this.attacks = attacks;
    }

    @Override
    protected void initialize() {
        // subscribe to termination broadcast
        TerminationBroadcast terminationBroadcast = new TerminationBroadcast();
        TerminationCallback terminationCallback = new TerminationCallback(this);
        subscribeBroadcast(terminationBroadcast.getClass() , terminationCallback);
        // send attack events to be handled by message bus
    	for(int i = 0; i < attacks.length; i++){
    	    sendEvent(new AttackEvent(attacks[i]));
        }

    	Diary diary = Diary.getInstance();
    	while(attacks.length != diary.getTotalAttacks()){
    	    try {
                wait();
            }catch (InterruptedException e){notify();}
        }
    	sendEvent(new DeactivationEvent());
    }

    @Override
    protected  void close(){

    }
}
