package model.dao;

import model.DatabaseConnection;
import model.dao.interfaces.AviacompanyDao;
import model.entity.Aviacompany;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AviacompanyDaoImpl implements AviacompanyDao {

    @Override
    public Aviacompany getById(int id) {
        String sql = "SELECT * FROM aviacompany WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Aviacompany(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("country")
                );
            }

        } catch (SQLException e) {
            System.out.println("Помилка при отриманні авіакомпанії");
        }

        return null;
    }

    @Override
    public List<Aviacompany> getAllCompanies() {
        List<Aviacompany> list = new ArrayList<>();
        String sql = "SELECT * FROM aviacompany";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Aviacompany(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("country")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Помилка при отриманні списку авіакомпаній");
        }

        return list;
    }
}