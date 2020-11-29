package java.bgu.spl.mics;//package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.bgu.spl.mics.application.passiveObjects.Ewok;
import java.bgu.spl.mics.application.passiveObjects.Ewoks;

import static org.junit.jupiter.api.Assertions.*;

class EwokTest {

    Ewoks ewoks;

    @BeforeEach
    void setUp() {
        ewoks = ewoks.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void acquire() {
        Ewok ewok = new Ewok(1);
        assertEquals(ewok.getAvailable(), true);
        ewok.acquire();
        assertEquals(ewok.getAvailable(), false);
    }

    @Test
    void release() {
        Ewok ewok = new Ewok(1);
        ewok.acquire();
        assertEquals(ewok.getAvailable(), false);
        ewok.release();
        assertEquals(ewok.getAvailable(), false);
    }

    /*@Test
    void getAvailable() {
    }*/
}