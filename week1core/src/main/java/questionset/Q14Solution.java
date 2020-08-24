package questionset;

import java.util.Date;

import app.week1core.App;
import operations.Question;

public class Q14Solution implements Question {

	// Q14 Multiple choice function
	// choose between 3 cases
	// case 1: get a square root of a number
	// case 2: display the dates
	// case 3: split and store a string in an array - string is "I am learning Core
	// Java"
	public boolean doMultipleChoice(int... option) {

		System.out.print("Please choose from one of the following items: ");
		System.out.println("1. To get the square root of a number.");
		System.out.println("2. To display today's date.");
		System.out.println("3. To display a split String as an array.");
		int choice;
		
		try {
			if(option.length == 0) choice = Integer.parseInt(App.con.next());
			else choice = option[0];
		} catch(NumberFormatException e) {
			System.out.println("Invalid choice, failing...");
			choice = 0;
		}
		switch (choice) {
		case 1:
			System.out.print("Please enter an integer to get the square root of: ");
			double sq = Double.parseDouble(App.con.next());
			System.out.println("Square root of " + sq + " is: " + Math.sqrt(sq));
			return true;
		case 2:
			System.out.println(new Date().toString());
			return true;
		case 3:
			System.out.print(java.util.Arrays.deepToString(("I am learning Core Java".split(" "))));
			break;
			default: 
				return false;
		}
		return false;
	}

	
	@Override
	public void performTask() {
		System.out.println("\nQuestion 14");
		System.out.println("Operating the switch statement...");
		doMultipleChoice();
	}

}
