package net.codejava.javaee.bookstore.service;


import net.codejava.javaee.bookstore.Utils.PropertyReader;
import net.codejava.javaee.bookstore.dao.UserDAO;
import net.codejava.javaee.bookstore.dao.UserDAOHibernateImpl;
import net.codejava.javaee.bookstore.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class UserDAOFactory {


public static UserDAO factoryInstance() throws SQLException {
    UserDAO userDAO;

    String instance = PropertyReader.getPropertyStr("config");
    if (instance.equals("hibernate"))
        userDAO = new UserDAOHibernateImpl();
    else userDAO = new UserDaoJDBCImpl();

    return userDAO;
}



}
