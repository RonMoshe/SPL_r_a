package java.bgu.spl.mics;

import java.bgu.spl.mics.Broadcast;

public class ExampleBroadcast implements Broadcast {
    private String broadcastMessage;

    public ExampleBroadcast(){broadcastMessage = "Example Message";}

    public String getBroadcastMessage(){return broadcastMessage;}
}
