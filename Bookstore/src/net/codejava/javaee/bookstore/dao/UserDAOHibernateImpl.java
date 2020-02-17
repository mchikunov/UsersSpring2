package net.codejava.javaee.bookstore.dao;

import net.codejava.javaee.bookstore.Utils.HibernateInstance;
import net.codejava.javaee.bookstore.model.Roles;
import net.codejava.javaee.bookstore.model.User;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Conditional(value = HibernateInstance.class)
public class UserDAOHibernateImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;




    @Transactional
    @Override
    public boolean insertUser(User user) throws HibernateException  {
            em.persist(user);
            return true;


    }



@Transactional
    @Override
    public List<User> listAllUsers() throws HibernateException {
        List listUsers;

        listUsers = em.createQuery("SELECT c from User c")
                .getResultList();

        return listUsers;

    }
@Transactional
    @Override
    public void deleteBook(int id) throws HibernateException {
            User user = em.find(User.class, id);
            em.remove(user);

    }
    @Transactional
    @Override
    public boolean updateUser(User user) throws HibernateException {

            em.merge(user);


        return true;



       }

    @Override
    public User getUser(int id) {

        return em.find(User.class, id);

    }

    public User findUser(String username) throws HibernateException {


       return (User)em.createQuery("select c from User c where c.SName = :name")
                .setParameter("name", username)
                .getSingleResult();


    }

    @Override
   public int findRoleId (String user) throws HibernateException{

       Roles role = (Roles)em.createQuery("select c from Roles c where c.roles = :name")
               .setParameter("name", user)
               .getSingleResult();

       return role.getId();
   }



}