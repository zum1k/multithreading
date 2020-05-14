package com.epam.multithreading.entity;

import com.epam.multithreading.seaport.Dock;
import com.epam.multithreading.seaport.SeaPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Ship implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Ship.class);
    private ShipType type;
    private Container container = null;

    public Ship(ShipType type) {
        this.type = type;
    }

    public Ship(ShipType type, Container container) {
        this.type = type;
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }

    public void loadContainer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        SeaPort port = SeaPort.getInstance();
        Dock dock = port.getDocks();
        switch (type) {
            case UPLOADING:
                dock.uploadContainers(this);
                break;
            case DOWNLOADING:
                loadContainer(dock.downloadContainers());
                break;
            case UPLOADING_DOWNLOADING:
                {
                dock.uploadContainers(this);
                loadContainer(dock.downloadContainers());
                }
                break;
        }
        LOGGER.info("In thread..." + Thread.currentThread().getName()+" Waiting for loading");
        port.returnDocks(dock);
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
        return type == ship.type &&
                container.equals(ship.container);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, container);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Ship{" +
                "type=" + type +
                ", container=" + container +
                '}').toString();
    }
}
