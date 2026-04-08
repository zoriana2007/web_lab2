package model.dao;

import model.entity.Aviacompany;

import java.util.List;

public interface AviacompanyDao {
    Aviacompany getById(int id);
    List<Aviacompany> getAllCompanies();
}
