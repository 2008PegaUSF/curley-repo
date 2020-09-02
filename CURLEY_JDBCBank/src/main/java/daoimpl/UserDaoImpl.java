package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.UserDao;
import model.Customer;
import model.User;
import util.ConnFactory;

public class UserDaoImpl implements UserDao{
	public static ConnFactory cf = ConnFactory.getInstance();

	private String properties;
	
	public UserDaoImpl(String... props) {
		if(props.length==0) properties = "database.properties";
		else properties = props[0];
	}
	
	@Override
	public User getUserByUsername(String username) {
		User u=null;
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "select * from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			return u;
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		try {
			Connection conn = cf.getConnection(properties);

			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from users");
			while(rs.next()) {
				userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			return userList;
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return userList;
	}


	@Override
	public int createNewUser(String username, String password) {
		int newId = -1;
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "insert into users (username, password) values (?,?)";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,password);
			
			//fire it off
			ps.executeUpdate();
			
			//we return a value here, so we must get the id of the added user
			User u = getUserByUsername(username);
			
			if(u==null) return -1;
			newId = u.getUserId();
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return newId;
	}

	@Override
	public void deleteUserByUserId(int userId) {
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "delete from users where userid = ?";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,userId);
			
			//fire it off
			ps.executeQuery();
			

		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}

		
	}

}
