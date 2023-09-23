package ru.spbu.apcyb.svp.tasks;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Задание 1.
 */

public class Task1 {
    private static final Logger logger = Logger.getLogger(Task1.class.getName());

    private static int getIntInput() {
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            input = scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("error: not int input");
        }
        return input;
    }

    private static int getPositiveInt() {
        int number = getIntInput();

        while (number <= 0) {
            logger.info("error: int not positive error");
            number = getIntInput();
        }
        return number;
    }

    private static int[] getIntArray() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] noms = str.split(" ");
        int[] array = new int[noms.length];
        for (int i = 0; i < array.length; i++) {
            try {
                array[i] = Integer.parseInt(noms[i]);
            } catch (Exception e) {
                throw new IllegalArgumentException("error: not int input");
            }
        }
        return array;
    }

    private static void printCombinations(List<int[]> combinations, int[] nominals) {
        for (var line : combinations) {
            for (int i = 0; i < line.length; i++) {
                for (int j = line[i]; j > 0; j--) {
                    logger.info(String.format("%s ", nominals[i]));
                }
            }
            logger.info("\n");
        }
    }

    public static void main(String[] args) {
        int sum = getPositiveInt();
        int[] nominals = getIntArray();
        nominals = Arrays.stream(nominals).distinct().toArray();
        Arrays.sort(nominals);

        CashMachine cashMachine = new CashMachine(nominals);
        List<int[]> combinations = cashMachine.getCombinations(sum);
        logger.info(combinations.size() + "\n");
        printCombinations(combinations, nominals);
    }
}
