package net.codejava.javaee.bookstore.service;
import net.codejava.javaee.bookstore.Utils.PropertyReader;
import net.codejava.javaee.bookstore.model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DaoTypeConnection {
    INSTANCE;

    private Connection jdbcConnection;
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "create";

    public Connection jDBC() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {


            String url = PropertyReader.getPropertyStr("url");
            String username = PropertyReader.getPropertyStr("username");
            String password = PropertyReader.getPropertyStr("password");


            try {
                DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            jdbcConnection = DriverManager.getConnection(
                    url, username, password);
            System.out.println(jdbcConnection);



        }
        return jdbcConnection;
    }

    public Configuration hibernate() {

        String url = PropertyReader.getPropertyStr("url");
        String username = PropertyReader.getPropertyStr("username");
        String password = PropertyReader.getPropertyStr("password");

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", username);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", "None");
        return configuration;
    }




}
