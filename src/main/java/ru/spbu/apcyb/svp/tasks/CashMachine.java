package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.List;

public class CashMachine {
    private final ArrayList<int[]> combinations = new ArrayList<>();
    private int sum;
    private int[] nominals;

    CashMachine(int[] nominals) {
        this.nominals = nominals;
    }

    public List<int[]> getCombinations(int sum, int[] nominals) {
        this.nominals = nominals;
        return getCombinations(sum);
    }

    public List<int[]> getCombinations(int sum) {
        this.sum = sum;
        combinations.clear();
        findCombinations(sum, new int[nominals.length], 0);
        return combinations;
    }

    /**
     * Рекурсивный метод нахождения разложения.
     *
     * @param remainder     число для размена после вычитания нужно количества номиналов
     * @param nominalsCount массив количества использований каждого номинала
     * @param h             индекс, предотвращающий появления результатов с точностью до перестановки
     */
    private void findCombinations(int remainder, int[] nominalsCount, int h) {
        int div = sum / nominals[h]; //Количество полных вхождений в число размена

        for (int i = 0; i <= div; i++) {
            if (remainder >= 0) {
                nominalsCount[h] = i;

                if (remainder == 0) {
                    combinations.add(nominalsCount);
                } else if (h + 1 < nominals.length) {
                    findCombinations(remainder, nominalsCount.clone(), h + 1);
                }
            }

            remainder -= nominals[h];
        }

    }
}