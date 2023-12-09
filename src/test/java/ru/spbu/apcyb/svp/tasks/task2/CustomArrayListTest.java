package ru.spbu.apcyb.svp.tasks.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomArrayListTest {
    @Test
    void addTest() {
        int size = 50;
        CustomArrayList<Integer> list = new CustomArrayList<>();

        Integer[] expectedArray = new Integer[size];
        for (int i = 0; i < size; i++) {
            expectedArray[i] = i;
            list.add(i);
        }
        CustomArrayList<Integer> expectedList = new CustomArrayList<>(expectedArray);

        Assertions.assertEquals(expectedList, list);
    }

    @Test
    void addByIndexTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>(new Integer[]{1, 2, 4});
        list.add(2, 3);
        CustomArrayList<Integer> expectedList = new CustomArrayList<>(new Integer[]{1, 2, 3, 4});

        Assertions.assertEquals(expectedList, list);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.add(10, 10));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, -1));
    }

    @Test
    void containsTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>(new Integer[]{1, -1, 2, 0, -2});

        Assertions.assertTrue(list.contains(-1));
        Assertions.assertFalse(list.contains(5));
        Assertions.assertFalse(list.contains(1.1));

    }

    @Test
    void removeByIndexTest() {
        CustomArrayList<Integer> expectedList = new CustomArrayList<>(new Integer[]{1, 3});
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(1);
        Assertions.assertEquals(expectedList, list);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(10));
    }

    @Test
    void emptyTest() {
        CustomArrayList<Integer> notEmptyList = new CustomArrayList<>(new Integer[]{3});
        CustomArrayList<Integer> emptyList1 = new CustomArrayList<>();
        CustomArrayList<Integer> emptyList2 = new CustomArrayList<>(new Integer[]{1});
        emptyList2.remove(0);

        Assertions.assertTrue(emptyList1.isEmpty());
        Assertions.assertTrue(emptyList2.isEmpty());
        Assertions.assertFalse(notEmptyList.isEmpty());

    }

    @Test
    void getTest() {
        int size = 10;
        CustomArrayList<Integer> list = new CustomArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        for (int i = 0; i < size; i++) {
            Assertions.assertEquals(i, list.get(i));
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(size + 1));

    }

    @Test
    void setTest() {
        CustomArrayList<Integer> list = new CustomArrayList<>(new Integer[]{1, 2, 3});
        CustomArrayList<Integer> expectedList = new CustomArrayList<>(new Integer[]{1, 2, -3});
        Integer actualRemovedElem = list.set(2, -3);
        int expectedRemovedElem = 3;

        Assertions.assertEquals(expectedRemovedElem, actualRemovedElem);
        Assertions.assertEquals(expectedList, list);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 2));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.set(4, 2));
    }

    @Test
    void sizeTest() {
        int size = 10;
        CustomArrayList<Integer> list = new CustomArrayList<>();
        Assertions.assertEquals(0, list.size());

        for (int i = 0; i < size; i++) {
            list.add(i);
            Assertions.assertEquals(i + 1, list.size());
        }
    }
}
