package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Тесты для задания 3.
 */
class Task3Test {
    @Test
    void writeFileTreeTest() throws IOException {
        Path searchPath = Path.of("./images");
        Path outputFilePath = Path.of("./output.txt");
        FileTreeSearcher.writeFileTree(searchPath, outputFilePath);
        String[] files = {
                "images",
                "img_1.png",
                "img_2.png",
                "img_3.png",
                "img_4.png",
                "img_5.png"
        };
        List<String> actual = Files.readAllLines(outputFilePath);
        for (int i = 0; i < files.length; i++) {
            Assertions.assertTrue(actual.get(i).contains(files[i]));
        }
    }
}
