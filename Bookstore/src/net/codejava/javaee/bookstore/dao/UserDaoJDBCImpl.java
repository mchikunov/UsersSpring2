package net.codejava.javaee.bookstore.dao;

import net.codejava.javaee.bookstore.model.User;
import net.codejava.javaee.bookstore.service.DaoTypeConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDAO {


        private Connection jdbcConnection;

        public UserDaoJDBCImpl() throws SQLException {

            jdbcConnection = DaoTypeConnection.INSTANCE.jDBC();
        }


        @Override
        public boolean insertUser(User user) throws SQLException {

            String sql = "INSERT INTO users (FName, SName, Age, Role) VALUES (?, ?, ?, ?)";
            jdbcConnection.setAutoCommit(false);
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            String ss = "ываыва";
            System.out.println(user.getFName()+ user.getSName()+ss);
            statement.setString(1, user.getFName());
            statement.setString(2, user.getSName());
            statement.setFloat(3, user.getAge());
            statement.setString(4, user.getRole());

            boolean rowInserted = statement.executeUpdate() > 0;
            jdbcConnection.commit();
            jdbcConnection.setAutoCommit(true);
            statement.close();
            return rowInserted;
        }

        @Override
        public List<User> listAllUsers() throws SQLException {
            List<User> listUsers = new ArrayList<>();

            String sql = "SELECT * FROM users";

            Statement statement = jdbcConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String fName = resultSet.getString("FName");
                String sName = resultSet.getString("SName");
                float age = resultSet.getFloat("Age");
                String role = resultSet.getString("Role");

                User user = new User(id, fName, sName, age, role);
                listUsers.add(user);
            }

            resultSet.close();
            statement.close();


            return listUsers;
        }

        @Override
        public void deleteBook(User user) throws SQLException {
            String sql = "DELETE FROM users where user_id = ?";


            jdbcConnection.setAutoCommit(false);
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, user.getId());

            boolean rowDeleted = statement.executeUpdate() > 0;
            jdbcConnection.commit();
            jdbcConnection.setAutoCommit(true);
            statement.close();

        }

        @Override
        public boolean updateUser(User user) throws SQLException {
            String sql = "UPDATE users SET FName = ?, SName = ?, Age = ?, Role = ?";
            sql += " WHERE user_id = ?";

            jdbcConnection.setAutoCommit(false);
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, user.getFName());
            statement.setString(2, user.getSName());
            statement.setFloat(3, user.getAge());
            statement.setString(4, user.getRole());
            statement.setInt(5, user.getId());


            boolean rowUpdated = statement.executeUpdate() > 0;
            jdbcConnection.commit();
            jdbcConnection.setAutoCommit(true);
            statement.close();

            return rowUpdated;
        }

        @Override
    public User findUser(String username) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE SName = ?";



        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String fName = resultSet.getString("FName");
            String sName = resultSet.getString("SName");
            float age = resultSet.getFloat("Age");
            String role = resultSet.getString("Role");
            int id = resultSet.getInt("user_id");

            user = new User(id, fName, sName, age, role);
        }

        resultSet.close();
        statement.close();

        return user;
    }

    @Override
    public User getUser(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE user_id = ?";



        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String fName = resultSet.getString("FName");
            String sName = resultSet.getString("SName");
            float age = resultSet.getFloat("Age");
            String role = resultSet.getString("Role");

            user = new User(id, fName, sName, age, role);
        }

        resultSet.close();
        statement.close();

        return user;
    }
    }


