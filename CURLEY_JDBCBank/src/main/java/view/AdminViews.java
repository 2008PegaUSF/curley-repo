package view;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import control.MenuControls;
import daoimpl.NukeDaoImpl;
import model.Account;
import model.Customer;

public class AdminViews {

	private MenuControls menucontroller;
	private Scanner cons;
	private Logger log;
	private CommonViews commonview;
	private String consolePrefix = "";

	public AdminViews(CommonViews commonview, MenuControls menucontroller, Scanner console, Logger log) {
		this.menucontroller = menucontroller;
		this.cons = console;
		this.log = log;
		this.commonview = commonview;
	}

	public void enterAdminMode() {
		System.out.println("Entering adminstrator mode...");
		menucontroller.setAdminLoggedIn(true); // this is set elsewhere but just to be explicit we do it again
		consolePrefix = menucontroller.getConsolePrefix();
		showAdminLogin(); // entering admin mode, go right to the administrator login prompt.
	}

	// used to log in as administrator
	private void showAdminLogin() {

		// verify input. three attempts for the user to login or else go back to main
		// menu.
		try {
			int attempts = 3;
			while (attempts > 0) {
				System.out.println("Please enter administrator username. (" + attempts + " attempt(s) remaining.)");
				String username = cons.nextLine().trim();

				if (!menucontroller.verifyAdminUsername(username)) {
					System.out.println("Invalid username, try again.");
					attempts--;
					continue;
				}
				System.out.println("Username accepted.");

				boolean valid = false;
				while (attempts > 0) {
					System.out.println("Please enter administrator password");
					String password = cons.nextLine().trim();
					if (menucontroller.verifyAdminPassword(password)) {
						valid = true;
						break;
					}
					System.out.println("Invalid password, please re-enter (" + attempts + " attempt(s) remaining)");
					attempts--;
				}
				if (valid) {
					menucontroller.setAdminLoggedIn(true);
					showAdminActions();
					return;
				} else {
					System.out.println("Failed to log in.  Returning to menu...");
					return;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Couldn't find that account name.");
		}

	}
	
	//shows the main administrator menu
	private void showAdminActions() {
		if (!menucontroller.isAdminLoggedIn()) {
			System.out.println("Admin must be logged in to see this page.");
			return;
		}
		System.out.println("Please select from one of the following options:");
		boolean done = false;
		int choice = 0;
		while (!done) {
			try {
				System.out.println("Enter 1 view users.");
				System.out.println("Enter 2 create users.");
				System.out.println("Enter 3 assign bank accounts to users.");
				System.out.println("Enter 4 update users.");
				System.out.println("Enter 5 for delete operations.");
				System.out.println("Enter 6 to log out and go back to the main menu");
				System.out.print(consolePrefix);
				choice = Integer.parseInt(cons.nextLine().trim());
				if (choice < 1 || choice > 6)
					throw new NumberFormatException();
				switch (choice) {
				case 1:
					doAdminViewUsers();
					break;
				case 2:
					System.out.println("Going to customer account creation...");
					doAdminCreateUsers();
					break;
				case 3:
					System.out.println("Going to customer bank account assignment menu...");
					showAdminAccountAssignmentMenu();
					break;
				case 4:
					System.out.println("Going to customer update menu...");
					showAdminUpdateCustomerInformationMenu();
					break;
				case 5:
					showAdminDeleteMenu();
					break;
				case 6:
					menucontroller.setAdminLoggedIn(false); // our high-tech 'logout' functionality :D
					done = true;
					System.out.println("Going back to main menu.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection");
			}
		}
	}

	//used to assign bank accounts to customer accounts
	private void showAdminAccountAssignmentMenu() {
		doAdminViewUsers();
		ArrayList<Customer> customerList = menucontroller.getAllCustomers();

		int choice = -1;
		try {
			boolean done = false;
			while (!done) {
				System.out.println("Please choose an customer to add a bank account to. Enter 0 to quit.");
				System.out.print(consolePrefix);
				choice = Integer.parseInt(cons.nextLine().trim());
				if (choice == 0) {
					System.out.println("Returning to menu...");
					done = true;
					break;
				}
				if (choice < 0 || choice > customerList.size()) {
					throw new NumberFormatException();
				} else {
					ArrayList<Account> accountList = menucontroller.getAccountsByCustomerId(choice - 1);
					if (!accountList.isEmpty()) {
						System.out.println("Customer account ID " + customerList.get(choice - 1).getCustomerId()
								+ " already has a bank account associated with it. Add another? 1 for yes, 2 for no.");
						int selection = 0;
						while (selection != 1 || selection != 2) {
							System.out.print(consolePrefix);
							selection = Integer.parseInt(cons.nextLine().trim());
							if (selection == 1) {
								System.out.println("Adding new bank account associated with customer ID "
										+ customerList.get(choice - 1).getCustomerId());
								menucontroller.createNewAccount(customerList.get(choice - 1).getCustomerId());
							}
							if (selection == 2) {
								System.out.println("Going back to customer selection...");
								break;
							}
						}
					} else {
						System.out.println("No bank accounts associated with this customer id.  Adding one...");
						menucontroller.createNewAccount(customerList.get(choice - 1).getCustomerId());
					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid selection.");
		}
	}

	//print out all customer accounts and user info from a view
	private void doAdminViewUsers() {
		System.out.println("Here is all of the user information ordered by customer information then user credentials");
		printUserInfoList(menucontroller.getAllUserInfo());
	}

	//do common action of creating a new customer account
	private void doAdminCreateUsers() {
		commonview.makeNewCustomerAccount();
	}

	//menu to update customer account information (names only)
	private void showAdminUpdateCustomerInformationMenu() {
		doAdminViewUsers();

		//check if anything is returned, if not then exit since no data to act on
		ArrayList<Customer> customerList = menucontroller.getAllCustomers();
		if (customerList.isEmpty()) {
			System.out.println("No users to act upon.  Returning to menu.");
		}
		int choice = -1;
		try {
			boolean done = false;
			while (!done) {
				System.out.println( //give a choice from the list
						"Please choose a customer from the above list that you wish to update the personal information of. Enter 0 to quit.");
				System.out.print(consolePrefix);
				choice = Integer.parseInt(cons.nextLine().trim());
				if (choice == 0) {
					System.out.println("Returning to menu...");
					done = true;
					break;
				}
				if (choice < 0 || choice > customerList.size()) {
					throw new NumberFormatException();
				} else {
					String firstname = "";
					String lastname = "";
					System.out.println("Please enter the new first name of the customer record");
					System.out.print(consolePrefix);
					firstname = cons.nextLine().trim();
					System.out.println("Please enter the new last name of the customer record");
					System.out.print(consolePrefix);
					lastname = cons.nextLine().trim();

					if (menucontroller.verifyAlphabetics(firstname, lastname)) {
						System.out.println("Valid inputs received, updating customer record...");
						System.out.println("New Customer information is:\n" + menucontroller.updateCustomerInformation(
								customerList.get(choice - 1).getCustomerId(), firstname, lastname));

					} else {
						System.out.println("Invalid inputs received, going back to customer choice.");
					}
				}
			}

		} catch (NumberFormatException e) {
			System.out.println("Invalid selection.");
		}
	}

	//this is the menu where everything gets destroyed
	private boolean showAdminNukeMenu() {
		//warn the user
		System.out.println("DANGER: CHOOSING TO PROCEED WILL REMOVE ALL DATA FROM THE DATABASE.");
		String choice = "";
		//warn them again
		System.out.println("ENTER 'YES' TO PROCEED, ENTER 'NO' TO RETURN TO PREVIOUS MENU.");
		System.out.print(consolePrefix);
		choice = cons.nextLine().trim();
		if (choice.equals("YES")) {
			//give them one last chance to consider their choice
			System.out.println("IF YOU ARE SURE, PLEASE TYPE \"I UNDERSTAND THE GRAVITY OF MY CHOICE\".");
			System.out.print(consolePrefix);
			choice = cons.nextLine().trim();
			//they did it
			if (choice.equals("I UNDERSTAND THE GRAVITY OF MY CHOICE")) {
				NukeDaoImpl nuke = new NukeDaoImpl();
				nuke.nukeAllTables();
				System.out.println("ALL DATA REMOVED");
				return true;
			}
		}
		return false;
	}

	//show account delete menu
	private void showAdminAccountDeleteMenu() {

		//show all available users
		doAdminViewUsers();
		ArrayList<Customer> customerList = menucontroller.getAllCustomers();

		int choice = -1;
		try {
			boolean done = false;
			while (!done && !customerList.isEmpty()) {
				System.out.println("Please choose a user to remove from the above list. Press 0 to quit.");
				System.out.print(consolePrefix);
				choice = Integer.parseInt(cons.nextLine().trim());
				if (choice == 0) {
					System.out.println("Returning to menu...");
					done = true;
					break;
				}
				if (choice < 0 || choice > customerList.size()) {
					throw new NumberFormatException();
				} else {
					String confirmation = ""; //next lines confirm user is sure
					System.out.println("Are you sure you wish to delete customer entry: \n" + customerList.get(choice-1).toString() + "?");
					System.out.println("This will remove all associated accounts, the customer entry and the associated username and password.");
					System.out.println("Type \"YES\" to confirm this action.");
					System.out.print(consolePrefix);
					confirmation = cons.nextLine().trim();
					if (confirmation.equals("YES")) {
						System.out.println("Deleting customer record...");
						int customerId = customerList.get(choice-1).getCustomerId();
						int userId = customerList.get(choice-1).getUserId();
						//remove it from current list so we don't keep hammering remote
						customerList.remove(choice-1);
						//now eradicate all records associated with this customer!
						menucontroller.removeCustomer(customerId, userId);
						System.out.println("Customer deleted.");

					} else {
						System.out.println("Going back to customer choice.");
					}
				}
			}

		} catch (NumberFormatException e) {
			System.out.println("Invalid selection.");
		}
	}

	//shows the delete options menu
	private void showAdminDeleteMenu() {
		ArrayList<Customer> customerList = menucontroller.getAllCustomers();
		if (customerList.isEmpty()) {
			System.out.println("No users to act upon.  Returning to menu.");
		}
		//warn the user
		System.out.println("WARNING: ACTIONS DONE IN THIS MENU CANNOT BE UNDONE.");
		boolean done = false;
		while (!done) {
			try {
				System.out.println( // give options
						"Enter 1 to delete individual accounts.  Enter 2 to completely eradicate all data.  Press 3 to go back to the menu.");
				int choice = 0;
				while (choice < 1 || choice > 3) {
					System.out.print(consolePrefix);
					choice = Integer.parseInt(cons.nextLine().trim());
					switch (choice) {
					case 1:
						showAdminAccountDeleteMenu();
						break;
					case 2:
						done = showAdminNukeMenu(); // we check boolean, if nuked then we just return to the prior menu
													// since nothing is left
						break;
					case 3:
						System.out.println("Returning to menu...");
						done = true;
						break;
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input.");
			}
		}
		doAdminViewUsers();
	}

	// prints a list of strings, used for view all account information
	private void printUserInfoList(ArrayList<String> list) {
		int count = 1;
		for (String a : list) {
			System.out.println((count++) + ". " + a);
		}
	}
}
