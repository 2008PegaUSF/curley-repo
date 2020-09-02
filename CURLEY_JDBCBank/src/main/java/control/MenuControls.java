package control;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import daoimpl.AccountDaoImpl;
import daoimpl.CustomerDaoImpl;
import daoimpl.UserDaoImpl;
import daoimpl.ViewAccessDaoImpl;
import model.Account;
import model.Customer;
import model.User;

//this class holds all of our control functionality for our menu system
//functionality is shared between customer and admin
//this is also all of the testable functionality present
//we could go through the trouble of testing all of the DAO functions but it is nicely mirrored in here
//this works well since we wouldn't need to change anything in any place but inside the DAO Implementations
//also any new functionality is stored here and any removed functionality is also here as well in one spot
//instead of having to track each call in each class of menus

public class MenuControls {

	private CustomerDaoImpl customers = new CustomerDaoImpl();
	private AccountDaoImpl accounts = new AccountDaoImpl();
	private UserDaoImpl users = new UserDaoImpl();
	private ViewAccessDaoImpl views = new ViewAccessDaoImpl();
	
	private boolean adminLoggedIn;
	
	private String consolePrefix;

	//removes all traces of a customer - order matters here.
	public void removeCustomer(int customerId, int userId) {
		accounts.deleteAllAccountsByCustomerId(customerId);
		customers.deleteCustomerByCustomerId(customerId);
		users.deleteUserByUserId(userId);
	}
	
	//updates a customer's information
	public String updateCustomerInformation(int customerId, String firstname, String lastname) {
		return customers.updateCustomerInformation(customerId, firstname, lastname);
	}
	
	//returns a view of customer and user info
	public ArrayList<String> getAllUserInfo() {
		return views.getAllUserInfo();
	}
	
	//deletes an account by accountid
	public void deleteAccountByAccountId(int accountId) {
		accounts.deleteAnAccountByAccountId(accountId);
	}
	
	//returns a list of all accounts belonging to a customer by customerId with a specific balance - used for 0 checking
	public ArrayList<Account> getAllAccountsByBalance(int customerId, double balance) {
		return accounts.getAllAccountsByBalance(customerId, balance);
	}
	
	//updates a bank account's balance
	public double updateAccountBalance(int accountId, double amount) {
		return accounts.updateAccountBalance(accountId, amount);
	}
	
	//return an account by accountid
	public Account getAccountById(int accountId) {
		return accounts.getAccountByAccountId(accountId);
	}
	
	//create a new bank account for this customer
	public int createNewBankAccount(int customerId) {
		return accounts.createNewAccount(customerId);
	}
	
	//returns the accountid of a newly created bank account
	public int createNewAccount(int customerId) {
		return accounts.createNewAccount(customerId);
	}
	
	//returns the customerid of a newly created customer
	public int createNewCustomer(String firstname, String lastname, int userId) {
		return customers.createNewCustomer(firstname, lastname, userId);
	}
	
	//returns the userid of a newly created user
	public int createNewUser(String username, String password) {
		return users.createNewUser(username, password);
	}
	
	//ensures a name field has valid alphabetic characters
	public boolean verifyAlphabetics(String...list) {
		for(int i = 0; i < list.length; i++) {
			if(list[i].matches(".*\\d.*") || list[i].isEmpty()) return false;
		}
		
		return true;
	}
	
	//returns a list of all accounts owned by customerId
	public ArrayList<Account> getAccountsByCustomerId(int id) {
		return accounts.getAllAccountsByCustomerId(id);
	}
	
	//returns a customer by username
	public Customer getCustomerByUsername(String username) {
		return customers.getCustomerByUsername(username);
	}
	
	//returns a customer by customerId
	public Customer getCustomerByUserId(int id) {
		return customers.getCustomerByUserId(id);
	}
	
	//ensures a user gets a unique username
	public boolean verifyNameNotTaken(String username) {
		return users.getUserByUsername(username) == null;
	}
	
	//gets a user by username
	public User getUserByUsername(String username) {
		return users.getUserByUsername(username);
	}
	
	//returns a list of all customers
	public ArrayList<Customer> getAllCustomers() {
		return customers.getAllCustomers();
	}
	
	//returns a list of all users
	public ArrayList<User> getAllUsers() {
		return users.getAllUsers();
	}

	//checks that admin is logged in or not
	public boolean isAdminLoggedIn() {
		return adminLoggedIn;
	}

	//sets whether admin is logged in or not
	public void setAdminLoggedIn(boolean adminLoggedIn) {
		this.adminLoggedIn = adminLoggedIn;
	}

	//verifies admin password as passed in from admin.properties
	public boolean verifyAdminPassword(String pass) {
		return pass.equals(getAdminCredentials()[1]);
	}
	
	//verifies admin usrname is correct against admin.properties
	public boolean verifyAdminUsername(String user) {
		return user.equals(getAdminCredentials()[0]);
	}
	
	//used to verify both admin user and password
	public boolean verifyAdminCredentials(String user, String pass) {
		String[] adminCredentials = getAdminCredentials();

		return user.equals(adminCredentials[0]) && pass.equals(adminCredentials[1]);
	}

	//prefixes the console based on customer or admin access
	public String getConsolePrefix() {
		return adminLoggedIn ? "[ADMIN] Admin>> " : "Customer>> ";
	}
	
	//get Admin Credentials - used to return the admin credentials for validation, as seen in MenuDriver
	//use admin.properties to separate admin info from the database
	public String[] getAdminCredentials() {
		String[] adminProperties = new String[2];

		Properties prop = new Properties();
		try {
			prop.load(new FileReader("admin.properties"));
			adminProperties[0] = prop.getProperty("username");
			adminProperties[1] = prop.getProperty("password");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adminProperties;
	}
}
