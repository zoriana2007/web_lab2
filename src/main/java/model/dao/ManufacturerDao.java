package model.dao;

import model.entity.Aviacompany;
import model.entity.Manufacturer;

import java.util.List;

public interface ManufacturerDao {
    Manufacturer getById(int id);
    List<Manufacturer> getAllManufacturer();
}
