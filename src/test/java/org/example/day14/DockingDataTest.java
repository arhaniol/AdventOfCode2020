package org.example.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DockingDataTest {

    @Test
    void getPart1() {
        DockingData dockingData = new DockingData("Test1.txt");

        assertEquals(165, dockingData.getPart1());
    }

    @Test
    void getPart2() {
        DockingData dockingData = new DockingData("Test2.txt");

        assertEquals(208, dockingData.getPart2());
    }
}