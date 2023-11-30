package ru.spbu.apcyb.svp.tasks.task2;

import java.util.*;

/**
 * Задание 2.
 */
public class CustomArrayList<E> implements List<E>, Iterable<E> {
    private static final String NOT_IMPLEMENTED = "Method not implemented";
    private static final int INITIAL_CAPACITY = 8;
    private int capacity;
    private int size = 0;
    private E[] array;

    public CustomArrayList() {
        capacity = INITIAL_CAPACITY;
        array = (E[]) new Object[capacity];
    }

    public CustomArrayList(E[] array) {
        this.array = Arrays.copyOf(array, array.length);
        capacity = array.length;
        size = array.length;
    }

    public CustomArrayList(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, array[i])) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean add(E e) {
        if (size >= capacity) {
            resize();
        }
        array[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E element) {
        Objects.checkIndex(index, size);
        if (size + 1 >= capacity) {
            resize();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }


    @Override
    public void clear() {
        array = (E[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return array[index];
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E o = array[index];
        array[index] = element;
        return o;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        E[] temp = array;
        array = (E[]) new Object[temp.length];
        System.arraycopy(temp, 0, array, 0, index);
        System.arraycopy(temp, index + 1, array, index, temp.length - index - 1);
        size--;

        return temp[index];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if (size != ((CustomArrayList<E>) obj).size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(array[i], ((CustomArrayList<E>) obj).array[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return size;
    }

    private void resize() {
        if (capacity == 0) {
            capacity = INITIAL_CAPACITY;
        } else if (capacity < Integer.MAX_VALUE / 2) {
            capacity *= 2;
        } else if (capacity < Integer.MAX_VALUE) {
            capacity = Integer.MAX_VALUE;
        } else {
            throw new IndexOutOfBoundsException("too large capacity");
        }
        E[] newArray = (E[]) new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }
}