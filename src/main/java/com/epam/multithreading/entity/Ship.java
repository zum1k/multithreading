package com.epam.multithreading.entity;

import com.epam.multithreading.seaport.Dock;
import com.epam.multithreading.seaport.SeaPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class Ship implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Ship.class);
    private int capacity;
    private ShipType type;
    private Queue<Container> containers;

    public Ship() {
    }

    public Ship(ShipType type, int capacity) {
        containers = new ArrayDeque<>();
        this.capacity = capacity;
        this.type = type;
    }

    public Ship(ShipType type, int capacity, List<Container> productContainers) {
        this.capacity = capacity;
        this.type = type;
        this.containers = new ArrayDeque<>();
        containers.addAll(productContainers);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Container getContainer() {
        return containers.poll();
    }

    public void loadContainer(Container container) {
        containers.add(container);
    }

    public int getContainersSize() {
        return containers.size();
    }

    @Override
    public void run() {
        SeaPort port = SeaPort.getInstance();
        boolean isTerminated = false;
        while (!isTerminated) {
            Dock dock = port.getDock();
            switch (type) {
                case UPLOADING:
                    isTerminated = dock.upload(this);
                    break;
                case DOWNLOADING:
                    isTerminated = dock.download(this);
                    break;
                case UPLOADING_DOWNLOADING: {
                    isTerminated = dock.upload(this);
                    if (!isTerminated) {
                        break;
                    }
                    isTerminated = dock.download(this);
                }
            }
            LOGGER.info("In thread..." + Thread.currentThread().getName() + " Departs from Dock");
            port.returnDock(dock);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        return capacity == ship.capacity &&
                type == ship.type &&
                Objects.equals(containers, ship.containers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, type, containers);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Ship{" +
                "capacity=" + capacity +
                ", type=" + type +
                ", containers=" + containers +
                '}').toString();
    }
}
