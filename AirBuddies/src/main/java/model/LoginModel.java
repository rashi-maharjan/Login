package model;

public class LoginModel {
	private String email;
	private String password;
	
	public LoginModel(String username, String password) {
		this.email = username;
		this.password = password;
	}
	
	public String getUsername() {
		return email;
	}
	public void setUsername(String username) {
		this.email = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
