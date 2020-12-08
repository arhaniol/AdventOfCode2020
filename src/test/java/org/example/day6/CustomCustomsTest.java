package org.example.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomCustomsTest {
    @Test
    public void getPart1() {
        CustomCustoms customCustoms = new CustomCustoms("inputTest.txt");
        assertEquals(11, customCustoms.getPart1());
    }

    @Test
    public void getPart2() {
        CustomCustoms customCustoms = new CustomCustoms("inputTest.txt");
        assertEquals(6, customCustoms.getPart2());
    }
}