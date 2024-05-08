package utils;

public class StringUtils {
	// SQL query string for inserting a new user into the register_table
	public static final String INSERT_USER = "INSERT INTO register_table"
			+ "(firstName, lastName, email, password)"
			+ "VALUES (?, ?, ?, ?)";
	
	
	// SQL query string for retrieving user information from register_table based on email and password
	public static final String GET_LOGIN_USER_INFO = "SELECT * from register_table where email = ? AND password = ?";
	
	
	// Constants for request parameter names
	public static final String First_Name = "firstName";
	public static final String Last_Name = "lastName";
	public static final String Email = "email";
	public static final String Password = "password";
	
	
	// Constants for success and error messages
	public static final String SUCCESS_REGISTER_MESSAGE = "Successfully Registered!";
	public static final String ERROR_REGISTER_MESSAGE = "Please enter correct data!";
	public static final String SERVER_ERROR_MESSAGE = "Unexpected server error occured.";
	public static final String SUCCESS_MESSAGE = "successMessage";
	public static final String ERROR_MESSAGE = "errorMessage";
	
	
	// Constants for JSP page paths
	public static final String LOGIN_PAGE = "/pages/login.jsp";
	public static final String REGISTER_PAGE = "/pages/register.jsp";
	
	
	// Constants for servlet URL patterns
	public static final String REGISTER_SERVLET = "/RegisterServlet";
}

