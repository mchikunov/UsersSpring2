package net.codejava.javaee.bookstore.servlets;

import net.codejava.javaee.bookstore.model.User;
import net.codejava.javaee.bookstore.service.UserService;
import net.codejava.javaee.bookstore.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

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
        mw.setViewName("login");
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
    public ModelAndView listAll() throws SQLException {
        List<User> listUsers = db.listAll();
        ModelAndView mw = new ModelAndView();
        mw.addObject("listUsers", listUsers);
        mw.setViewName("BookList");
        return mw;
    }

    @RequestMapping("/edit")
    public ModelAndView edit (@RequestParam("id") int id) throws SQLException {
        User existingUser = db.get(id);
        ModelAndView mw = new ModelAndView();
        mw.addObject("user", existingUser);
        mw.setViewName("BookForm");
        return mw;
    }

    @RequestMapping("/new")
    public ModelAndView new1() {
        ModelAndView mw = new ModelAndView();
        mw.setViewName("BookForm");
        return mw;
    }


    @RequestMapping("/insert")
    public ModelAndView insert (@RequestParam("FName") String fName,
                                @RequestParam("SName") String sName,
                                @RequestParam("age") Float age,
                                @RequestParam("role") String role
    ) throws SQLException {
        fName = passwordEncoder.encode(fName);
        User newUser = new User(fName, sName, age, role);
        db.insert(newUser);
        return listAll();
    }

    @RequestMapping("/update1")
    public ModelAndView update (@RequestParam("id") int id,
                                @RequestParam("FName") String fName,
                                @RequestParam("SName") String sName,
                                @RequestParam("age") Float age,
                                @RequestParam("role") String role


    ) throws SQLException {

      User user = new User(id, fName, sName, age, role);

        db.update(user);
        return listAll();
    }

    @RequestMapping("/delete")
    public ModelAndView delete (@RequestParam("id") int id) throws SQLException {
        db.delete(id);
        return listAll();
    }








}








