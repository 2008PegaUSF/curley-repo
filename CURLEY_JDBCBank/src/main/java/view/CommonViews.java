package view;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import control.MenuControls;
import model.Customer;
import model.User;

public class CommonViews {
	private MenuControls menucontroller;
	private Scanner cons;
	private Logger log;
	
	public CommonViews(MenuControls menucontroller, Scanner cons, Logger log) {
		this.menucontroller = menucontroller;
		this.cons = cons;
		this.log = log;
	}
	
	public void makeNewCustomerAccount() {

		String[] credentials = getAccountCredentials(); // after here, we have everything we need to create a new user
		// and an associated customer account - we have already proven the username
		System.out.println("Creating new user account for username " + credentials[2]);
		int userid = menucontroller.createNewUser(credentials[2], credentials[3]);
		int customerid = menucontroller.createNewCustomer(credentials[0], credentials[1], userid);
		User user = new User(userid, credentials[2], credentials[3]);
		Customer cust = new Customer(customerid, credentials[0], credentials[1], userid);

		System.out.println("Account creation successful.");
		System.out.println("Your user information: ");
		System.out.println("User info: " + user.toString());
		System.out.println("Customer info: " + cust.toString());

	}
	
	private String[] getAccountCredentials() {

		String[] credentials = new String[4];
		boolean choiceValid = false;
		while (!choiceValid) {
			System.out.println("Please enter your first name.");
			credentials[0] = cons.nextLine();
			System.out.println("Please enter your last name.");
			credentials[1] = cons.nextLine();
			// verify name fields don't contain numbers or other strange symbols
			if (!menucontroller.verifyAlphabetics(Arrays.copyOf(credentials, 2))) {
				System.out.println("Please enter a valid name");
				continue;
			}
			choiceValid = true;
		}
		choiceValid = false;
		while (!choiceValid) { // go and go until a valid username is supplied
			choiceValid = true;
			System.out.println("Please enter a username");
			credentials[2] = cons.nextLine();
			if (!menucontroller.verifyNameNotTaken(credentials[2])) { // is the username vacant?
				System.out.println("Username " + credentials[2] + " is already in use.");
				choiceValid = false;
				break;
			}
		}

		System.out.println("Username accepted: " + credentials[2]);
		System.out.println("Please enter the account password: ");
		credentials[3] = cons.nextLine();

		String checkPass = ""; // verify password is the same before passing
		while (!checkPass.equals(credentials[3])) {
			System.out.println("Please verify your password entered: ");
			checkPass = cons.nextLine();
		}
		return credentials;
	}
}
