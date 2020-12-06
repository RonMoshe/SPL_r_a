package java.bgu.spl.mics.application.services;

import javax.security.auth.callback.Callback;
import java.bgu.spl.mics.ExampleCallBack;
import java.bgu.spl.mics.Message;
import java.bgu.spl.mics.application.messages.TerminationBroadcast;
import java.util.ArrayList;
import java.util.List;

import java.bgu.spl.mics.MicroService;
import java.bgu.spl.mics.application.passiveObjects.Attack;
import java.bgu.spl.mics.application.messages.AttackEvent;

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
        TerminationBroadcast a = new TerminationBroadcast();
        ExampleCallBack c = new ExampleCallBack();
        subscribeBroadcast(a.getClass() , c);
    	for(int i = 0; i < attacks.length; i++){
    	    sendEvent(new AttackEvent(attacks[i]));
        }
    }
}
