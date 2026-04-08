package model.dao;

import model.dao.PlaneDao;
import model.entity.Plane;
import model.entity.Aviacompany;
import model.entity.Manufacturer;
import model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaneDaoImpl implements PlaneDao {

    @Override
    public List<Plane> getAllPlanes() {
        List<Plane> planes = new ArrayList<>();
        String sql = "SELECT p.*, a.id as avia_id, a.name as avia_name, a.country as avia_country, " +
                "m.id as man_id, m.name as man_name, m.country as man_country, m.founded_year " +
                "FROM plane p " +
                "LEFT JOIN aviacompany a ON p.aviacompany_id = a.id " +
                "LEFT JOIN manufacturer m ON p.manufacturer_id = m.id " +
                "ORDER BY p.id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                planes.add(mapRowToPlane(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при отриманні списку літаків", e);
        }
        return planes;
    }

    @Override
    public Plane getById(int id) {
        String sql = "SELECT p.*, a.id as avia_id, a.name as avia_name, a.country as avia_country, " +
                "m.id as man_id, m.name as man_name, m.country as man_country, m.founded_year " +
                "FROM plane p " +
                "LEFT JOIN aviacompany a ON p.aviacompany_id = a.id " +
                "LEFT JOIN manufacturer m ON p.manufacturer_id = m.id " +
                "WHERE p.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapRowToPlane(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при отриманні літака", e);
        }
    }

    @Override
    public void addPlane(Plane plane) {
        String sql = "INSERT INTO plane (aviacompany_id, manufacturer_id, model, fuel_capacity, max_speed) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setObject(1, plane.getAviacompany() != null ? plane.getAviacompany().getId() : null);
            pstmt.setObject(2, plane.getManufacturer() != null ? plane.getManufacturer().getId() : null);
            pstmt.setString(3, plane.getModel());
            pstmt.setDouble(4, plane.getFuelCapacity());
            pstmt.setInt(5, plane.getMaxSpeed());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    plane.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка у створенні літака", e);
        }
    }

    @Override
    public void updatePlane(Plane plane) {
        String sql = "UPDATE plane SET aviacompany_id = ?, manufacturer_id = ?, model = ?, " +
                "fuel_capacity = ?, max_speed = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, plane.getAviacompany() != null ? plane.getAviacompany().getId() : null);
            pstmt.setObject(2, plane.getManufacturer() != null ? plane.getManufacturer().getId() : null);
            pstmt.setString(3, plane.getModel());
            pstmt.setDouble(4, plane.getFuelCapacity());
            pstmt.setInt(5, plane.getMaxSpeed());
            pstmt.setInt(6, plane.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Помилка у редагуванні літака", e);
        }
    }

    @Override
    public void deletePlane(int id) {
        String sql = "DELETE FROM plane WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Помилка у видаленні літака", e);
        }
    }

    private Plane mapRowToPlane(ResultSet rs) throws SQLException {
        Plane plane = new Plane();
        plane.setId(rs.getInt("id"));
        plane.setModel(rs.getString("model"));
        plane.setFuelCapacity(rs.getInt("fuel_capacity"));
        plane.setMaxSpeed(rs.getInt("max_speed"));

        // Маппінг Aviacompany
        if (rs.getObject("avia_id") != null) {
            Aviacompany aviacompany = new Aviacompany(
                    rs.getInt("avia_id"),
                    rs.getString("avia_name"),
                    rs.getString("avia_country")
            );
            plane.setAviacompany(aviacompany);
        }

        // Маппінг Manufacturer
        if (rs.getObject("man_id") != null) {
            Manufacturer manufacturer = new Manufacturer(
                    rs.getInt("man_id"),
                    rs.getString("man_name"),
                    rs.getString("man_country"),
                    rs.getInt("founded_year")
            );
            plane.setManufacturer(manufacturer);
        }

        return plane;
    }
}