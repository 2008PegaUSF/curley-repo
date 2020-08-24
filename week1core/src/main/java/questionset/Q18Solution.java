package questionset;

import operations.Question;
import operations.StringHandler;

public class Q18Solution implements Question {
	
	public void performStringFun(String in) {
		//create new local-scope class to test method functionality
		StringHandler sth = new StringHandler();
		//use regex to check for uppercase
		System.out.println(
				"Does string \"" + in + "\" have uppercase letters?" + (sth.checkForUpperCase(in) ? " Yes" : " No"));
		//convert to uppercase and print
		System.out.println("What does string \"" + in + "\" look like in all caps? " + sth.converToUpperCase(in));
		//add ten to string... parse integer? literal with 10 concatenated onto it? i have no idea, so we'll just use a character array, sum it and add ten!
		System.out.println("What is \"" + in + " plus 10?");
		sth.convertToIntegerAndAddTen(in);
	}

	@Override
	public void performTask() {
		System.out.println("Question 18");
		System.out.println("Having some fun with strings...");
		performStringFun("i Am A sTrInG");

	}

}
