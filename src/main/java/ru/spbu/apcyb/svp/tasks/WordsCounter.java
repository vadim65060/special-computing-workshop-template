package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Задание 5.
 */
public class WordsCounter {
    private static final String COUNT_FILE_NAME = "!counts.txt";
    private static final int THREADS_COUNT = 4;
    private final Logger logger = Logger.getLogger(WordsCounter.class.getName());
    private final Path inputFile;
    private final Path outputDir;

    public static void main(String[] args) throws IOException {
        WordsCounter wordsCounter = new WordsCounter(Path.of("src/main/resources/WarAndPeace.txt"),
                Path.of("src/main/resources/task5"));
        Instant startTime = Instant.now();
        Map<String, Long> countedWords = wordsCounter.getCountWords();
        wordsCounter.writeResultInFile(countedWords);
        Instant endTime = Instant.now();
        long runTime = Duration.between(startTime, endTime).toMillis();
        wordsCounter.logger.info("Different words found: %d%nTime spent: %dms".formatted(countedWords.size(), runTime));
    }

    public WordsCounter(Path inputFile, Path outputDir) {
        this.inputFile = inputFile;
        this.outputDir = outputDir;
    }

    public Map<String, Long> getCountWords() throws IOException {
        String replacePattern = "[^\\p{L}]+";
        try (Stream<String> stringStream = Files.lines(inputFile)) {
            return stringStream.flatMap(line -> Arrays.stream(line.split(" ")))
                    .map(word -> word.trim().replaceAll(replacePattern, "").toLowerCase())
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        } catch (IOException e) {
            throw new IOException(e.getMessage() + "\nError while reading file");
        }
    }

    public void writeResultInFile(Map<String, Long> words) throws IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputDir + File.separator + COUNT_FILE_NAME))) {
            for (Map.Entry<String, Long> word : words.entrySet()) {

                String line = "%s %d%n".formatted(word.getKey(), word.getValue());
                writer.write(line);

                Path wordFile = Path.of(outputDir + File.separator + word.getKey() + ".txt");
                futures.add(writeWordToSeparateFile(wordFile, word.getKey(), word.getValue(), executorService));
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());

        } finally {
            futures.forEach(CompletableFuture::join);
            executorService.shutdown();
        }
    }

    private CompletableFuture<Void> writeWordToSeparateFile(Path path, String word, Long count,
                                                            ExecutorService executorService) {
        return CompletableFuture.runAsync(() -> {
            try (BufferedWriter wordFile = Files.newBufferedWriter(path)) {
                for (int i = 0; i < count; i++) {
                    wordFile.write(word + " ");
                }

            } catch (IOException e) {
                logger.severe("Cannot open file for word.\n" + Arrays.toString(e.getStackTrace()));
            }
        }, executorService);
    }
}
