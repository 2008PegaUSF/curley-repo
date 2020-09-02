package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.CustomerDao;
import model.Customer;
import model.User;
import util.ConnFactory;

public class CustomerDaoImpl implements CustomerDao {
	public ConnFactory cf = ConnFactory.getInstance();

	private String properties;
	
	public CustomerDaoImpl(String... props) {
		if(props.length==0) properties = "database.properties";
		else properties = props[0];
	}
	
	@Override
	public ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		try {
			Connection conn = cf.getConnection(properties);

			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from customers");
			while(rs.next()) {
				customerList.add(new Customer(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4)));
			}

		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return customerList;
	}

	//convenience method.  yes, it does break the decoupling a bit but allows a customer to be returned by username
	@Override 
	public Customer getCustomerByUsername(String username) {
		try {
			User u = null; //used for verifying a valid username 
			Connection conn = cf.getConnection(properties);
			String sql = "select * from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,username);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			if(u==null) return null;
			return getCustomerByUserId(u.getUserId());
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return null;
	}

	@Override
	public Customer getCustomerByUserId(int id) {
		Customer c=null;
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "select * from customers where userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
			return c;
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return null;
	}

	@Override
	public int createNewCustomer(String firstname, String lastname, int userId) {
		int newId = -1;
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "insert into customers (firstname, lastname, userid) values (?,?, ?)";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,firstname);
			ps.setString(2,lastname);
			ps.setInt(3, userId);
			
			//fire it off
			ps.executeUpdate();
			
			//we return a value here, so we must get the id of the added user
			Customer c = getCustomerByUserId(userId);
			
			if(c==null) return -1;
			newId = c.getCustomerId();
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return newId;
	}

	@Override
	public String updateCustomerInformation(int customerId, String firstname, String lastname) {
		String out = "";
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "update customers set firstname = ?, lastname = ? where customerId = ?";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,firstname);
			ps.setString(2,lastname);
			ps.setInt(3, customerId);
			
			//fire it off
			ps.executeUpdate();
			
			//we return a value here, so we must get the id of the added user
			out = getCustomerByCustomerId(customerId).toString();
			
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return out;
	}

	@Override
	public void deleteCustomerByCustomerId(int customerId) {
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "delete from customers where customerid = ?";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,customerId);
			
			//fire it off
			ps.executeUpdate();
			
			//we return a value here, so we must get the id of the added user
		} catch(SQLException e) {
			System.out.println("RECORD DELETED: " + e.getMessage());
		}
	}

	@Override
	public Customer getCustomerByCustomerId(int customerId) {
		Customer c = null;
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "select * from customers where customerid = ?";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,customerId);
			
			//fire it off
			ResultSet rs = ps.executeQuery();
			
			//we return a value here, so we must get the id of the added user
			while(rs.next()) {
				c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
			if(c==null) return null;
			
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return c;
	}


}
