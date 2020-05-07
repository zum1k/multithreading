package com.epam.multithreading.entity;

import com.epam.multithreading.seaport.SeaPort;

import java.util.List;
import java.util.Objects;

public class Ship implements Runnable {
    private int shipID;
    private int shipCapacity;
    private List<Container> containers;

    public Ship() {
    }

    public Ship(int shipCapacity, int shipID) {
        this.shipCapacity = shipCapacity;
        this.shipCapacity = shipID;
    }

    public int getShipCapacity() {
        return shipCapacity;
    }

    public void run() {
        SeaPort seaPort = SeaPort.getInstance();
        seaPort.addShip(this);
    }

    public int getShipID() {
        return shipID;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    public void setShipCapacity(int shipCapacity) {
        this.shipCapacity = shipCapacity;
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
        return shipID == ship.shipID &&
                shipCapacity == ship.shipCapacity &&
                Objects.equals(containers, ship.containers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipID, shipCapacity, containers);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Ship { " +
                "shipID = " + shipID +
                ", shipCapacity = " + shipCapacity +
                ", containers = " + containers +
                " }").toString();
    }
}
