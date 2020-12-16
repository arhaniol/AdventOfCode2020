package org.example.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdapterArrayTest {

    @Test
    void getPart1Small() {
        AdapterArray adapterArray = new AdapterArray("inputTestSmall.txt");
        assertEquals(35, adapterArray.getPart1());
    }

    @Test
    void getPart2Small() {
        AdapterArray adapterArray = new AdapterArray("inputTestSmall.txt");
        assertEquals(5, adapterArray.getPart2());
    }

    @Test
    void getPart1Big() {
        AdapterArray adapterArray = new AdapterArray("inputTestBig.txt");
        assertEquals(220, adapterArray.getPart1());
    }

    @Test
    void getPart2Big() {
        AdapterArray adapterArray = new AdapterArray("inputTestBig.txt");
        assertEquals(10, adapterArray.getPart2());
    }
}