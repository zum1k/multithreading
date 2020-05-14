package com.epam.multithreading.seaport;

import com.epam.multithreading.entity.Container;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SeaPort {
    private static final Logger LOGGER = LogManager.getLogger(SeaPort.class);
    private static SeaPort INSTANCE = new SeaPort();
    private static final int CONTAINER_CAPACITY = 20000;
    private static final int DOCKS_AMOUNT = 6;
    private Semaphore dockSemaphore = new Semaphore(DOCKS_AMOUNT);
    private static AtomicBoolean initialized = new AtomicBoolean(false);
    private static final Lock lock = new ReentrantLock();

    private Queue<Container> containers;
    private Queue<Dock> docks;

    private SeaPort() {
        docks = new ArrayDeque<>(DOCKS_AMOUNT);
        containers = new ArrayDeque<>();
        for (int i = 0; i < DOCKS_AMOUNT; i++) {
            docks.add(new Dock(i + 1, this));
        }
        for (int i = 0; i < 100; i++) {
            containers.add(new Container());
        }
    }

    public static SeaPort getInstance() {
        if (!initialized.get()) {
            try {
                lock.lock();
                if (!initialized.get()) {
                    INSTANCE = new SeaPort();
                    initialized.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return INSTANCE;
    }

    Container getContainers() {
        lock.lock();
        Container container = containers.poll();
        LOGGER.info("In thread.. " + Thread.currentThread().getName() + " containers removed!!");
        lock.unlock();
        return container;
    }

    void addContainers(Container container) {
        lock.lock();
        containers.add(container);
        LOGGER.info("In thread.. " + Thread.currentThread().getName() + " containers added!");
        lock.unlock();
    }

    public Dock getDock() {
        try {
            dockSemaphore.acquire();
            Dock dock = docks.poll();
            LOGGER.info("In thread.. " + Thread.currentThread().getName() + " Dock " + dock.getId() + " used!!");
            return dock;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Something was wrong in");
    }

    public void returnDock(Dock dock) {
        try {
            docks.add(dock);
            LOGGER.info("In thread.. " + Thread.currentThread().getName() + " Dock " + dock.getId() + " added!!");
            dockSemaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
