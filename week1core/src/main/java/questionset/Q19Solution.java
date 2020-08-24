package questionset;

import java.util.ArrayList;

import operations.Question;

public class Q19Solution implements Question {

	public ArrayList<Integer> manipulateTen() {
		//produce a new arraylist
		ArrayList<Integer> listOfTen = new ArrayList<Integer>();

		//fill it
		for (int i = 1; i <= 10; ++i) {
			listOfTen.add(i);
		}

		System.out.println("Contents of the ArrayList: " + listOfTen);

		int sumOfEven = 0;
		int sumOfOdd = 0;

		//add total of evens and odds above
		for (Integer item : listOfTen) {
			if (item % 2 == 0)
				sumOfEven += item;
			else
				sumOfOdd += item;
		}

		System.out.println("Sum of all odd integers in list :" + sumOfOdd);
		System.out.println("Sum of all even integers in list :" + sumOfEven);

		//now evict the primes from their homes
		for (int i = 0; i < listOfTen.size(); i++) {
			if (Q9Solution.checkPrime(listOfTen.get(i))) {
				listOfTen.remove(listOfTen.get(i--));
			}
		}
		
		System.out.println("Resulting list: " + listOfTen);
		return listOfTen;
	}

	@Override
	public void performTask() {
		System.out.println("Question 19");
		System.out.println("Manipulating an arrayList of numbers 1-10 in various ways...");
		manipulateTen();
		
	}

}
