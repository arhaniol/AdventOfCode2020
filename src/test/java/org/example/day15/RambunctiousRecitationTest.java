package org.example.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RambunctiousRecitationTest {

    @Test
    void getPart1() {
        RambunctiousRecitation rambunctiousRecitation = new RambunctiousRecitation("Test1.txt");

        assertEquals(3,rambunctiousRecitation.getPart1(5));
        assertEquals(3,rambunctiousRecitation.getPart1(6));
        assertEquals(0,rambunctiousRecitation.getPart1(8));
        assertEquals(0,rambunctiousRecitation.getPart1(10));
        assertEquals(436,rambunctiousRecitation.getPart1(2020));
    }

    @Test
    void getPart2() {
        RambunctiousRecitation rambunctiousRecitation = new RambunctiousRecitation("Test1.txt");

        assertEquals(3,rambunctiousRecitation.getPart2(5));
        assertEquals(3,rambunctiousRecitation.getPart2(6));
        assertEquals(0,rambunctiousRecitation.getPart2(8));
        assertEquals(0,rambunctiousRecitation.getPart2(10));
        assertEquals(436,rambunctiousRecitation.getPart2(2020));
    }
}