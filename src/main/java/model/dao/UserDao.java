package model.dao;

import model.entity.User;

public interface UserDao {
    User findByUsername(String username);
    boolean validateUser(String username, String password);
}