package net.codejava.javaee.bookstore.service;

import net.codejava.javaee.bookstore.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void insert(User newUser) throws SQLException;

    List<User> listAll() throws SQLException;

    User get(int id) throws SQLException;

    boolean update(User user) throws SQLException;

    void delete(int id) throws SQLException;

    User find(String username) throws SQLException;

}
