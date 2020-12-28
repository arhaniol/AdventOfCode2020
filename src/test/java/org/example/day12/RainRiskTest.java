package org.example.day12;

import com.sun.imageio.spi.RAFImageInputStreamSpi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RainRiskTest {

    @Test
    void getPart1() {
        RainRisk risk = new RainRisk("inputTest.txt");
        assertEquals(25, risk.getPart1());
    }

    @Test
    void getPart2() {
        RainRisk risk = new RainRisk("inputTest.txt");
        assertEquals(286, risk.getPart2());
    }
}