package org.example.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterMessagesTest {

    @Test
    void buildTest() {
        MonsterMessages monsterMessages = new MonsterMessages("Test.txt");
        assertEquals(2, monsterMessages.build());
    }

    @Test
    void getPart1() {
        MonsterMessages monsterMessages = new MonsterMessages("Test1.txt");

        assertEquals(2, monsterMessages.getPart1());
    }

    @Test
    void getPart2() {
    }
}