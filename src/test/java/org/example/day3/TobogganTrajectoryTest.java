package org.example.day3;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TobogganTrajectoryTest {

    @org.junit.jupiter.api.Test
    void getPart1() {
        TobogganTrajectory tobogganTrajectory =new TobogganTrajectory("inputTest.txt");
        assertEquals(7,tobogganTrajectory.getPart1());
    }

    @org.junit.jupiter.api.Test
    void getPart2(){
        TobogganTrajectory tobogganTrajectory =new TobogganTrajectory("inputTest.txt");
        assertEquals(336,tobogganTrajectory.getPart2());
    }
}