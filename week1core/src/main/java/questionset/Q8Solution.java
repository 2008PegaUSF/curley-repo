package questionset;

import java.util.ArrayList;

import operations.Question;

public class Q8Solution implements Question {
	// Q8 Store a list of strings in an arraylist and return a new arraylist with
	// all strings that are a palindrome
	public ArrayList<String> getListOfPalindromes(ArrayList<String> in) {
		// create a new ArrayList to return
		ArrayList<String> out = new ArrayList<String>();
		for (String item : in) {
			// code reuse! just call the reverse method made earlier and determine if it
			// equals the same thing
			// if it is, add it to out
			if (item.toLowerCase().equals(new StringBuilder(item).reverse().toString()))
				out.add(item);
		}
		return out;
	}

	@Override
	public void performTask() {
		System.out.println("\nQuestion 8: Get palindromes from a list");
		ArrayList<String> stringList = new ArrayList<String>();
		stringList.add("karan");
		stringList.add("madam");
		stringList.add("tom");
		stringList.add("civic");
		stringList.add("radar");
		stringList.add("jimmy");
		stringList.add("kayak");
		stringList.add("john");
		stringList.add("refer");
		stringList.add("billy");
		stringList.add("did");
		System.out.println("Checking for palindromes in list " + stringList);
		System.out.println("Result is " + getListOfPalindromes(stringList));
		
	}


}
