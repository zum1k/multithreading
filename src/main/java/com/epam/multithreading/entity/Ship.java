package com.epam.multithreading.entity;

import com.epam.multithreading.seaport.Dock;
import com.epam.multithreading.seaport.SeaPort;

import java.util.List;

public class Ship implements Runnable {
    private int shipID;
    private int shipCapacity;
    private List<Container> containers;

    public Ship() {}

    public Ship(int shipCapacity, int shipID) {
        this.shipCapacity = shipCapacity;
        this.shipCapacity = shipID;
    }

    public int getShipCapacity() {
        return shipCapacity;
    }

    public void run() {
        SeaPort seaPort = SeaPort.getInstance();

    }
}
