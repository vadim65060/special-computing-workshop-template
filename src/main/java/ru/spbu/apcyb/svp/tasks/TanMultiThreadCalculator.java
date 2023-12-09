package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Задание 4.
 */
public class TanMultiThreadCalculator {
    private static final Logger logger = Logger.getLogger(TanMultiThreadCalculator.class.getName());

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        int numCount = 1_000_000;
        int threadCount = 4;
        Path numsFile = Path.of(String.format("./src/main/resources/num%d", numCount));
        Path outputFile = Path.of(String.format("./src/main/resources/values%d", numCount));
        if (!Files.exists(numsFile)) {
            generateNumsFile(numCount, numsFile);
        }
        List<Double> nums = Files.readAllLines(numsFile).stream().map(Double::valueOf).toList();

        writeResult(outputFile,computeTanMultiThread(nums,threadCount));

        benchTanOneThread(nums, true);
        benchTanMultiThread(nums, threadCount, true);
    }

    public static void generateNumsFile(int numsCount, Path numsFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numsFile.toFile()))) {
            for (int i = 0; i < numsCount; i++) {
                double num = Math.random() * Math.PI - Math.PI / 2;
                writer.write(String.valueOf(num));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public static void writeResult(Path outputFile, List<Double> values) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.toFile()))) {
            writer.write("Calculated %d numbers:%n".formatted(values.size()));
            values.forEach(value ->
            {
                try {
                    writer.write(String.valueOf(value));
                    writer.newLine();
                } catch (IOException e) {
                    logger.warning(e.getMessage());
                }
            });
        }
    }

    public static long benchTanMultiThread(List<Double> nums, int threadCount, boolean logTime)
            throws ExecutionException, InterruptedException {
        Instant startTime = Instant.now();
        computeTanMultiThread(nums, threadCount);
        Instant endTime = Instant.now();
        long runTime = Duration.between(startTime, endTime).toMillis();
        if (logTime) {
            logger.info("Time spent on calculation %d values with %d threads: %dms"
                    .formatted(nums.size(), threadCount, runTime));
        }
        return runTime;
    }

    public static long benchTanOneThread(List<Double> nums, boolean logTime) {
        Instant startTime = Instant.now();
        computeTanOneThread(nums);
        Instant endTime = Instant.now();
        long runTime = Duration.between(startTime, endTime).toMillis();
        if (logTime) {
            logger.info("Time spent on calculation %d values with 1 threads: %dms"
                    .formatted(nums.size(), runTime));
        }
        return runTime;
    }

    public static List<Double> computeTanMultiThread(List<Double> nums, int threadCount)
            throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        try {
            Future<List<Double>> futures = executorService.submit(
                    () -> nums.parallelStream().map(Math::tan).toList());
            return futures.get();
        } finally {
            executorService.shutdownNow();
        }
    }

    public static List<Double> computeTanOneThread(List<Double> nums) {
        return nums.stream().map(Math::tan).toList();
    }
}
