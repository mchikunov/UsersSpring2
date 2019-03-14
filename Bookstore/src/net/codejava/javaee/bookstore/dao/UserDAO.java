package net.codejava.javaee.bookstore.dao;

import net.codejava.javaee.bookstore.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    boolean insertUser(User user) throws SQLException;

    List<User> listAllUsers() throws SQLException;

    void deleteBook(int id) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    User getUser(int id) throws SQLException;

    User findUser(String username) throws SQLException;


}

