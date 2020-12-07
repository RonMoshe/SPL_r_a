package java.bgu.spl.mics.application.services;


import java.bgu.spl.mics.application.messages.DeactivationEvent;
import java.bgu.spl.mics.application.passiveObjects.Diary;
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

        // send attack events to be handled by message bus
        for(Attack attack : attacks){
    	    sendEvent(new AttackEvent(attack));
        }

    	Diary diary = Diary.getInstance();
    	while(attacks.length != diary.getTotalAttacks()){
    	    try {
                wait();
            }catch (InterruptedException e){}
        }
    	sendEvent(new DeactivationEvent());
    }


}
