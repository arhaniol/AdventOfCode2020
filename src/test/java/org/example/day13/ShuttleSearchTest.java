package org.example.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShuttleSearchTest {

    @Test
    void getPart1() {
        ShuttleSearch shuttleSearch=new ShuttleSearch("Test1.txt");

        assertEquals(295,shuttleSearch.getPart1());
    }

    @Test
    void getPart2() {
        ShuttleSearch shuttleSearch=new ShuttleSearch("Test1.txt");

        assertEquals(1068781,shuttleSearch.getPart2());

        ShuttleSearch shuttleSearch2=new ShuttleSearch("Test2.txt");

        assertEquals(3417,shuttleSearch2.getPart2());

        ShuttleSearch shuttleSearch3=new ShuttleSearch("Test3.txt");

        assertEquals(754018,shuttleSearch3.getPart2());

        ShuttleSearch shuttleSearch4=new ShuttleSearch("Test4.txt");

        assertEquals(779210,shuttleSearch4.getPart2());

        ShuttleSearch shuttleSearch5=new ShuttleSearch("Test5.txt");

        assertEquals(1261476,shuttleSearch5.getPart2());

        ShuttleSearch shuttleSearch6=new ShuttleSearch("Test6.txt");

        assertEquals(1202161486,shuttleSearch6.getPart2());
    }
}