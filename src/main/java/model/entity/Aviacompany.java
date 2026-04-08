package model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Aviacompany {
    private int id;
    private String name;
    private  String country;
    private List<Plane> planes=new ArrayList<>();

    public Aviacompany(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aviacompany that = (Aviacompany) o;
        return Objects.equals(name, that.name) && Objects.equals(country, that.country) && Objects.equals(planes, that.planes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, planes);
    }

    @Override
    public String toString() {
        return "Авіакомпанія: " + name +
                ", країна: " + country;
    }
}
