package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import control.MenuControls;
import exceptions.InvalidAmountException;
import exceptions.OverdraftException;
import model.Account;
import model.Customer;
import model.Transaction;
import model.User;

public class CustomerViews {

	private MenuControls menucontroller;
	private Scanner cons;
	private Logger log;
	private CommonViews commonview;

	// these are here to simplify checks throughout the program
	private Customer cust = null;
	private User user = null;
	// private Account currentAccount = null;
	private ArrayList<Account> accountList = new ArrayList<Account>(); // this is just in case a customer has more than
																		// one account

	private String userPrefix = "";

	public CustomerViews(CommonViews commonview, MenuControls menucontroller, Scanner console, Logger log) {
		this.menucontroller = menucontroller;
		this.cons = console;
		this.log = log;
		this.commonview = commonview;
	}

	public void enterCustomerMode() {
		System.out.println("Entering customer mode...");
		menucontroller.setAdminLoggedIn(false); // we set this to false on logout of admin, but just to be safe we do it
												// again here.
		showCustomerLogin();
	}

	// validate customer credentials for login
	private void showCustomerLogin() {
		System.out.println("You are logging in as a customer.");
		int choice = 0;
		while (choice < 1 || choice > 3) {
			try {
				System.out.println(
						"Please enter 1 to sign in with an existing account, or enter 2 to sign up for a new account. Press 3 to go back to the main menu.");
				choice = Integer.parseInt(cons.nextLine().trim());

				switch (choice) {
				case 1:
					loginCustomer();
					break;
				case 2:
					commonview.makeNewCustomerAccount();
					break;
				case 3:
					// we do this here so that one day the gc might actually clean up what can be
					// cleaned.
					// currentAccount = null;
					cust = null;
					user = null;
					accountList.clear();
					accountList = null;
					userPrefix = "";
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection");
			}
		}

	}

	// log in customer -- first gets the user credentials, then fills in the
	// customer by the userid supplied
	private void loginCustomer() {

		System.out.println("Checking for existing customer accounts...");

		if (menucontroller.getAllCustomers().isEmpty()) {
			System.out.println("Congratulations, you are our first customer.  Please create a new user account.");
			commonview.makeNewCustomerAccount();
			System.out.println("Successfully created new account.  Please log in from the main customer menu.");
			return;
		}

		System.out.println("Found customer information.");

		boolean validEntry = false;

		while (!validEntry) {
			String username;
			System.out.println("Please enter your username");
			username = cons.nextLine();
			user = menucontroller.getUserByUsername(username);

			if (user == null) {
				System.out.println("Invalid account name.");
				continue;
			}
			System.out.println("Please enter your password");
			String pass = cons.nextLine();
			if (pass.equals(user.getPassword()))
				validEntry = true;

			// get our customer based on matching userid
			cust = menucontroller.getCustomerByUserId(user.getUserId());
			setUserPrefix(user);
		}
		System.out.println("Authorized.");

		showCustomerBankAccountOptionsMenu(user, cust);
	}

	// show customer account action menu
	private void showCustomerBankAccountOptionsMenu(User user, Customer cust) {

		boolean done = false;
		while (!done) {
			accountList = menucontroller.getAccountsByCustomerId(cust.getCustomerId());
			if (accountList.isEmpty()) {
				System.out.println("Welcome, " + cust.getFirstName());
				System.out
						.println("You have no accounts yet.  Would you like to create one? Enter 1 for yes, 2 for no.");
				System.out.print("[" + user.getUsername() + "]" + menucontroller.getConsolePrefix());
				int choice = Integer.parseInt(cons.nextLine().trim());
				while (choice != 1 || choice != 2) {
					switch (choice) {
					case 1: {
						createBankAccount(user, cust);
					}
					case 2: {
						done = true;
						System.out.println("Going back to main menu...");
						return;
					}
					}
				}
			} else {
				done = true;
				System.out.println("Welcome back, " + cust.getFirstName() + ".");
				System.out.println("Account information: ");
				printAccountList(accountList);

			}
			/*
			 * if (currentAccount == null) {
			 * System.out.println("Account creation failed. Returning to main menu.");
			 * return; }
			 */
		}
		showAccountActionMenu(cust, user);
	}

	// print all accounts
	private void printAccountList(ArrayList<Account> list) {
		int count = 1;
		for (Account a : list) {
			System.out.println((count++) + ". " + a.toString());
		}
	}

	// show main menu for customer
	private void showAccountActionMenu(Customer c, User u) {

		boolean isDone = false;

		while (!isDone) {

			System.out.println("Please choose from one of the following options: ");

			System.out.println("Enter 1 to view accounts and balances.");
			System.out.println("Enter 2 to delete an account with zero balance.");
			System.out.println("Enter 3 to create an account.");
			System.out.println("Enter 4 to deposit funds into an account.");
			System.out.println("Enter 5 to withdraw funds from an account.");
			System.out.println("Enter 6 to sign out.");
			try {
				int choice = Integer.parseInt(cons.nextLine().trim());
				switch (choice) {
				case 1:
					viewAllAccounts(c);
					break;
				case 2:
					deleteAccounts(c, u);
					break;
				case 3:
					createBankAccount(u, c);
					break;
				case 4:
					doDeposit(c, u);
					break;
				case 5:
					doWithdrawal(c, u);
					break;

				case 6:
					isDone = true;
					break;
				default:
					System.out.println("Invalid selection.");
				}
			} catch (NumberFormatException e) {

			}
		}
	}

	// just shows all bank accounts owned by customer c
	private void viewAllAccounts(Customer c) {
		System.out.println("Here are all of your accounts: ");
		printAccountList(menucontroller.getAccountsByCustomerId(c.getCustomerId()));
	}

	// delete a user account
	private void deleteAccounts(Customer c, User u) {
		// we want to get a list of any accounts with zero balance
		accountList = menucontroller.getAllAccountsByBalance(c.getCustomerId(), 0.0);
		if (accountList.isEmpty()) {
			System.out.println("You have no empty accounts.  Returning to previous menu...");
			return;
		} else {
			boolean done = false;
			while (!done || !accountList.isEmpty()) {
				try {
					System.out.println("Please choose an empty account (or enter 0 to go back to the previous menu: ");

					printAccountList(accountList);
					int totalAccounts = accountList.size();
					int accountToDelete = Integer.parseInt(cons.nextLine().trim());
					if (accountToDelete == 0) {
						System.out.println("Returning to previous menu...");
						done = true;
						return;
					}
					if (accountToDelete < 0 || accountToDelete > totalAccounts)
						throw new NumberFormatException();
					else {
						System.out.println( // warn the user that this action cannot be undone
								"***************************** W A R N I N G ! **********************************");
						System.out.println(
								"************ Once you delete an account, it cannot be recovered. ***************");
						System.out.println(
								"If you are sure, please enter your username and password to confirm this action.");
						System.out // make them confirm with user and pass authentication
								.print("[" + u.getUsername() + "]" + menucontroller.getConsolePrefix() + " Username: ");
						String username = cons.nextLine().trim();
						System.out
								.print("[" + u.getUsername() + "]" + menucontroller.getConsolePrefix() + " Password: ");
						String password = cons.nextLine().trim();
						if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
							System.out.println("Confirmed.  Deleting account...");
							menucontroller
									.deleteAccountByAccountId(accountList.get(accountToDelete - 1).getAccountId());
							// also remove it from local list too
							accountList.remove(accountToDelete - 1);
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid selection");
				}

			}
		}
	}

	private void createBankAccount(User user, Customer cust) {
		System.out.println("Creating new account for username " + user.getUsername());
		menucontroller.createNewBankAccount(cust.getCustomerId());
		// currentAccount = menucontroller.getAccountById(accountId);
	}

	private Account selectAnAccount(Customer c) {
		System.out.println("Please select an account from the following: ");
		printAccountList(accountList);

		int choice = 0;
		boolean done = false;
		while (!done) {
			try {
				System.out.print(userPrefix);
				choice = Integer.parseInt(cons.nextLine().trim());
				if (choice < 1 || choice > accountList.size()) {
					System.out.println("Invalid selection");
					continue;
				}
				else {
					done=true;
				}
			} catch (NumberFormatException e) {
				done = false;
				System.out.println("Invalid input");
			}
		}
		return accountList.get(choice - 1);
	}

	// withdraws funds from an account

	private void doWithdrawal(Customer c, User user) {
		accountList = menucontroller.getAccountsByCustomerId(c.getCustomerId());
		Account a;
		if (accountList.size() > 1) {
			System.out.println("Found more than one account for " + c.getFirstName());
			System.out.println("Please choose an account.");
			a = selectAnAccount(c);
		} else {
			a = accountList.get(0);
		}
		boolean valid = false;
		while (!valid) {
			try {
				System.out.println("Please enter the amount you wish to withdraw");
				double amount = Double.parseDouble(cons.nextLine().trim());
				if (amount < 0.01) {
					throw new InvalidAmountException();
				} else if (amount > a.getBalance()) {
					throw new OverdraftException();
				} else {
					valid = true;
					a.setBalance(a.getBalance() - amount);
					double newBalance = menucontroller.updateAccountBalance(a.getAccountId(), a.getBalance());
					System.out.println(
							"Successfully withdrew $" + amount + " from account.  New balance is $" + newBalance);
					log.info(new String("TEST TEST TEST").toString());
					// new Transaction('w', a, a, a.getBalance() + amount, amount,
					// user.getUsername()).toString(), a.getBalance());
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			} catch (OverdraftException e) {
				System.out.println(e.getMessage());
			} catch (InvalidAmountException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// deposits an amount to an Account
	private void doDeposit(Customer c, User user) {
		accountList = menucontroller.getAccountsByCustomerId(c.getCustomerId());
		Account a;
		if (accountList.size() > 1) {
			System.out.println("Found more than one account for " + c.getFirstName());
			System.out.println("Please choose an account.");
			a = selectAnAccount(c);
		} else {
			a = accountList.get(0);
		}
		boolean valid = false;
		while (!valid) {
			try {
				System.out.println("Please enter the amount you wish to deposit");
				double amount = Double.parseDouble(cons.nextLine().trim());
				if (amount < 0.01) {
					throw new InvalidAmountException();

				} else {
					valid = true;
					a.setBalance(a.getBalance() + amount);
					double newBalance = menucontroller.updateAccountBalance(a.getAccountId(), a.getBalance());
					System.out.println(
							"Successfully deposited $" + amount + " into account.  New balance is $" + newBalance);
					log.info(new Transaction('d', a, a, a.getBalance() - amount, amount, user.getUsername()).toString(),
							a.getCustomerId());
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");

			} catch (InvalidAmountException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private void setUserPrefix(User user) {
		userPrefix = "[" + user.getUsername() + "]" + menucontroller.getConsolePrefix();
	}

}
