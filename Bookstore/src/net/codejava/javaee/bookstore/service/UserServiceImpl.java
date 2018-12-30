package net.codejava.javaee.bookstore.service;
import net.codejava.javaee.bookstore.dao.UserDAO;
import net.codejava.javaee.bookstore.model.User;
import java.sql.SQLException;
import java.util.List;


public final class UserServiceImpl implements UserService{


    //Start singleton code
    private static UserServiceImpl _instance = null;

    private UserServiceImpl() {}

    public static synchronized UserServiceImpl getInstance() throws SQLException {
        if (_instance == null)
            _instance = new UserServiceImpl();
        return _instance;
    }
    //end singleton code


    private UserDAO userDAO;
    @Override
    public void insert(User newUser) throws SQLException{
        userDAO = UserDAOFactory.factoryInstance();
        userDAO.insertUser(newUser);
    }


    @Override
    public List<User> listAll() throws SQLException {
        userDAO = UserDAOFactory.factoryInstance();
      return  userDAO.listAllUsers();


    }

    @Override
    public User get(int id) throws SQLException {
        userDAO = UserDAOFactory.factoryInstance();
        return userDAO.getUser(id);
    }

    @Override
    public boolean update(User user) throws SQLException {
        userDAO = UserDAOFactory.factoryInstance();
        return userDAO.updateUser(user);
    }


    @Override
    public void delete(User user) throws SQLException {
        userDAO = UserDAOFactory.factoryInstance();
        userDAO.deleteBook(user);

    }

    public User find (String username) throws SQLException {
        userDAO = UserDAOFactory.factoryInstance();
        return userDAO.findUser(username);
    }



    }


