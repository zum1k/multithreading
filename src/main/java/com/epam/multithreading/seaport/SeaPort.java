package com.epam.multithreading.seaport;

import com.epam.multithreading.entity.Container;
import com.epam.multithreading.entity.Ship;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class SeaPort {
    private static final SeaPort INSTANCE = new SeaPort();
    private static final int PORT_CONTAINER_CAPACITY = 20000;
    private static final int DOCKS_NUMBER = 6;
    private static final int WATER_AREA_CAPACITY = 50;
    private final Deque<Ship> waterAreaQueue = new ArrayDeque<>();
    private final Deque<Dock> dockQueue = new ArrayDeque<>();
    private Map<Integer, Dock> docks;
    private List<Container> containers = new ArrayList<>();
    Semaphore dockCapacity = new Semaphore(DOCKS_NUMBER);

    private SeaPort() {}

    //dopisat Singleton
    public static SeaPort getInstance() {
        return INSTANCE;
    }

    public void removeShip(Ship ship) {
    }

    public void addShip(Ship ship) {
    }

    private boolean isCanAddShipToDock() {
        return false;
    }
}
