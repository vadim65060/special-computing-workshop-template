package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Тесты для задания 4.
 */
class Task4Test {
    @Test
    void generateNumsFileTest() throws IOException {
        int numCount = 100;
        Path numsFile = Path.of(String.format("./src/test/resources/nums%d", numCount));
        TanMultiThreadCalculator.generateNumsFile(numCount, numsFile);
        List<Double> nums = Files.readAllLines(numsFile).stream().map(Double::valueOf).toList();

        for (var num : nums) {
            Assertions.assertTrue(-Math.PI / 2 <= num && num < Math.PI / 2);
        }
    }

    @Test
    void writeResultTest() throws IOException {
        int numCount = 100;
        Path outputFile = Path.of(String.format("./src/test/resources/out%d", numCount));
        List<Double> nums = new LinkedList<>();
        for (int i = 0; i < numCount; i++) {
            nums.add(Math.random());
        }
        TanMultiThreadCalculator.writeResult(outputFile, nums);
        List<String> outText = Files.readAllLines(outputFile);
        for (int i = 0; i < numCount; i++) {
            Assertions.assertEquals(nums.get(i), Double.valueOf(outText.get(i + 1)));
        }
    }

    @Test
    void tanComputeTest() throws ExecutionException, InterruptedException {
        int numCount = 100;
        List<Double> nums = new LinkedList<>();
        for (int i = 0; i < numCount; i++) {
            nums.add(Math.random() * Math.PI - Math.PI / 2);
        }
        Assertions.assertEquals(TanMultiThreadCalculator.computeTanOneThread(nums),
                TanMultiThreadCalculator.computeTanMultiThread(nums, 4));
    }
}
