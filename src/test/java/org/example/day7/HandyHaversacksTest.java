package org.example.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandyHaversacksTest {

    @Test
    public void getPart1Test(){
        HandyHaversacks handyHaversacks=new HandyHaversacks("inputTest.txt");
        assertEquals(4,handyHaversacks.getPart1());
    }

}