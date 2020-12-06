package org.example.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassportProcessingTest {

    @Test
    public void getPart1(){
        PassportProcessing passportProcessing=new PassportProcessing("inputTest.txt");
        assertEquals(2, passportProcessing.getPart1());
    }
}