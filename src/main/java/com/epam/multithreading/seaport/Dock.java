package com.epam.multithreading.seaport;

public class Dock {
    private int id;
    private boolean isDocked;

    public Dock(){}

    public Dock(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDocked() {
        return isDocked;
    }

    public void setDocked(boolean docked) {
        isDocked = docked;
    }
}
