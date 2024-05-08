package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.RegisterModel;
import utils.StringUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = (StringUtils.REGISTER_SERVLET))
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Instance of DBController for database operations
	private final DBController dbController;

    public RegisterServlet() {
    	// Initialize the dbController instance
    	this.dbController = new DBController();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Placeholder for handling GET requests
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user registration data from the request parameters
		String firstName = request.getParameter(StringUtils.First_Name);
		String lastName = request.getParameter(StringUtils.Last_Name);
		String email = request.getParameter(StringUtils.Email);
		String password = request.getParameter(StringUtils.Password);
		
		
		// Create a RegisterModel instance with the user data
		RegisterModel registerModel = new RegisterModel(firstName, lastName, email, password);
		
		
		// Call the addUser method in DBController to add the user to the database
		int result = dbController.addUser(registerModel);
		
		
		// Handle the result of the database operation
		if (result == 1 ) { 
			// User added successfully
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_REGISTER_MESSAGE);
			response.sendRedirect(request.getContextPath() + StringUtils.LOGIN_PAGE);
		} else if (result == 0) { 
			// User already exists
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_REGISTER_MESSAGE);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
		} else { 
			// Other error
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtils.REGISTER_PAGE).forward(request, response);
		}
		
		
	}

}
