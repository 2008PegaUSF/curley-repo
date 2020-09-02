package dao;

import java.util.ArrayList;

import model.User;

public interface UserDao {
	public ArrayList<User> getAllUsers();
	public User getUserByUsername(String username);

	public int createNewUser(String username, String password);
	public void deleteUserByUserId(int userId);

}
