package tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import control.MenuControls;
import daoimpl.AccountDaoImpl;
import daoimpl.CustomerDaoImpl;
import daoimpl.UserDaoImpl;
import daoimpl.ViewAccessDaoImpl;

class Tests {

	// all of our dao's to test against
	AccountDaoImpl accounts = new AccountDaoImpl("testdb.properties");
	CustomerDaoImpl customers = new CustomerDaoImpl("testdb.properties");
	UserDaoImpl users = new UserDaoImpl("testdb.properties");
	ViewAccessDaoImpl views = new ViewAccessDaoImpl("testdb.properties");

	private static Connection conn;

	// we are testing against MenuController since a lot of functionality lives in
	// there
	MenuControls controller = new MenuControls();

	/*
	 * 
	 */



	/**
	 * Tests to ensure we start with a clean DB state for our DAO tests that follow
	 * we put in test data to ensure manipulations occur
	 */


	@Test
	void testOneUserRecords() {

		Assertions.assertEquals(1, users.getAllUsers().size());
	}

	@Test
	void testOneCustomerRecords() {

		Assertions.assertEquals(1, customers.getAllCustomers().size());
	}

	@Test
	void testOneAccountRecords() {
		Assertions.assertEquals(1, accounts.getAllAccounts().size());
	}

	/***
	 * Tests for UserDaoImpl
	 */

	@Test
	void testGetUserByUsername() {
		Assertions.assertEquals(1, users.getUserByUsername("testuser").getUserId());
	}

	/***
	 * Tests for CustomerDaoImpl
	 */

	@Test
	void testGetCustomerByCustomerId() {
		Assertions.assertEquals("test", customers.getCustomerByCustomerId(1).getFirstName());
	}

	@Test
	void testGetCustomerByUserId() {
		Assertions.assertEquals(1, customers.getCustomerByUserId(1).getCustomerId());
	}

	@Test
	void testGetCustomerByUsername() {
		Assertions.assertEquals(1, customers.getCustomerByUsername("testuser").getCustomerId());
	}


	/***
	 * Tests for AccountDaoImpl
	 */
	@Test
	void testGetAccountByAccountId() {
		Assertions.assertEquals(1, accounts.getAccountByAccountId(1).getCustomerId());
	}

	@Test
	void testGetAllAccountsByBalance() {
		Assertions.assertEquals(1, accounts.getAllAccountsByBalance(1, 0.0).size());
	}

	@Test
	void testGetAllAccountsByCustomerId() {
		Assertions.assertEquals(1, accounts.getAllAccountsByCustomerId(1).size());
	}

	
	/***
	 * Test for MenuControls
	 */

	@Test 
	void testGetAdminCredentials() {
		Assertions.assertArrayEquals(new String[] {"superuser","r00+U$32" }, controller.getAdminCredentials());
	}
	
	@Test
	void testGetAdminConsolePrefix() {
		controller.setAdminLoggedIn(true);
		Assertions.assertEquals("[ADMIN] Admin>> ", controller.getConsolePrefix());
	}
	
	@Test
	void testGetCustomerConsolePrefix() {
		controller.setAdminLoggedIn(false);
		Assertions.assertEquals("Customer>> ", controller.getConsolePrefix());
	}
	
	@Test
	void testIsAdminLoggedIn() {
		Assertions.assertEquals(false, controller.isAdminLoggedIn());
	}

	
	@Test
	void testVerifyAdminCredentials() {
		Assertions.assertEquals(false, controller.verifyAdminCredentials("bad", "pass"));
	}
	@Test
	void verifyAdminPassword() {
		Assertions.assertEquals(true, controller.verifyAdminPassword("r00+U$32"));
	}
	
	@Test
	void testVerifyAdminUsername() {
		Assertions.assertEquals(true, controller.verifyAdminUsername("superuser"));
	}
	
	@Test
	void verifyAlphabetics() {
		Assertions.assertEquals(false, controller.verifyAlphabetics(new String("111")));
	}

	@AfterAll
	public static void cleanup() {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery("rollback");
		} catch (SQLException e) {
			System.out.println("SQL INFO AFTER ROLLBACK / CLEANUP: " + e.getMessage());
		}
	}
}
