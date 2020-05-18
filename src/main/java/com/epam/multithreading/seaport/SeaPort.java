package com.epam.multithreading.seaport;

import com.epam.multithreading.entity.Container;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SeaPort {
    private static final Logger LOGGER = LogManager.getLogger(SeaPort.class);
    private static final int DOCKS_AMOUNT = 6;
    private static final Lock LOCK = new ReentrantLock();
    private static SeaPort INSTANCE = new SeaPort();
    private static AtomicBoolean INITIALIZED = new AtomicBoolean(false);

    public static final int CONTAINERS_CAPACITY = 350000;

    private Semaphore dockSemaphore = new Semaphore(DOCKS_AMOUNT);

    private Queue<Container> containers;
    private Queue<Dock> docks;

    private SeaPort() {
        docks = new ArrayDeque<>(DOCKS_AMOUNT);
        containers = new ArrayDeque<>();
        for (int i = 0; i < DOCKS_AMOUNT; i++) {
            docks.add(new Dock(i + 1, this));
        }
        for (int i = 0; i < 15000; i++) {
            containers.add(new Container());
        }
    }

    public static SeaPort getInstance() {
        if (!INITIALIZED.get()) {
            try {
                LOCK.lock();
                if (!INITIALIZED.get()) {
                    INSTANCE = new SeaPort();
                    INITIALIZED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return INSTANCE;
    }

    List<Container> getContainers(int capacity) {
        LOCK.lock();
        List<Container> containers = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            containers.add(this.containers.poll());
        }
        LOGGER.info("In thread.. " + Thread.currentThread().getName() +" "+capacity+ " containers removed.");
        LOCK.unlock();
        return containers;
    }

    void addContainers(List<Container> shipContainers) {
        LOCK.lock();
        containers.addAll(shipContainers);
        LOCK.unlock();
        LOGGER.info("In thread.. " + Thread.currentThread().getName() +" "+ shipContainers.size()+" containers added.");
    }

    public Dock getDock() {
        try {
            LOGGER.info("In thread... " + Thread.currentThread().getName() + " trying to get resource.");
            dockSemaphore.acquire();
            LOCK.lock();
            Dock dock = docks.poll();
            LOGGER.info("In thread.. " + Thread.currentThread().getName() + " Dock " + dock.getId() + " used!!");
            return dock;
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            LOCK.unlock();
        }
        throw new RuntimeException("Somethings happened wrong!!!");
    }

    public void returnDock(Dock dock) {
        try {
            LOCK.lock();
            docks.add(dock);
            LOGGER.info("In thread.. " + Thread.currentThread().getName() + " Dock " + dock.getId() + " returned!");
            dockSemaphore.release();
        } finally {
            LOCK.unlock();
        }
    }

    int getContainersSize() {
        return containers.size();
    }
}
