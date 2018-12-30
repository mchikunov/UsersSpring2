package net.codejava.javaee.bookstore.servlets;

import net.codejava.javaee.bookstore.model.User;
import net.codejava.javaee.bookstore.service.UserDAOFactory;
import net.codejava.javaee.bookstore.service.UserService;
import net.codejava.javaee.bookstore.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {

    private UserService db;

    public void init() {
        try {
            db = UserServiceImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            insertBook(request, response);

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    public void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("FName");
        String author = request.getParameter("SName");
        float price = Float.parseFloat(request.getParameter("age"));
        String role = request.getParameter("role");
        System.out.println(title+author+"лоывраыф");
        User newUser = new User(title, author, price, role);
        db.insert(newUser);
        response.sendRedirect("NNN");
    }
}