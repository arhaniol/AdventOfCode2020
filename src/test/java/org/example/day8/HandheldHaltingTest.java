package org.example.day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandheldHaltingTest {

    @Test
    void getPart1() {
        HandheldHalting handheldHalting = new HandheldHalting("inputTest.txt");
        assertEquals(5, handheldHalting.getPart1());
    }

    @Test
    void getPart2() {
        HandheldHalting handheldHalting=new HandheldHalting("inputTest.txt");
        assertEquals(8,handheldHalting.getPart2());
    }
}