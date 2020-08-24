package questionset;

import java.util.ArrayList;

import operations.Question;

public class Q9Solution implements Question {
	// Q9 create an ArrayList which stores from 1 to 100 and prints all prime
	// numbers
	public ArrayList<Integer> getPrimeFromList(int start, int end) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = start; i < end; i++) {
			list.add(i);
		}

		System.out.println("List of prime numbers in range of " + start + " to " + end + ":");

		for (int item : list) {
			if (checkPrime(item))
				System.out.print(item + " ");
		}
		System.out.println(list);
		return list;
	}

	public static boolean checkPrime(int in) {
		if (in < 2)
			return false;
		for (int i = 2; i <= in / 2; i++) {
			if (in % i == 0)
				return false;
		}
		return true;
	}

	@Override
	public void performTask() {
		System.out.println("\nQuestion 9");
		System.out.println("Getting prime numbers from 1 to 100: ");
		getPrimeFromList(1,100);
		
	}

}
