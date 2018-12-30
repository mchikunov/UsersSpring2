package net.codejava.javaee.bookstore.dao;

import net.codejava.javaee.bookstore.model.User;
import net.codejava.javaee.bookstore.service.DaoTypeConnection;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAOHibernateImpl implements UserDAO {

    private Session session;
    private final SessionFactory sessionFactory;

    public UserDAOHibernateImpl() {
        Configuration configuration = DaoTypeConnection.INSTANCE.hibernate();
        sessionFactory = createSessionFactory(configuration);

    }



    @Override
    public boolean insertUser(User user) throws HibernateException {
        session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }


        return true;
    }




    @Override
    public List<User> listAllUsers() throws HibernateException {
        session = sessionFactory.openSession();
        List listUsers = new ArrayList<User>();
        try{
        listUsers = session.createCriteria(User.class).list();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка списка", JOptionPane.OK_OPTION);
    } finally {
        if (session != null && session.isOpen()) {
           //session.close();
        }
    }
        //Query query = session.createQuery("from User"); РАБОЧЕЕ ТОЖЕ
        //listUsers =  query.list();
        return listUsers;

    }

    @Override
    public void deleteBook(User user) throws HibernateException {
        session = sessionFactory.openSession();
        // Query query = session.createQuery("from User where id = :id");
        // query.setParameter("id", user.getId());
        try {
            session.beginTransaction();
            session.delete(user);
            //session.delete(query.uniqueResult()); //ТОЖЕ РАБОЧЕЕ
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
              session.close();
            }
        }
        // System.out.println(((User) query.uniqueResult()).getFName());


    }

    @Override
    public boolean updateUser(User user) throws HibernateException {
        session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при обновлении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
               session.close();
            }
        }
        return true;



       }

    @Override
    public User getUser(int id) throws HibernateException {
        session = sessionFactory.openSession();
        User usr1 =null;
        //Query query = session.createQuery("from User where user_id = :id");
        //query.setParameter("id", id);
        //return (User) query.uniqueResult();
        try {
            usr1 = (User) session.load(User.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при получении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
             //   session.close();
            }
        }
        return usr1;
    }

    public User findUser(String username) throws HibernateException {
        session = sessionFactory.openSession();
        User usr1 = null;
        Query query = session.createQuery("from User where SName = :username");
        query.setParameter("username", username);
        try {
            usr1 = (User) query.uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return usr1;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}