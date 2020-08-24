package questionset;

import java.util.Random;

import operations.Question;

// Q4 compute N Factorial
// recursion works nicely for this
// factorials are just n1 * n2 * n3 * ... nx until hit designated n
public class Q4Solution implements Question {
	
	@Override
	public void performTask() {
		getFactorial(new Random().nextInt(30) + 3);
	}
	
	public int getFactorial(int n) {
		if (n <= 1)
			return n;

		return n * getFactorial(n - 1);
	}
}
