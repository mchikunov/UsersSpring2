package net.codejava.javaee.bookstore.service;
import net.codejava.javaee.bookstore.dao.UserDAO;
import net.codejava.javaee.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;


@Component
public final class UserServiceImpl implements UserService{




    private UserServiceImpl() {}


    @Autowired
    private UserDAO userDAO;
    @Override
    public void insert(User newUser) throws SQLException{
        userDAO.insertUser(newUser);
    }


    @Override
    public List<User> listAll() throws SQLException {
      return  userDAO.listAllUsers();


    }

    @Override
    public User get(int id) throws SQLException {
        return userDAO.getUser(id);
    }

    @Override
    public boolean update(User user) throws SQLException {
        return userDAO.updateUser(user);
    }


    @Override
    public void delete(int id) throws SQLException {
        userDAO.deleteBook(id);

    }

    public User find (String username) throws SQLException {
        return userDAO.findUser(username);
    }



    }


