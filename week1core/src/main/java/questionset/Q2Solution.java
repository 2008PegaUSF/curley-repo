package questionset;

import operations.Question;

public class Q2Solution implements Question {

	
	public int doFibonacci(int n) {
		// Q2 - Fibonacci sequence
		// Recursion is very much useful
		// calculates a series of numbers matching the patter of xn = xn-2 + xn-1
		if (n <= 1) {
			System.out.print("Base number hit: " + n);
			return n;
		}
		n--;
		int n1 = 0;
		int n2 = 1;
		int total = 1;
		for (int i = 0; i < n; i++) {
			total = n1 + total;
			n1 = n2;
			n2 = total;
			System.out.println("Total of Fibonacci up to " + (n+1) + " (current number " + (i+2) + "): " + total);
		}
		// System.out.println("Total: " + total);
		 return total;
		// return getFibonacci(n-1) + getFibonacci(n-2);
	}

	@Override
	public void performTask() {
		System.out.println("Question 2: Output results of fibonacci series to 25");
		System.out.println("Performing a fibonacci series on 25: ");
		doFibonacci(25);
		
	}

}
