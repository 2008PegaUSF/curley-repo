package questionset;

import app.week1core.App;
import operations.Question;

public class Q17Solution implements Question {

	public double calculateInterest(double... input) {

		
		
		double principle = 0.0;
		double rate = 0.0;
		double time = 0.0;
		
		if(input.length == 3) {
			principle = input[0];
			rate = input[1]/100;
			time = input[2];
		}
		
		System.out.println("Please enter the amount of principle investment: ");
		if(principle == 0.0) principle = App.con.nextDouble();
		System.out.println("Please enter the interest rate as a whole number: ");
		if(rate == 0.0) rate = App.con.nextDouble() / 100;
		System.out.println("Please enter the term of the loan in months ");
		if(time == 0.0) time = App.con.nextInt();
		double result = (principle * (1 + rate * (time / 12)));
		System.out.println("Simple interest is " + result);
		return result;
	}

	@Override
	public void performTask() {
		System.out.println("Question 17");
		System.out.println("Calculating simple interest...");
		calculateInterest();
	}

}
