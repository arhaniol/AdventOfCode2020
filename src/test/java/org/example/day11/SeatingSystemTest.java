package org.example.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatingSystemTest {

    @Test
    void getPart1() {
        SeatingSystem seatingSystem=new SeatingSystem("inputTest.txt");
        assertEquals(37,seatingSystem.getPart1());
    }

    @Test
    void getPart2() {
        SeatingSystem seatingSystem=new SeatingSystem("inputTest.txt");
        assertEquals(26,seatingSystem.getPart2());
    }
}