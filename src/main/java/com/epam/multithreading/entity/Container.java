package com.epam.multithreading.entity;

import java.util.Objects;

public class Container {
    private ProductType type;

    public Container() {
    }

    public Container(ProductType type) {
        this.type = type;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Container container = (Container) o;
        return type == container.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Container {" +
                ", type = " + type +
                " }").toString();
    }
}
