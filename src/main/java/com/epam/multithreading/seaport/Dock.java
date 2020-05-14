package com.epam.multithreading.seaport;

import com.epam.multithreading.entity.Container;
import com.epam.multithreading.entity.Ship;

public class Dock {
    private int id;
    private SeaPort port;

    Dock(int id, SeaPort port) {
        this.id = id;
        this.port = port;
    }

    int getId() {
        return id;
    }

    public void
    uploadContainers(Ship ship) {
        Container container = ship.getContainer();
        port.addContainers(container);
    }

    public Container downloadContainers() {
        return port.getContainers();
    }


}
