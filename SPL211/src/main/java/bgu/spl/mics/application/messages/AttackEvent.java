package java.bgu.spl.mics.application.messages;
import java.bgu.spl.mics.Event;
import java.bgu.spl.mics.Future;
import java.bgu.spl.mics.application.passiveObjects.Attack;
import java.util.List;

public class AttackEvent implements Event<Future> {

    private Attack attack;

    public AttackEvent(Attack attack){
        this.attack = attack;
    }

    public List<Integer> getSerials(){
        return attack.getSerials();
    }

    public int getDuration(){
        return attack.getDuration();
    }
}
