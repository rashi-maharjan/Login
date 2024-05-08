package controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.RegisterModel;
import utils.StringUtils;

/** 
 * This method establishes a connection to a MySQL database
 * It loads the MySQL JDBC driver, sets the database URL, username, and password 
 * and returns a Connection object that can be used to execute SQL statements
**/

public class DBController {
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/earbuddies";
		String user = "root";
		String pass = "";
		return DriverManager.getConnection(url, user, pass);
	}
	

/** 
 * This method adds a new user to the database.
 * 
 * Takes a RegisterModel object as input, which contains user details 
   like first name, last name, email, and password
 * Creates a PreparedStatement using a SQL INSERT query defined in StringUtils.INSERT_USER
 * Sets the parameter values in the PreparedStatement using the data from the RegisterModel object
 * Executes the INSERT query and returns:
  		1 if the insert was successful, 
  		0 if it failed, or 
  		-1 if an exception occurred 
 * @throws SQLException if a database access error occurs.
 * @throws ClassNotFoundException if the JDBC driver class is not found.
**/
	
	public int addUser(RegisterModel registerModel) {
		try (Connection con = getConnection()){
			PreparedStatement st = con.prepareStatement(StringUtils.INSERT_USER);
			
			st.setString(1, registerModel.getFirstName());
			st.setString(2, registerModel.getLastName());
			st.setString(3, registerModel.getEmail());
			st.setString(4, registerModel.getPassword());
			
			int result = st.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
			
		}
	
	
/** 
 * This method checks if the provided email and password match a user in the database
 * It takes an email and password as input parameters
 * Creates a PreparedStatement using a SQL SELECT query defined in StringUtils.GET_LOGIN_USER_INFO
 * Sets the email and password as parameters in the PreparedStatement
 * Executes the SELECT query and retrieves the result set
 * If a user is found, it checks if the retrieved password matches the provided password
 * It returns:
  		1 if the login is successful, 
  		0 if the password is incorrect or the user is not found, 
 		or -1 if an exception occurred 
 * @throws SQLException if a database access error occurs.
 * @throws ClassNotFoundException if the JDBC driver class is not found.
 **/
	public int getUserLoginInfo(String email, String password) {
	    try (Connection con = getConnection()) {
	        PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
	        st.setString(1, email);
	        st.setString(2, password);

	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            // Check if the retrieved password matches the provided password
	            String retrievedPassword = rs.getString("password");
	            if (password.equals(retrievedPassword)) {
	                return 1; // Login successful
	            } else {
	                return 0; // Incorrect password
	            }
	        } else {
	            return 0; // User not found
	        }
	    } catch (ClassNotFoundException | SQLException ex) {
	        ex.printStackTrace();
	        return -1; // Error occurred
	    }
	}
	
}
	

