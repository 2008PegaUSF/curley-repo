package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.AccountDao;
import model.Account;
import util.ConnFactory;

public class AccountDaoImpl implements AccountDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	private String properties;
	
	public AccountDaoImpl(String... props) {
		if(props.length==0) properties = "database.properties";
		else properties = props[0];
	}
	
	@Override	//matches all accounts to a customer id and returns them as a list
	public ArrayList<Account> getAllAccountsByCustomerId(int id) {
		ArrayList<Account> accountList = new ArrayList<Account>();
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "select * from accounts where customerid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accountList.add(new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3)));
			}
			return accountList;
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return accountList;
	}
	
	public Account getAccountByAccountId(int accountId) {
		Account a = null;
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "select * from accounts where accountid = ?";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,accountId);
			
			//fire it off
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				a = new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3));
			}
			
			if(a==null) return null;
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return a;
	}

	@Override
	public int createNewAccount(int customerId) {
		int newId = -1;
		try {
			Account a = null;
			Connection conn = cf.getConnection(properties);
			String sql = "insert into accounts (customerid) values (?)";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,customerId);
			
			//fire it off
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				a = new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3));
			}
			
			if(a==null) return -1;
			newId = a.getAccountId();
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return newId;		
	}

	
	@Override
	public double updateAccountBalance(int accountId, double amount) {
		double balance = 0.0;
		try {
			Account a = null;
			Connection conn = cf.getConnection(properties);
			String sql = "update accounts set balance = ? where accountid = ?";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1,amount);
			ps.setInt(2,accountId);
			
			//fire it off
			ps.executeUpdate();
			
			//return the new balance
			a = getAccountByAccountId(accountId);
			
			if(a==null) return -1;
			balance = a.getBalance();
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return balance;			
	}

	@Override
	public ArrayList<Account> getAllAccountsByBalance(int customerId, double balance) {
		ArrayList<Account> accountList = new ArrayList<Account>();
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "select * from accounts where customerid = ? and balance = ?";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,customerId);
			ps.setDouble(2, balance);
			
			//fire it off
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accountList.add(new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3)));
			}
			
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return accountList;	
	}

	@Override
	public void deleteAnAccountByAccountId(int accountId) {
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "delete from accounts where accountid = ?";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,accountId);
			
			ps.executeUpdate();
			System.out.println("Successfully deleted account with id of " + accountId);
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}

	}

	@Override
	public void deleteAllAccountsByCustomerId(int customerId) {

		try {
			Connection conn = cf.getConnection(properties);
			String sql = "delete from accounts where customerid = ?";

			//prepare statement for firing
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,customerId);
			
			//fire it off
			ps.executeQuery();

		} catch(SQLException e) {
			System.out.println("RECORDS DELETED: " + e.getMessage());
		}
		
	}

	@Override
	public ArrayList<Account> getAllAccounts() {
		ArrayList<Account> a = new ArrayList<Account>();
		try {
			Connection conn = cf.getConnection(properties);
			String sql = "select * from accounts";

			//prepare statement for firing
			Statement stmt = conn.createStatement();

			
			//fire it off
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				a.add(new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3)));
			}
			
		} catch(SQLException e) {
			System.out.println("COULDN'T CONNECT: " + e.getMessage());
		}
		return a;
	}


}
