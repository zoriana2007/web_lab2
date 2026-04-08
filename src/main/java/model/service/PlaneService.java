package model.service;

import controller.validator.PlaneValidator;
import model.dao.*;
import model.entity.Plane;
import model.entity.Aviacompany;
import model.entity.Manufacturer;

import java.util.List;

public class PlaneService {
    private PlaneDao planeDAO;
    private AviacompanyDao aviacompanyDAO;
    private ManufacturerDao manufacturerDAO;
    private PlaneValidator validator;

    public PlaneService() {
        this.planeDAO = new PlaneDaoImpl();
        this.aviacompanyDAO = new AviacompanyDaoImpl();
        this.manufacturerDAO = new ManufacturerDaoImpl();
        this.validator = new PlaneValidator();
    }

    public List<Plane> getAllPlanes() {
        return planeDAO.getAllPlanes();
    }

    public Plane getPlaneById(int id) {
        return planeDAO.getById(id);
    }

    public void createPlane(Plane plane) {
        validator.validatePlane(plane);
        planeDAO.addPlane(plane);
    }

    public void updatePlane(Plane plane) {
        validator.validatePlane(plane);
        planeDAO.updatePlane(plane);
    }

    public void deletePlane(int id) {
        planeDAO.deletePlane(id);
    }

    public List<Aviacompany> getAllAviacompanies() {
        return aviacompanyDAO.getAllCompanies();
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerDAO.getAllManufacturer();
    }
}