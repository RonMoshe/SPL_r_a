package java.bgu.spl.mics;

import java.bgu.spl.mics.application.passiveObjects.Diary;

public class TerminationCallback implements Callback {

    private MicroService microService;

    public TerminationCallback(MicroService m){
        this.microService = m;
    }

    @Override
    public void call(Object c) {
        Diary diary = Diary.getInstance();
        // timestamps
        microService.terminate();
    }
}
