package org.example.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationOrderTest {

    @Test
    void getPart1() {
        OperationOrder operationOrder =new OperationOrder("Test1.txt");

        assertEquals(71,operationOrder.getPart1());
    }

    @Test
    void getPart1Parentheses() {
        OperationOrder operationOrder =new OperationOrder("Test2.txt");

        assertEquals(51,operationOrder.getPart1());
    }

    @Test
    void getPart1Test3() {
        OperationOrder operationOrder =new OperationOrder("Test3.txt");

        assertEquals(26,operationOrder.getPart1());
    }

    @Test
    void getPart1Test4() {
        OperationOrder operationOrder =new OperationOrder("Test4.txt");

        assertEquals(437,operationOrder.getPart1());
    }

    @Test
    void getPart1Test5() {
        OperationOrder operationOrder =new OperationOrder("Test5.txt");

        assertEquals(12240,operationOrder.getPart1());
    }

    @Test
    void getPart1Test6() {
        OperationOrder operationOrder =new OperationOrder("Test6.txt");

        assertEquals(13632,operationOrder.getPart1());
    }

    @Test
    void getPart1Test7() {
        OperationOrder operationOrder =new OperationOrder("Test7.txt");

        assertEquals(26+437+12240+13632,operationOrder.getPart1());
    }

    @Test
    void getPart1Test8() {
        OperationOrder operationOrder =new OperationOrder("Test8.txt");

        assertEquals(12960,operationOrder.getPart1());
    }

    @Test
    void getPart2() {
        OperationOrder operationOrder =new OperationOrder("Test1.txt");

        assertEquals(231,operationOrder.getPart2());
    }

    @Test
    void getPart2Test9() {
        OperationOrder operationOrder =new OperationOrder("Test9.txt");

        assertEquals(51,operationOrder.getPart2());
    }

    @Test
    void getPart2Test10() {
        OperationOrder operationOrder =new OperationOrder("Test10.txt");

        assertEquals(46,operationOrder.getPart2());
    }

    @Test
    void getPart2Test11() {
        OperationOrder operationOrder =new OperationOrder("Test11.txt");

        assertEquals(1445,operationOrder.getPart2());
    }

    @Test
    void getPart2Test12() {
        OperationOrder operationOrder =new OperationOrder("Test12.txt");

        assertEquals(669060,operationOrder.getPart2());
    }
    @Test
    void getPart2Test13() {
        OperationOrder operationOrder =new OperationOrder("Test13.txt");

        assertEquals(23340,operationOrder.getPart2());
    }
}