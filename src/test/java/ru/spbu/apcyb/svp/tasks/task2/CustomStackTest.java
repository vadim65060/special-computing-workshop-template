package ru.spbu.apcyb.svp.tasks.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

class CustomStackTest {
    @Test
    void pushTest() {
        CustomStack<Integer> stack = new CustomStack<>();
        CustomStack<Integer> expected1 = new CustomStack<>(new Integer[]{null});
        CustomStack<Integer> expected2 = new CustomStack<>(new Integer[]{null, 2, 3});

        stack.push(null);
        Assertions.assertEquals(expected1, stack);

        stack.push(2);
        stack.push(3);
        Assertions.assertEquals(expected2, stack);
    }

    @Test
    void popTest() {
        CustomStack<Integer> stack = new CustomStack<>();
        CustomStack<Integer> expected = new CustomStack<>(new Integer[]{1});
        Assertions.assertThrows(EmptyStackException.class, stack::pop);

        stack.push(1);
        stack.push(2);
        Assertions.assertEquals(2, stack.pop());

        Assertions.assertEquals(expected, stack);
    }

    @Test
    void peekTest() {
        CustomStack<Integer> stack = new CustomStack<>();
        Assertions.assertThrows(EmptyStackException.class, stack::peek);

        stack.push(1);
        Assertions.assertEquals(1, stack.peek());
        Assertions.assertEquals(1, stack.peek());

        stack.push(2);
        Assertions.assertEquals(2, stack.peek());

    }

    @Test
    void testIsEmpty() {
        CustomStack<Integer> stack1 = new CustomStack<>();
        CustomStack<Integer> stack2 = new CustomStack<>(new Integer[]{1});
        Assertions.assertTrue(stack1.isEmpty());
        Assertions.assertFalse(stack2.isEmpty());

    }
}
