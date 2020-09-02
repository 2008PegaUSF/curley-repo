package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {

	private static ConnFactory cf = new ConnFactory();
	private ConnFactory() {
		super();
	}
	
	public static synchronized ConnFactory getInstance() {
		if(cf==null) cf = new ConnFactory();
		return cf;
	}
	
	public static Connection getLoggerConnection() {
		return getConnection();
	}
	
	public static Connection getConnection(String... properties) {
		Connection conn = null;
		Properties prop = new Properties();
		try {
			String dbprops;
			if(properties.length == 0) dbprops = "database.properties";
			else dbprops = properties[0];
			prop.load(new FileReader(dbprops));

			conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
			if(dbprops.equals("testdb.properties")) conn.setAutoCommit(false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
}
