package model;

public class User {
	private String username;
	private String password;
	private int userId;

	public User(int id, String user, String pass) {
		userId = id;
		username = user;
		password = pass;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "User ID: " + userId + " username: " + username + " password: " + password + "\n";
	}

	

}
