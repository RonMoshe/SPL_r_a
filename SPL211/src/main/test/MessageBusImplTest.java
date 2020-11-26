//package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.bgu.spl.mics.Event;
import java.bgu.spl.mics.MessageBusImpl;
import java.bgu.spl.mics.MicroService;
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
    void subscribeEvent() {

    }

    @Test
    void subscribeBroadcast() {
    }

    @Test
    void complete() {
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