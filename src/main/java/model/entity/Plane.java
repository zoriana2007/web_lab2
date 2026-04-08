package model.entity;

import java.util.Objects;

public class Plane {
    private int id;
    private Aviacompany aviacompany;
    private Manufacturer manufacturer;
    private String model;
    private int fuelCapacity;
    private int maxSpeed;

    public Plane(){};
    public Plane(Aviacompany aviacompany,Manufacturer manufacturer, String model, int fuelCapacity, int maxSpeed) {
        this.aviacompany=aviacompany;
        this.manufacturer = manufacturer;
        this.model = model;
        this.fuelCapacity = fuelCapacity;
        this.maxSpeed=maxSpeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aviacompany getAviacompany() {
        return aviacompany;
    }

    public void setAviacompany(Aviacompany aviacompany) {
        this.aviacompany = aviacompany;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane plane)) return false;
        return Objects.equals(manufacturer, plane.manufacturer) && Objects.equals(model, plane.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model);
    }

    @Override
    public String toString() {
        return manufacturer +
                ", модель: " + model +
                ", місткість баку: " + fuelCapacity +
                ", максимальна швидкість: " + maxSpeed;
    }
}
