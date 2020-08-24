package questionset;

import operations.Question;

public class Q3Solution implements Question {

	public String reverseString(String in) {
		// is our string length less than 2? if so just send it back out
		if (in.length() <= 1)
			return in;

		// get the next index and put first char in it then return into our working
		// string
		return reverseString(in.substring(1)) + in.charAt(0);
	}

	@Override
	public void performTask() {
		System.out.println("Question 3: reverse a String without using a placeholder variable.");
		System.out.println("Doing reverse on String \"Radiation\"");
		System.out.println("Radiation reversed is: " + reverseString("Radiation"));
	}
}
