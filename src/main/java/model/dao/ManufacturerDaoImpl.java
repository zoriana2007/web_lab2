package model.dao;

import model.DatabaseConnection;
import model.dao.ManufacturerDao;
import model.entity.Manufacturer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDaoImpl implements ManufacturerDao {

    @Override
    public Manufacturer getById(int id) {
        String sql = "SELECT * FROM manufacturer WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Manufacturer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getInt("founded_year")
                );
            }

        } catch (SQLException e) {
            System.out.println("Помилка при отриманні виробника");
        }

        return null;
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {
        List<Manufacturer> list = new ArrayList<>();
        String sql = "SELECT * FROM manufacturer";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Manufacturer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getInt("founded_year")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Помилка при отриманні списку виробників");
        }

        return list;
    }
}