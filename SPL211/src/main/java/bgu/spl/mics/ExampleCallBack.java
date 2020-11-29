package java.bgu.spl.mics;

public class ExampleCallBack implements Callback{

    private String callbackFunction;

    public ExampleCallBack(){callbackFunction = "Example";}

    public ExampleCallBack(String call){callbackFunction = call;}

    public String getCallbackFunction(){return callbackFunction;}

    public void call(Object c) {

    }
}
