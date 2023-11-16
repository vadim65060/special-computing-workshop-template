package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CashMachine {
    private static final Logger logger = Logger.getLogger(CashMachine.class.getName());
    private final ArrayList<int[]> combinations = new ArrayList<>();
    private boolean logOn = false;
    private int sum;
    private int[] nominals;

    CashMachine(int[] nominals) {
        this.nominals = nominals;
        Arrays.sort(this.nominals);
    }

    CashMachine(int[] nominals, boolean logOn) {
        this.nominals = nominals;
        Arrays.sort(this.nominals);
        this.logOn = logOn;
    }

    public List<int[]> getCombinations(int sum, int[] nominals) {
        this.nominals = nominals;
        Arrays.sort(this.nominals);
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
                    if (logOn) {
                        printCombination(combinations.get(combinations.size() - 1));
                    }
                } else if (h + 1 < nominals.length) {
                    findCombinations(remainder, nominalsCount.clone(), h + 1);
                }
            }

            remainder -= nominals[h];
        }

    }

    private void printCombination(int[] combination) {
        for (int i = 0; i < combination.length; i++) {
            for (int j = combination[i]; j > 0; j--) {
                logger.info(String.format("%s ", nominals[i]));
            }
        }
        logger.info("\n");
    }
}