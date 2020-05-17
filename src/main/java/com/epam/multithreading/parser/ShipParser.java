package com.epam.multithreading.parser;

import com.epam.multithreading.entity.Container;
import com.epam.multithreading.entity.ProductType;
import com.epam.multithreading.entity.Ship;
import com.epam.multithreading.entity.ShipType;

import java.util.ArrayList;
import java.util.List;

public class ShipParser {
    private static final String SPACE_SPLITERATOR = "\\s";
    private static final int THREAD_SHIP_INDEX = 0;
    private static final int TYPE_SHIP_INDEX = 1;
    private static final int SHIP_CAPACITY_INDEX = 2;
    private static final int PRODUCT_TYPE_INDEX = 3;

    public List<Thread> parseShips(List<String> strings) {
        List<Thread> ships = new ArrayList<>();
        for (String s : strings) {
            ships.add(parseShip(s));
        }
        return ships;
    }

    private List<Container> parseContainers(int shipCapacity, ProductType type) {
        List<Container> containers = new ArrayList<>();
        int i = containers.size();
        while (i < shipCapacity) {
            containers.add(new Container(type));
            i++;
        }
        return containers;
    }

    private Thread parseShip(String dataString) {
        String[] parts = dataString.split(SPACE_SPLITERATOR);

        int id = Integer.parseInt(parts[THREAD_SHIP_INDEX]);
        ShipType shipType = ShipType.valueOf(parts[TYPE_SHIP_INDEX]);
        int capacity = Integer.parseInt(parts[SHIP_CAPACITY_INDEX]);
        ProductType productType = ProductType.valueOf(parts[PRODUCT_TYPE_INDEX]);

        if (shipType == ShipType.DOWNLOADING) {
            Thread ship = new Thread(new Ship(shipType, capacity), "Ship-" + id);
            return ship;
        } else {
            List<Container> containers = parseContainers(capacity, productType);
            Thread ship = new Thread(new Ship(shipType, capacity, containers), "Ship-" + id);
            return ship;
        }
    }
}
