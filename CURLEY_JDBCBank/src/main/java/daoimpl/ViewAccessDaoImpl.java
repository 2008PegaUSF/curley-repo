package daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ViewAccessDao;
import util.ConnFactory;

public class ViewAccessDaoImpl implements ViewAccessDao {
	public static ConnFactory cf = ConnFactory.getInstance();
	private String properties;
	
	public ViewAccessDaoImpl(String... props) {
		if(props.length==0) properties = "database.properties";
		else properties = props[0];
	}
	@Override
	public ArrayList<String> getAllUserInfo() {
		ArrayList<String> userView = new ArrayList<String>();
		try {
			Connection conn = cf.getConnection(properties);

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from userInformation");
			while (rs.next()) {
				String view = String.format(
						"CustomerID: %d First Name: %s Last Name: %s User ID: %d Username: %s Password: %s",
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
				userView.add(view);
			}

		} catch (SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}

		return userView;
	}

	//returns a user's info by customer id
	public String getUserInfoByCustomerId(int customerId) {
		String userinfo = "";
		try {
			Connection conn = cf.getConnection(properties);

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from users");
			while (rs.next()) {
				userinfo = String.format(
						"CustomerID: %d First Name: %s Last Name: %s User ID: %d Username: %s Password: %s",
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
			}

		} catch (SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}

		return userinfo;
	}
}
