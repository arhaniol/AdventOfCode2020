package org.example.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConwayCubesTest {

    @Test
    void getPart1() {
        ConwayCubes conwayCubes=new ConwayCubes("Test1.txt");
        assertEquals(11,conwayCubes.getPart1(1));
    }

    @Test
    void getPart2() {
    }
}