package daoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import dao.NukeDao;
import util.ConnFactory;

public class NukeDaoImpl implements NukeDao{
	public static ConnFactory cf = ConnFactory.getInstance();
	private String properties;
	
	public NukeDaoImpl(String... props) {
		if(props.length==0) properties = "database.properties";
		else properties = props[0];
	}
	
	@Override
	public void nukeAllTables() {

		//we were warned, do it.  eliminate all of the records.
		try {
			Connection conn = cf.getConnection(properties);
			System.out.println("Truncating accounts...");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("truncate accounts");
			System.out.println("Accounts truncated.");
			System.out.println("Truncating customers...");
			stmt = conn.createStatement();
			stmt.executeUpdate("truncate customers");
			System.out.println("Customers truncated.");
			System.out.println("Truncating users.");
			stmt = conn.createStatement();
			stmt.executeUpdate("truncate users");
			System.out.println("Users truncated.");
			System.out.println("All tables have been truncated.");

		} catch (SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
	}

}
