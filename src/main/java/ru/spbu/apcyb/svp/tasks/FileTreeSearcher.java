package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * Задание 3.
 */
public class FileTreeSearcher {
    private static final Logger logger = Logger.getLogger(FileTreeSearcher.class.getName());

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("requires 2 arguments (searchPath, outputFilePath)");
        }
        writeFileTree(Path.of(args[0]), Path.of(args[1]));
    }

    public static void writeFileTree(Path searchPath, Path outputFilePath) throws IOException {
        if (!Files.exists(searchPath)) {
            throw new FileNotFoundException(String.format("searchPath: %s not exists", searchPath));
        }
        if (!searchPath.isAbsolute()) {
            searchPath = searchPath.toAbsolutePath().normalize();
        }
        try (var filesStream = Files.walk(searchPath)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath.toFile()))) {
                filesStream.forEach(path -> {
                    try {
                        writer.write(path.toString());
                        writer.newLine();
                    } catch (IOException e) {
                        logger.warning(e.getMessage());
                    }
                });
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }
}

