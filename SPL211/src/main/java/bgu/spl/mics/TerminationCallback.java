package java.bgu.spl.mics;

import java.bgu.spl.mics.application.passiveObjects.Diary;

import static java.lang.System.currentTimeMillis;

public class TerminationCallback implements Callback {

    private MicroService microService;

    public TerminationCallback(MicroService m){
        this.microService = m;
    }


    @Override
    public void call(Object c) {
        Diary diary = Diary.getInstance();
        // timestamps
        switch(microService.getName()) {
            case "C3PO":
                diary.setC3POTerminate(currentTimeMillis());
            case "Han":
                diary.setHanSoloTerminate(currentTimeMillis());
            case "Lando":
                diary.setLandoTerminate(currentTimeMillis());
            case "Leia":
                diary.setLeiaTerminate(currentTimeMillis());
            case  "R2D2":
                diary.setR2D2Terminate(currentTimeMillis());
        }
        microService.terminate();
    }
}
