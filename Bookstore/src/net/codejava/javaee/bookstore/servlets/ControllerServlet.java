package net.codejava.javaee.bookstore.servlets;

import net.codejava.javaee.bookstore.model.User;
import net.codejava.javaee.bookstore.service.UserDAOFactory;
import net.codejava.javaee.bookstore.service.UserService;
import net.codejava.javaee.bookstore.service.UserServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */

@WebServlet("/NNN")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService db;

	public void init() {

        try {
            db = UserServiceImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/confDb.propertiess");
        System.out.println(input);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        try {
            listBook(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	   String sss = request.getParameter("un");
        System.out.println(sss);
        try {
            listBook(request, response);


        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

        private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
                List<User>  listUsers = db.listAll();
            request.setAttribute("listUsers", listUsers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
            dispatcher.forward(request, response);
        }
	}




