package com.epam.multithreading.reader;

import com.epam.multithreading.exception.InvalidPathException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    private static final Logger LOGGER = LogManager.getLogger(FileReader.class);

    public List<String> readShipStrings(String filepath) throws InvalidPathException {
        List<String> lines;
        Path path = Paths.get(filepath);
        try (Stream<String> lineStream = Files.newBufferedReader(path).lines()) {
            lines = lineStream.collect(Collectors.toList());
        } catch (IOException ex) {
            LOGGER.error("Ошибка чтения", ex);
            throw new InvalidPathException("Ошибка чтения", ex);
        }
        LOGGER.info("Data has been read:");
        return lines;
    }
}
