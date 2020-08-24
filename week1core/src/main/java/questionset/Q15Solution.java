package questionset;

import operations.Calculator;
import operations.Question;

public class Q15Solution implements Question {
	private Calculator calc = new Calculator();
	// Q15 Work with an implementation of a calculator interface
	public double[] useCalculator(int a, int b) {
		
		double[] out = new double[2];
		
		System.out.println(a + " + " + b + " is " + calc.addition(a, b));
		System.out.println(a + " - " + b + " is " + calc.subtract(a, b));
		System.out.println(a + " / " + b + " is " + calc.divide(a, b));
		System.out.println(a + " * " + b + " is " + calc.multiply(a, b));
		
		out[0] = calc.addition(a, b);
		out[1] = calc.multiply(a, b);
		
		return out;
	}
	
	public Calculator getCalculator() {
		return this.calc;
	}

	@Override
	public void performTask() {
		System.out.println("Question 15");
		System.out.println("Testing out a new 14 button calculator");
		useCalculator(6, 3);
		
	}

}
