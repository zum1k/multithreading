package com.epam.multithreading.runner;

import com.epam.multithreading.entity.Container;
import com.epam.multithreading.entity.Ship;
import com.epam.multithreading.entity.ShipType;

import java.util.Random;

public class Runner {
    public static void main(String[]args) {

        Random r = new Random();
        for(int n = 0; n < 50; n++){
            Thread ship = new Thread(new Ship(ShipType.values()[r.nextInt(ShipType.values().length)], new Container()),"Ship "+n);
            ship.start();
        }
    }
}
