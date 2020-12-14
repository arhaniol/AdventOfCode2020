package org.example.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncodingErrorTest {

    @Test
    void getPart1() {
        EncodingError encodingError=new EncodingError("inputTest.txt");
        assertEquals(127,encodingError.getPart1(5));
    }

    @Test
    void getPart2() {
    }
}