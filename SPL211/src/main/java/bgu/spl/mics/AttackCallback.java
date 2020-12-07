package java.bgu.spl.mics;

import java.bgu.spl.mics.application.messages.AttackEvent;
import java.bgu.spl.mics.application.passiveObjects.Diary;
import java.bgu.spl.mics.application.passiveObjects.Ewoks;
import java.util.List;

public class AttackCallback implements Callback {

    private MicroService microService;

    public AttackCallback(MicroService m){
        this.microService = m;
    }

    @Override
    public void call(Object c) {
        Diary diary = Diary.getInstance();
        AttackEvent e = (AttackEvent)c;
        Ewoks ewoks = Ewoks.getInstance(3); // change this!!!
        List<Integer> resourceList = e.getSerials();
        for(int i = 0; i < resourceList.size(); i++){
            ewoks.acquire(resourceList.get(i));
        }
        try {
            microService.wait(e.getDuration());
        }catch (Exception x){}
        // do you release resources at the end?
        try {
            microService.wait(((AttackEvent) c).getDuration());
        }catch(Exception x){}
        diary.addAttack(microService);
    }
}
