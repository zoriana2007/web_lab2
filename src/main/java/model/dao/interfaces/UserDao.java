package model.dao.interfaces;

import model.entity.User;

public interface UserDao {
    User findByUsername(String username);
    boolean validateUser(String username, String password);
}