package java.bgu.spl.mics;

import java.bgu.spl.mics.application.messages.AttackEvent;
import java.bgu.spl.mics.application.passiveObjects.Diary;
import java.bgu.spl.mics.application.passiveObjects.Ewoks;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class AttackCallback implements Callback {

    private MicroService microService;

    public AttackCallback(MicroService m){
        this.microService = m;
    }

    @Override
    public void call(Object c) {
        // initializing objects necessary for method
        Diary diary = Diary.getInstance();
        AttackEvent e = (AttackEvent) c;
        Ewoks ewoks = Ewoks.getInstance();
        List<Integer> resourceList = e.getSerials();

        // acquiring necessary ewok objects for attack
        for(Integer ewokSerialNumber : resourceList){
            while(!ewoks.acquire(ewokSerialNumber)){
                try {
                    wait();
                }catch(InterruptedException x){}
            }
        }

        // once all required resources are gathered thread will wait for attack duration in order to simulate the attack
        try {
            microService.wait(e.getDuration());
        }catch (InterruptedException x){}
        // do you release resources at the end?

        // attack has ended - resources-ewok objects are released
        for(Integer ewokSerialNumber : resourceList){
            ewoks.release(ewokSerialNumber);
        }

        // attack is added in diary
        diary.addAttack(microService);

        // last attack of microservice is updated in diary
        diary.setAttackerFinish(microService, currentTimeMillis());
    }
}
