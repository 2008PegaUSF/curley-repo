package questionset;

import operations.Question;
import operations.StringHandler;

public class Q18Solution implements Question {
	public static void performStringFun(String in) {
		StringHandler sth = new StringHandler();
		System.out.println(
				"Does string \"" + in + "\" have uppercase letters?" + (sth.checkForUpperCase(in) ? " Yes" : " No"));
		System.out.println("What does string \"" + in + "\" look like in all caps? " + sth.converToUpperCase(in));
		System.out.println("What is \"" + in + " plus 10?");
		sth.convertToIntegerAndAddTen(in);
	}

	@Override
	public void performTask() {
		System.out.println("Having some fun with strings...");
		performStringFun("i Am A sTrInG");

	}

}
