package org.example.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryBoardingTest {
    @Test
    public void getPart1(){
        BinaryBoarding binaryBoarding=new BinaryBoarding("inputTest.txt");
        assertEquals(820, binaryBoarding.getPart1());
    }


}