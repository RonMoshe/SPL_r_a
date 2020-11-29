package java.bgu.spl.mics;//package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.bgu.spl.mics.Event;
import java.bgu.spl.mics.MessageBusImpl;

import java.bgu.spl.mics.application.ExampleCallBack;
import java.bgu.spl.mics.application.ExampleEvent;
import java.bgu.spl.mics.application.messages.AttackEvent;
import java.bgu.spl.mics.application.services.HanSoloMicroservice;

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
        ExampleEvent e = new ExampleEvent();
        ExampleCallBack c = new ExampleCallBack();
        m.subscribeEvent(e.getClass(), c);
        messageBus.sendEvent(e);
        Message message = messageBus.awaitMessage(m);
        assertEquals(c.getCallbackFunction(), message);
    }

    @Test
    void sendBroadcast() {

    }

    @Test
    void sendEvent() {
    }

    @Test
    void register() {
       MicroService m = new HanSoloMicroservice();
        messageBus.register(m);
        //assertTrue(messageBus.registeredMicroservice);
    }

    @Test
    void awaitMessage() throws InterruptedException {
        Event<Boolean> e = new AttackEvent();
        //Class<Event<Boolean>> type = e;
        MicroService m = new HanSoloMicroservice();
        messageBus.register(m);
        //messageBus.subscribeEvent(type, m);
        messageBus.sendEvent(e);
        assertEquals(messageBus.awaitMessage(m), e);
    }
}