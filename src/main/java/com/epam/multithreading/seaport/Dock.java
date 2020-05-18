package com.epam.multithreading.seaport;

import com.epam.multithreading.entity.Container;
import com.epam.multithreading.entity.Ship;

import java.util.ArrayList;
import java.util.List;

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

    public boolean download(Ship ship) {
        if (canDownloadContainer(ship)) {
            downloadContainer(ship);
            return true;
        }
        return false;
    }

    public boolean upload(Ship ship) {
        if (canUploadContainer(ship)) {
            uploadContainer(ship);
            return true;
        }
        return false;
    }

    void uploadContainer(Ship ship) {
        Container container = ship.getContainer();
        List<Container> containers = new ArrayList<>();
        while (container != null) {
            containers.add(container);
            container = ship.getContainer();
        }
        port.addContainers(containers);
    }

    void downloadContainer(Ship ship) {
        List<Container> portContainers = port.getContainers(ship.getCapacity());
        for (Container container : portContainers) {
            ship.loadContainer(container);
        }
    }

    boolean canUploadContainer(Ship ship) {
        return SeaPort.CONTAINERS_CAPACITY > port.getContainersSize() + ship.getContainersSize();
    }

    boolean canDownloadContainer(Ship ship) {
        return port.getContainersSize() >= ship.getContainersSize();
    }
}
