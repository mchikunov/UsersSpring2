package net.codejava.javaee.bookstore.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.codejava.javaee.bookstore.model.Roles;
import net.codejava.javaee.bookstore.model.User;
import net.codejava.javaee.bookstore.service.UserService;
import net.codejava.javaee.bookstore.service.UserServiceImpl;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;

@RestController
@EnableWebMvc
public class ControllerServlet {



    @Autowired
    private UserService db;
    @Autowired
    private PasswordEncoder passwordEncoder;




    @RequestMapping("/")
    public ModelAndView wlc (@RequestParam(value = "error", required = false) String error) {


        ModelAndView mw = new ModelAndView();
        if (error != null) {
            mw.addObject("error", "Invalid username and password!");
        }
        mw.setViewName("login.jsp");
        return mw;
    }

    @RequestMapping("/user")
    public ModelAndView login () {
        org.springframework.security.core.userdetails.User user = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
                user = (org.springframework.security.core.userdetails.User) principal;

        }
            Collection<GrantedAuthority> userRole = user.getAuthorities();
            System.out.println(userRole);

            ModelAndView mw = new ModelAndView();
            mw.addObject(user);
            mw.setViewName("UserForm");
            return mw;

    }

    @RequestMapping("/admin")
    public ModelAndView adm ()  {

        ModelAndView mw = new ModelAndView();
        mw.setViewName("pages/BookList.html");
        return mw;


       // return "pages/BookList.html";
    }



    @RequestMapping("/admin1")
    public ResponseEntity<?> listAll_() throws SQLException {
        List<User> users = db.listAll();
        return ResponseEntity.ok(users);
    }






    @RequestMapping(value = "/insert", method = RequestMethod.POST)
   /* public void insert (@RequestParam("FName") String fName,
                                @RequestParam("SName") String sName,
                                @RequestParam("Age") Float age,
                                @RequestParam("Role") String role
    ) throws HibernateException, SQLException {*/
    public void updateHosting(@RequestBody User userForm) throws HibernateException, SQLException, IOException {

       // User user = new ObjectMapper().readValue(userForm, User.class);
       // System.out.println(userForm);


        String fName = userForm.getFName();
        String role = userForm.getRole();
        String sName = userForm.getSName();
        Float age = userForm.getAge();

      //  fName = passwordEncoder.encode(fName);

        int roleId = db.findRoleId(role);
        Roles roles = new Roles(roleId, role);
        HashSet<Roles> rolesHashSet = new HashSet<>();
        rolesHashSet.add(roles);
        User newUser = new User(fName, sName, age, rolesHashSet);
        db.insert(newUser);

    }




    @RequestMapping(value = "/update1", method = RequestMethod.POST)
   /* public void insert (@RequestParam("FName") String fName,
                                @RequestParam("SName") String sName,
                                @RequestParam("Age") Float age,
                                @RequestParam("Role") String role
    ) throws HibernateException, SQLException {*/
    public void updateH(@RequestBody User userForm) throws HibernateException, SQLException, IOException {

        // User user = new ObjectMapper().readValue(userForm, User.class);
        // System.out.println(userForm);

        int id = userForm.getId();
        String fName = userForm.getFName();
        String role = userForm.getRole();
        String sName = userForm.getSName();
        Float age = userForm.getAge();

        //  fName = passwordEncoder.encode(fName);

        int roleId = db.findRoleId(role);
        Roles roles = new Roles(roleId, role);
        HashSet<Roles> rolesHashSet = new HashSet<>();
        rolesHashSet.add(roles);
        User newUser = new User(id, fName, sName, age, rolesHashSet);
        db.update(newUser);

    }





/*
    @RequestMapping("/update1")
    public void update (@RequestParam("id") int id,
                                @RequestParam("FName") String fName,
                                @RequestParam("SName") String sName,
                                @RequestParam("age") Float age,
                                @RequestParam("role") String role


    ) throws SQLException {


        int roleId = db.findRoleId(role);
        Roles roles = new Roles(roleId, role);
        HashSet<Roles> rolesHashSet = new HashSet<>();
        rolesHashSet.add(roles);

      User user = new User(id, fName, sName, age, rolesHashSet);

        db.update(user);

    }*/

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RedirectView delH(@RequestBody User userForm) throws HibernateException, SQLException {
        int id1 = userForm.getId();
        db.delete(id1);
        return new RedirectView("/admin");

    }








}








