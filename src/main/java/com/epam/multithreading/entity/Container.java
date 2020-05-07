package com.epam.multithreading.entity;

import java.util.Objects;

public class Container {
    private int weight;
    private ProductType type;

    public Container(){}

    public Container(int weight, ProductType type) {
        this.weight = weight;
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return weight == container.weight &&
                type == container.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, type);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Container {" +
                "weight = " + weight +
                ", type = " + type +
                " }").toString();
    }
}
