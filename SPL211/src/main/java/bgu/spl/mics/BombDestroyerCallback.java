package java.bgu.spl.mics;

import java.bgu.spl.mics.application.messages.BombDestroyerEvent;

public class BombDestroyerCallback implements Callback {

    private MicroService microService;

    public BombDestroyerCallback(MicroService m){
        this.microService = m;
    }

    @Override
    public void call(Object c) {

    }
}
