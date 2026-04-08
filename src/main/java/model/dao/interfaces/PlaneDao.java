package model.dao.interfaces;

import model.entity.Plane;

import java.util.List;

public interface PlaneDao {
    List<Plane> getAllPlanes();
    Plane getById(int id);
    void addPlane(Plane plane);
    void deletePlane(int id);
    void updatePlane(Plane plane);
}
