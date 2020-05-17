package com.epam.multithreading.runner;

import com.epam.multithreading.exception.InvalidPathException;
import com.epam.multithreading.parser.ShipParser;
import com.epam.multithreading.reader.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    private static final String FILE_PATH = "src/main/resources/res/Ships.txt";

    public static void main(String[] args) throws Exception {
        Runner runner = new Runner();
        runner.run(FILE_PATH);
    }

    private void run(String path) {
        for (Thread t : buildShips(path)) {
            t.start();
        }
    }

    private List<Thread> buildShips(String path) {
        FileReader reader = new FileReader();
        ShipParser parser = new ShipParser();
        List<Thread> ships = new ArrayList<>();
        try {
            List<String> strings = reader.readStrings(path);
            ships.addAll(parser.parseShips(strings));
        } catch (InvalidPathException e) {
            LOGGER.error(e.getMessage(), e);

        }
        return ships;
    }
}
