package ru.spbu.apcyb.svp.tasks.task2;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class CustomStack<E> extends Stack<E> {
    private static final String NOT_IMPLEMENTED = "Method not implemented";
    private final List<E> list;

    public CustomStack() {
        list = new CustomArrayList<>();
    }

    public CustomStack(E[] array) {
        list = new CustomArrayList<>(array);
    }

    @Override
    public E push(E item) {
        list.add(item);
        return item;
    }

    @Override
    public synchronized E pop() {
        try {
            return list.remove(list.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }

    @Override
    public synchronized E peek() {
        try {
            return list.get(list.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public synchronized boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        return list.equals(((CustomStack<?>) obj).list);
    }

    @Override
    public synchronized int hashCode() {
        return list.size();
    }

    @Override
    public synchronized int search(Object o) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }
}