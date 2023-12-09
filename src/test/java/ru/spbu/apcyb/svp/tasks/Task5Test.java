package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Тесты для задания 5.
 */
class Task5Test {
    @Test
    void getCountWordsTest() throws IOException {
        WordsCounter wordsCounter = new WordsCounter(Path.of("src/test/resources/TestText.txt"),
                Path.of("src/java/resources/task5"));
        Map<String, Long> countWords = wordsCounter.getCountWords();
        Map<String, Long> expectedCountWords = new HashMap<>();
        expectedCountWords.put("foo", 2L);
        expectedCountWords.put("bar", 1L);
        expectedCountWords.put("baz", 1L);
        Assertions.assertEquals(expectedCountWords, countWords);
    }

    @Test
    void writeResultInFileTest() throws IOException {
        WordsCounter wordsCounter = new WordsCounter(Path.of("src/test/resources/TestText.txt"),
                Path.of("src/test/resources/task5"));
        Map<String, Long> countWords = wordsCounter.getCountWords();
        wordsCounter.writeResultInFile(countWords);
        String expectedText = "bar 1\nfoo 2\nbaz 1\n";
        String currentText = Files.readString(Path.of("src/test/resources/task5/!counts.txt"))
                .replace("\r", "");
        Assertions.assertEquals(expectedText, currentText);
    }
}
