package java.bgu.spl.mics;//package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.bgu.spl.mics.application.messages.AttackEvent;
import java.bgu.spl.mics.application.services.*;

import static org.junit.jupiter.api.Assertions.*;

class MessageBusImplTest {

    private MessageBusImpl messageBus;

    @BeforeEach
    void setUp() {
        messageBus = messageBus.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstance() {
        assertEquals(messageBus.getInstance(), messageBus);
    }

    @Test
    void complete() {
        // check that the result matches the type of event
        MicroService m = new HanSoloMicroservice();
        messageBus.register(m);
        ExampleEvent e = new ExampleEvent();
        ExampleCallBack c = new ExampleCallBack();
        m.subscribeEvent(e.getClass(), c);
        messageBus.sendEvent(e);
        Message message = null;
        try {
            message = messageBus.awaitMessage(m);
        }catch(Exception a){}
        assertEquals(c.getCallbackFunction(), message);
    }

    @Test
    void sendBroadcast() throws InterruptedException {
        ExampleBroadcast b = new ExampleBroadcast();
        MicroService han = new HanSoloMicroservice();
        MicroService c3po = new C3POMicroservice();
        MicroService han2 = new HanSoloMicroservice();
        messageBus.register(han);
        messageBus.register(c3po);
        messageBus.register(han2);
        messageBus.subscribeBroadcast(b.getClass(), han);
        messageBus.subscribeBroadcast(b.getClass(), c3po);
        messageBus.sendBroadcast(b);
        try {//?
            assertNotNull(messageBus.awaitMessage(han));
            assertNotNull(messageBus.awaitMessage(c3po));

            //assertEquals(b, messageBus.awaitMessage(han));// supposed to have message
            //assertEquals(b, messageBus.awaitMessage(c3po));// supposed to have message

        }catch(Exception e){}
    }

    @Test
    void sendEvent1() {
        ExampleEvent event1 = new ExampleEvent();
        ExampleEvent event2 = new ExampleEvent();
        MicroService han = new HanSoloMicroservice();
        MicroService c3po = new C3POMicroservice();
        messageBus.register(han);
        messageBus.register(c3po);
        ExampleCallBack cH = new ExampleCallBack("han");
        ExampleCallBack cC = new ExampleCallBack("c3po");
        han.subscribeEvent(event1.getClass(), cH);
        c3po.subscribeEvent(event1.getClass(), cC);
        messageBus.subscribeEvent((Class<? extends Event<Object>>) event1.getClass(), han);
        messageBus.subscribeEvent((Class<? extends Event<Object>>) event1.getClass(), c3po);
        messageBus.sendEvent(event1);
        messageBus.sendEvent(event2);
        Message a = null;
        Message b = null;
        try {
            a = messageBus.awaitMessage(han);
        }catch(Exception e){}
        assertNotNull(a);
        assertEquals(cH, a);
        try {
            b = messageBus.awaitMessage(c3po);
        }catch (Exception e){}
        assertNotNull(b);
        assertEquals(cC, b);
    }

    @Test
    void sendEvent2() {
        ExampleEvent event1 = new ExampleEvent();
        ExampleEvent event2 = new ExampleEvent();
        ExampleEvent event3 = new ExampleEvent();
        MicroService han = new HanSoloMicroservice();
        MicroService c3po = new C3POMicroservice();
        messageBus.register(han);
        messageBus.register(c3po);
        messageBus.subscribeEvent((Class<? extends Event<Object>>) event1.getClass(), han);
        messageBus.subscribeEvent((Class<? extends Event<Object>>) event1.getClass(), c3po);
        messageBus.sendEvent(event1);
        messageBus.sendEvent(event2);
        messageBus.sendEvent(event3);
        Message a = null;
        Message b = null;
        Message c = null;
        try {
            a = messageBus.awaitMessage(han);
        }catch(Exception e){}
        assertNotNull(a);
        try {
            b = messageBus.awaitMessage(c3po);
        }catch (Exception e){}
        assertNotNull(b);
        try {
            c= messageBus.awaitMessage(han);
        }catch (Exception e){}
        assertNotNull(c);
    }

    @Test
    void awaitMessage() {
        AttackEvent e = new AttackEvent();
        MicroService m = new HanSoloMicroservice();
        messageBus.register(m);
        messageBus.subscribeEvent(e.getClass(), m);
        messageBus.sendEvent(e);
        try {
            assertNotNull(messageBus.awaitMessage(m));
        }catch(Exception a){assertNotNull(null);}//?
        //assertEquals(messageBus.awaitMessage(m), e);
    }


}