package questionset;

import operations.Question;

public class Q6Solution implements Question{

	// Q6 Determine integer is even without modulo
	// Bitwise AND with 1 to receive result
	// for instance, 5 in binary is 0b1001, 1 is 0b0001.
	// this would return 1 since 1001 & 0001 = 1
	public boolean isEven(int in) {
		return (in & 0b1) == 0;
	}

	@Override
	public void performTask() {
		System.out.println("\nQuestion 6");
		System.out.println("Checking if the number 3 is even...");
		System.out.println("3 is " + (isEven(3) ? "even" : "odd"));
		
	}
}