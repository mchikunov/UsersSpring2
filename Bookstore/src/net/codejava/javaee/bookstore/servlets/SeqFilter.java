package net.codejava.javaee.bookstore.servlets;

import net.codejava.javaee.bookstore.model.User;
import net.codejava.javaee.bookstore.service.UserService;
import net.codejava.javaee.bookstore.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;


@WebFilter({"/NNN", "/new", "/delete", "/update", "/edit", "/insert"})
public class SeqFilter implements Filter {

    private User user = null;

    private UserService db;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            db = UserServiceImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        user = (User) session.getAttribute("user");
        String userRole = user.getRole();
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            if (userRole.equals("admin")) chain.doFilter(request, response);
            else request.getRequestDispatcher("UserForm.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("UserForm.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
