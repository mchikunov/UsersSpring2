package net.codejava.javaee.bookstore.servlets;

import net.codejava.javaee.bookstore.model.User;
import net.codejava.javaee.bookstore.service.UserService;
import net.codejava.javaee.bookstore.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class LoginServlet extends HttpServlet {


    private User user;

    private UserService db;

    public void init() {
        try {
            db = UserServiceImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("un");
        Map<String, String> messages = new HashMap<>();

        if (username == null || username.isEmpty()) {
            messages.put("un", "Please enter username");
        }

        if (messages.isEmpty()) {

            try {
                user = db.find(username);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (user != null) {
                request.getSession().setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
                request.setAttribute("user", user);
                dispatcher.forward(request, response);
                return;
            } else {
                messages.put("un", "Unknown login, please try again");
            }
        }

        request.setAttribute("messages", messages.values());
        request.getRequestDispatcher("login.jsp").forward(request, response);


    }
}








