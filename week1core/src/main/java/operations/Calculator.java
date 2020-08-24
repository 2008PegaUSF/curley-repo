package operations;

public class Calculator implements CalculatorInterface {

	@Override
	public int addition(int a, int b) {

		return a + b;
	}

	@Override
	public int subtract(int a, int b) {
		// TODO Auto-generated method stub
		return a - b;
	}

	@Override
	public double divide(double a, double b) {
		// TODO Auto-generated method stub
		if (b == 0)
			return 0;
		return a / b;
	}

	@Override
	public int multiply(int a, int b) {
		// TODO Auto-generated method stub
		return a * b;
	}

}
