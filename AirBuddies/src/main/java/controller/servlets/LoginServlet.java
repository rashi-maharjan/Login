package controller.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int SESSION_TIMEOUT = 30 * 60; // 30 minutes in seconds

	 DBController dbController = new DBController();

	    public LoginServlet() {
	        super();
	    }

	    //doGet method is empty
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    }

	    
	    //doPost method
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        // Validate input fields
	        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
	            request.setAttribute("errorMessage", "Please enter your email and password.");
	            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	            return;
	        }

	        int loginResult = dbController.getUserLoginInfo(email, password);

	        if (loginResult == 1) {
	            // Login successful
	            HttpSession session = request.getSession();
	            session.setMaxInactiveInterval(SESSION_TIMEOUT);

	            if (isAdmin(email)) {
	                // User is an admin
	                session.setAttribute("email", email);
	            

	                // Store user information in cookies
	                Cookie usernameCookie = new Cookie("email", email);
	                response.addCookie(usernameCookie);
	            
	                request.getRequestDispatcher("/pages/admin.jsp").forward(request, response);
	            } else {
	                // Regular user
	                session.setAttribute("isAdmin", false);
	                request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
	            }
	        } else if (loginResult == 0) {
	            // Login failed
	            request.setAttribute("errorMessage", "Invalid email or password.");
	            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	        } else {
	            // Error occurred
	            request.setAttribute("errorMessage", "User not registered. Please register first!");
	            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	        }
	    }

	    private boolean isAdmin(String email) {
	        return email.equals("jaz@admin.com"); //admin's email
	    }
}
