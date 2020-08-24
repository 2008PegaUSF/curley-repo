package questionset;

import java.util.ArrayList;

import operations.Question;

public class Q12Solution implements Question {

	// Q12 Store numbers 1 to 100 in an array
	// Print all even using enhanced for loop
	public int[] printOneToOneHundredEnhanced() {
		int[] intArray = new int[100];
		int[] evenArray = new int[50];
		int current = 0;
		for (int i = 0; i < 100; i++) {
			intArray[i] = i + 1;
		}

		System.out.println("Adding even numbers to arraylist...");
		for (int in : intArray) {
			if ((in & 1) == 0) {
				System.out.println(in);
				evenArray[current++] = in;
			}
		}

		for(int i = 0 ; i < evenArray.length; i++) 
			System.out.print(evenArray[i] + " ");
		
		System.out.println();
		return evenArray;
	}

	@Override
	public void performTask() {
		System.out.println("Working on various manipulations of a range from 1 to 100");
		printOneToOneHundredEnhanced();
		
	}

}
