package org.example.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTranslationTest {

    @Test
    void getPart1() {
        TicketTranslation ticketTranslation=new TicketTranslation("Test1.txt");

        assertEquals(71,ticketTranslation.getPart1());
    }

    @Test
    void getPart2() {
        TicketTranslation ticketTranslation=new TicketTranslation("Test2.txt");
    }
}