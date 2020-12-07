package java.bgu.spl.mics;

public class DeactivationCallback implements Callback {

    private MicroService microService;

    public DeactivationCallback(MicroService m){
        this.microService = m;
    }

    @Override
    public void call(Object c) {

    }
}
