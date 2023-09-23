package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для задания 1.
 */
class Task1Test {
    @Test
    void getPositiveIntTest() {

        System.setIn(new ByteArrayInputStream("15".getBytes()));
        assertEquals(15, Task1.getPositiveInt());

        System.setIn(new ByteArrayInputStream("-1".getBytes()));
        assertThrows(RuntimeException.class, Task1::getPositiveInt);

        System.setIn(new ByteArrayInputStream("a".getBytes()));
        assertThrows(RuntimeException.class, Task1::getPositiveInt);

        System.setIn(System.in);
    }

    @Test
    void getIntArrayTest() {

        System.setIn(new ByteArrayInputStream("1 2 3".getBytes()));
        int[] expected = {1, 2, 3};
        int[] actual = Task1.getIntArray();
        assertArrayEquals(expected, actual);

        System.setIn(new ByteArrayInputStream("1 a 3".getBytes()));
        boolean thrown = false;
        try {
            Task1.getIntArray();
            System.setIn(System.in);
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);

        System.setIn(System.in);
    }

    @Test
    void cashMachineTest() {
        int sum = 12;
        int[] nominals = {2, 3, 4};
        CashMachine cashMachine = new CashMachine(nominals);

        int exp = 7;
        int act = cashMachine.getCombinations(sum).size();

        assertEquals(exp, act);
    }
}
