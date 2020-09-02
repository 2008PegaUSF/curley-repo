package view;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import control.MenuControls;

/**
 * 
 * @author Rob C
 * 
 * This class is just a kickoff point from main.  It shows the main menu then branches out to other views
 * depending on the option chosen
 * 
 * we store an instance of Scanner and MenuControls here which are then passed to the chosen view.
 *
 */

public class MenuDriver {
	private Scanner cons = new Scanner(System.in);
	private Logger log = LogManager.getLogger(MenuDriver.class);

	private MenuControls menucontroller = new MenuControls();
	
	//provide our views depending on what was chosen by user - supplies the controls functionality and the 
	//scanner instance to each user.  the logger is for any major loggable events done in each menu
	private CommonViews commonview = new CommonViews(menucontroller, cons, log);
	private AdminViews adminview = new AdminViews(commonview, menucontroller, cons, log);
	private CustomerViews customerview = new CustomerViews(commonview, menucontroller, cons, log);

	// initial greetings, used for the sake of just showing any major new features

	private void greet() {
		System.out.println("Welcome to the Bank of The Town With No Name\n");
	}

	// startup / main menu when an account exists
	public void showMainMenu() {
		this.greet();
		System.out.println("Please select from one of the following options:");
		boolean done = false;
		int choice = 0;
		while (!done) {
			try {
				System.out.println("Enter 1 for customer actions.");
				System.out.println("Enter 2 for administrator actions.");
				System.out.println("Enter 3 to exit");
				choice = Integer.parseInt(cons.nextLine());
				if (choice < 1 || choice > 3)
					throw new NumberFormatException();
				switch (choice) {
				case 1:
					customerview.enterCustomerMode();
					break;
				case 2:
					adminview.enterAdminMode();
					break;
				case 3:
					done = true;
					System.out.println("Thank you for using this banking software.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection");
			}
		}
	}



	// withdraw an amount from an account

	// transfer balances between accounts
/*	public double doTransfer(User u, Account a) {
		if (debug)
			return a.getBalance();
		boolean valid = false;
		while (!valid) {
			try {
				System.out.println("Please enter the username of the account you wish to deposit to");
				String username = cons.nextLine();

				if (username.isEmpty()) {
					System.out.println("Please enter a valid username");
					continue;
				}

				Customer c = findACustomer(username);
				if (c != null) {
					Account ac = findAnAccount(c);
					System.out.println("Please enter the amount you wish to transfer");
					double amount = Double.parseDouble(cons.nextLine());

					if (amount < 0.01) {
						System.out.println("Cannot withdraw less than one cent.");
					} else if (amount > a.getBalance()) {
						System.out.println("Cannot overdraw an account.");
					} else {
						ac.setBalance(ac.getBalance() + amount);
						a.setBalance(a.getBalance() - amount);
						System.out.println("Successfully transfered $" + amount + " to " + username);
						log.info(new Transaction('t', a, ac, a.getBalance() + amount, amount, u).toString());
						valid = true;
					}

				} else {
					System.out.println("Please enter a valid username");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
		return a.getBalance();
	}*/

	// for a customer, allows to produce a new account
	// yes, we do allow for a customer to have multiple accounts
/*	public Account createNewBankAccount(Customer c) {
		if (debug)
			return new Account(c);
		boolean valid = false;
		while (!valid) {
			System.out.println("Would you like to create a single owner account or a joint account?");
			System.out.println("Enter 1 for single account, enter 2 for joint account, or 3 to quit.");
			try {
				int choice = Integer.parseInt(cons.nextLine());
				switch (choice) {
				case 1:
					valid = true;
					System.out.println("Creating new bank account...");
					return new Account(c);

				case 2:
					if (Customer.customerList.size() < 2) {
						System.out.println("Cannot create a joint account: Not enough customers.");
						return null;
					}
					valid = true;
					System.out.println("Creating new joint bank account...");
					return createJointAccount(c);
				case 3:
					System.out.println("Going back to menu...");
					valid = true;
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection");
			}

		}
		Account a = new Account(c);
		files.writeAccountFile();
		return a;
	}

	// used to find a bank account based on a username
	public Account findAnAccount(Customer u) {
		for (int i = 0; i < Account.pendingAccounts.size(); i++) {
			if (Account.pendingAccounts.get(i).getMember().getUsername().contentEquals(u.getUsername()))
				return Account.pendingAccounts.get(i);
		}
		for (int i = 0; i < Account.activeAccounts.size(); i++) {
			if (Account.activeAccounts.get(i).getMember().getUsername().contentEquals(u.getUsername()))
				return Account.activeAccounts.get(i);
		}
		return null;
	}

	// walks the list of customers and returns null if nothing is found for account
	// access
	public Customer findACustomer(String username) {
		for (int i = 0; i < Customer.customerList.size(); i++) {
			if (Customer.customerList.get(i).getUsername().equals(username))
				return Customer.customerList.get(i);
		}
		return null;
	}



	// create a new customer joint account
	public JointAccount createJointAccount(Customer c1) {
		if (debug)
			return new JointAccount(placeHolderCustomer, placeHolderCustomer);
		Customer c2 = null;
		int attemptsRemaining = 3;
		while (attemptsRemaining > 0) {
			System.out.println("Please enter the username of the second account holder (" + attemptsRemaining
					+ " attempt(s) remaining)");
			String user = cons.nextLine();
			try {
				c2 = findACustomer(user);
				if (c2 == null) {
					attemptsRemaining--;
				} else {
					attemptsRemaining = 0;
					break;
				}
			} catch (NullPointerException e) {
				System.out.println("Invalid account entered.");
				attemptsRemaining--;
			}
		}
		try {
			if (c2 == null) {
				System.out.println("Failed to create joint account.");
				return null;
			}
		} catch (NullPointerException e) {
			System.out.println("Failed to create joint account.");
		}
		return new JointAccount(c1, c2);
	}

	// used for defining a new customer
	public String[] getAccountCredentials(User u) {
		if (debug)
			return new String[] { "Test", "Test", "Pass", "Pass" };

		String accountVerbage = "";
		if (verifyCustomer(u))
			accountVerbage = "account holder";
		if (verifyEmployee(u))
			accountVerbage = "employee";
		if (verifyAdministrator(u))
			accountVerbage = "administrator";
		String[] credentials = new String[4];
		boolean choiceValid = false;
		while (!choiceValid) {
			System.out.println("Please enter the First Name of " + accountVerbage);
			credentials[0] = cons.nextLine();
			System.out.println("Please enter the Last Name of " + accountVerbage);
			credentials[1] = cons.nextLine();
			if (credentials[0].contains("[^a-zA-Z]") || credentials[0].isEmpty() || credentials[1].isEmpty()
					|| credentials[1].contains("[^a-zA-Z]")) {
				System.out.println("Please enter a valid name");
				continue;
			}
			choiceValid = true;
		}
		choiceValid = false;
		while (!choiceValid) {
			choiceValid = true;
			System.out.println("Please enter a username");
			credentials[2] = cons.nextLine();
			if (verifyAdministrator(u)) {
				if (!credentials[2].contains("admin")) {
					System.out.println("Username must contain admin to be an administrator");
					choiceValid = false;
					continue;
				}
			}
			if (verifyCustomer(u)) {
				for (int i = 0; i < Customer.customerList.size(); i++) {
					if (credentials[2].equals(Customer.customerList.get(i).getUsername())) {
						System.out.println("Username " + credentials[2] + " is already in use.");
						choiceValid = false;
						break;
					}
				}
			}
			if (verifyEmployee(u) || verifyAdministrator(u)) {
				for (int i = 0; i < Employee.employeeList.size(); i++) {
					if (credentials[2].equals(Employee.employeeList.get(i).getUsername())) {
						System.out.println("Username " + credentials[2] + " is already in use.");
						choiceValid = false;
						break;
					}
				}
			}
			System.out.println("Username accepted: " + credentials[2]);
		}
		System.out.println("Please enter the account password: ");
		credentials[3] = cons.nextLine();
		String checkPass = "";
		while (!checkPass.equals(credentials[3])) {
			System.out.println("Please verify your password entered: ");
			checkPass = cons.nextLine();
		}
		return credentials;
	}



	// creates a new employee account
/*	public void createEmployeeAccount() {
		Employee emp = placeHolderEmployee;
		String[] credentials = getAccountCredentials(emp);

		emp = new Employee(credentials[0], credentials[1], credentials[2], credentials[3]);

		files.writeEmployeeFile();
	}*/

	// shows a menu for account actions. context sensitive to user supplied by login

	// shows all pending accounts, accessed by admin or employee
/*	private void viewPendingAccounts(User user) {
		if (!verifyAdministrator(user) && !verifyEmployee(user)) {
			System.out.println("Invalid user credentials.  How'd you get here?");
			return;
		}

		for (Account a : Account.pendingAccounts) {
			System.out.println("First name: " + a.getMember().getFirstName() + ", Last name: "
					+ a.getMember().getLastName() + ", username " + a.getMember().getUsername());
		}

		boolean isDone = false;
		while (!isDone) {
			System.out.println("Please enter the user name of the account you want to approve or deny ");
			String username = cons.nextLine();

			if (username.isEmpty() || findACustomer(username) == null) {
				System.out.println("The user name entered doesn't belong to a customer");
				continue;
			}

			System.out.println("Enter A to approve the pending account");
			System.out.println("Enter D to deny the pending account");
			System.out.println("Enter Q to to exit out of this menu");
			char choice = cons.nextLine().toLowerCase().charAt(0);

			Customer c = findACustomer(username);
			Account a = findAnAccount(c);

			if (a != null) {
				switch (choice) {
				case 'a':
					// approve
					a.promoteAccountToActive(a);
					break;
				case 'd':
					a.removePendingAccount(a);
					break;
				case 'q':
					isDone = true;
					break;
				default:
					System.out.println("Invalid selection.");
				}
			} else {
				System.out.println(
						"The cutomer: " + c.getFirstName() + " " + c.getLastName() + " does not have an account");
				continue;
			}
			System.out.println("Do you want to continue approving/ denying accounts enter (y/n)");

			if (a.getPendingAccounts().size() == 0 || cons.nextLine().toLowerCase().charAt(0) == 'n') {
				System.out.println("Exiting");
				break;
			}
		}
	}*/

	// cancel account option for admin use
/*	public boolean doAdminCancel(User user, Account a) {
		if (debug)
			return debug;

		if (!verifyAdministrator(user)) {
			System.out.println("Invalid user credentials.  How'd you get here?");
			return false;
		}

		int attemptsRemaining = 3;

		while (attemptsRemaining > 0) {
			System.out.println("Please enter the username of the account you wish to cancel " + attemptsRemaining
					+ " attempt(s) remaining)");
			String username = cons.nextLine();
			if (username.isEmpty()) {
				System.out.println("Not a valid input");
				attemptsRemaining--;
				continue;
			}
			Customer c = findACustomer(username);
			if (c == null) {
				System.out.println("No customer with username " + username);
				attemptsRemaining--;
				continue;
			} else {
				a = findAnAccount(c);
				if (a.isPendingApproval()) {
					System.out.println("Removing inactive account.");
					Account.pendingAccounts.remove(a);
				} else {
					if (a.getBalance() != 0) {
						System.out.println("Cannot remove an active account that has a balance remaining.");
						return false;
					}
					System.out.println("Cancelling active account with no balance.");
					Account.activeAccounts.remove(a);
				}
				a = null;
				return true;
			}
		}

		return false;
	}*/

/*	// shows all customer accounts
	private void showAllAccounts(User user) {
		Account a = new Account();
		if (!verifyEmployee(user)) {
			System.out.println("Invalid user credentials.  How'd you get here?");
			return;
		}
		for (Account ac : a.getActiveAccounts()) {
			System.out.println("Active: " + ac.toString());
		}
		for (Account ac : a.getPendingAccounts()) {
			System.out.println("Pending: " + ac.toString());
		}
	}

	private void doAdminAccountView(User user, Account a) {
		if (!verifyAdministrator(user)) {
			System.out.println("Invalid user credentials.  How'd you get here?");
			return;
		}
		Customer c = a.getMember();
		System.out.println(a.toString()); // Has account First/Last Name AccoutnID Balance
		System.out.println("User Name: " + c.getUsername() + "\nPassword: " + c.getPassword());
		System.out.println("Would you like to change the credentials for this account? 1 for yes, 2 for no");
		while (true) {
			try {
				int choice = Integer.parseInt(cons.nextLine());
				if (choice == 1) {
					System.out.println("Going to account change.");
					String credentials[] = getAccountCredentials(placeHolderCustomer);
					// change the customer account info bound to the account
					a.getMember().setFirstName(credentials[0]);
					a.getMember().setLastName(credentials[1]);
					a.getMember().setUsername(credentials[2]);
					a.getMember().setPassword(credentials[3]);

					// just in case, change the customer's info too
					c.setFirstName(credentials[0]);
					c.setLastName(credentials[1]);
					c.setUsername(credentials[2]);
					c.setPassword(credentials[3]);

					System.out.println("Success! New customer info: " + a.getMember().toString());
					choice = 2;
				}
				if (choice == 2)
					break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection, try again");
			}
		}
	}*/

/*	// employee login method
	public void doEmployeeLogin() {
		User emp = placeHolderEmployee;
		System.out.println("Checking employee accounts...");

		if (checkForAnyEmployee() && Employee.employeeList.size() > 1) {
			System.out.println("Found employee account.");
			try {
				while (true) {
					System.out.println("Please enter your username.");
					String username = cons.nextLine();
					emp = findAnEmployee(username);
					// System.out.println(emp.toString());
					if (!username.equals(emp.getUsername()) || emp == null || username.contains("admin")) {
						System.out.println("Invalid username, please enter a valid employee account name");
						continue;
					}
					System.out.println("Username valid.");
					int count = 3;
					boolean valid = false;
					while (count > 0) {
						System.out.println("Please enter your password");
						String password = cons.nextLine();
						if (password.equals(emp.getPassword())) {
							valid = true;
							break;
						}
						System.out.println("Invalid password, please re-enter (" + count + " attempt(s) remaining)");
						count--;
					}
					if (valid) {
						showAccountActionMenu(emp, null);
						return;
					}
				}
			} catch (NullPointerException e) {
				System.out.println("Couldn't find an employee account by that username.");
			}
		}
		if (Employee.employeeList.isEmpty()) {
			System.out.println("No employee accounts found.  Please create one.");
			createEmployeeAccount();
		}
	}*/

/*	// helper methods to shorten some checks
	public boolean verifyCustomer(User user) {
		return user.getClass().equals(Customer.class);
	}

	// checks user class to verify an admin is accessing something
	public boolean verifyAdministrator(User user) {
		return user.getClass().equals(Administrator.class);
	}

	// ...but for employee
	public boolean verifyEmployee(User user) {
		return user.getClass().equals(Employee.class);
	}

	// just writes all data, saves individual calls
	private void writeData() {

		files.writeAccountFile();
		files.writeEmployeeFile();
		files.writeEmployeeFile();
	}

	public void setDebug(boolean d) {
		debug = d;
	}*/
}
