package operations;

/*import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import app.App;*/

//performs all requested operations
public class Operations {

	// Q1 - Bubble Sort
	// very simple sort algorithm, just check i and i+1 and flip them if i > i+1,
	// then walk down the line until done
	/*public static int[] bubbleSort(int[] intArray) {
		boolean sortDone = false;
		while (!sortDone) {
			sortDone = true;
			for (int i = 0; i < intArray.length - 1; i++) {
				int t = 0;
				// is current index bigger than next index?
				if (intArray[i] > intArray[i + 1]) {
					// yes, swap em, also set done to false because we had a swap
					System.out.println("Swap");
					sortDone = false;
					t = intArray[i];
					intArray[i] = intArray[i + 1];
					intArray[i + 1] = t;
				}

			}

		}
		// return our newly sorted array
		return intArray;
	}*/

	// Q2 - Fibonacci sequence
	// Recursion is very much useful
	// calculates a series of numbers matching the patter of xn = xn-2 + xn-1
	/*public static int getFibonacci(int n) {
		// if (n<=2) return 1;
		/*
		 * int total = 0; int sum = 0; for (int i = 2; i < n; i++) { total += (i + 2) +
		 * (i + 1); sum += i + total + (i-2)+(i-1); System.out.println("Total " +
		 * total); }
		 * 
		 * 
		 * return total;
		 */

		/*if (n <= 1)
			return n;
		int n1 = 0;
		int n2 = 1;
		int total = 1;
		for (int i = 0; i < n; i++) {
			total = n1 + total;
			n1 = n2;
			n2 = total;
			System.out.println("Total: " + total);
		}
		// System.out.println("Total: " + total);
		return total;
		// return getFibonacci(n-1) + getFibonacci(n-2);
	}*/

	// Q3 - Reverse a String without a temp variable
	// use recursion here.
	/*public static String reverseString(String in) {
		// is our string length less than 2? if so just send it back out
		if (in.length() <= 1)
			return in;

		// get the next index and put first char in it then return into our working
		// string
		return reverseString(in.substring(1)) + in.charAt(0);
	}*/

	// Q4 compute N Factorial
	// recursion strikes again
	// factorials are just n1 * n2 * n3 * ... nx until hit designated n
	/*public static int getFactorial(int n) {
		if (n <= 1)
			return n;

		return n * getFactorial(n - 1);
	}*/

	// Q5 Substring method without use of String.substring
	// takes input string and just adds to a stringbuilder the string up to index
	/*public static String newSubstring(String in, int idx) {
		char[] s = in.toCharArray();
		s = java.util.Arrays.copyOfRange(s, 0, idx);
		return String.copyValueOf(s);
		/*
		 * StringBuilder sb = new StringBuilder(); for (int i = 0; i < idx; i++) {
		 * sb.append(in.charAt(i)); } return sb.toString();
		 */

	//}

	// Q6 Determine integer is even without modulo
	// Bitwise AND with 1 to receive result
	// for instance, 5 in binary is 0b1001, 1 is 0b0001.
	// this would return 1 since 1001 & 0001 = 1
/*	public static boolean isEven(int in) {
		return (in & 0b1) == 0;
	}*/

	// Q7 Sort two Employees using comparator by name, department, and age

	/*public static ArrayList<Employee> compareEmployees(Employee em1, Employee em2) {
		ArrayList<Employee> empList = new ArrayList<Employee>();

		if (em1.compare(em1, em2) == 0) {
			empList.add(em1);
			empList.add(em2);
		} else {
			empList.add(em2);
			empList.add(em1);
		}

		return empList;
	}*/

	// Q8 Store a list of strings in an arraylist and return a new arraylist with
	// all strings that are a palindrome
	/*public static ArrayList<String> getListOfPalindromes(ArrayList<String> in) {
		// create a new ArrayList to return
		ArrayList<String> out = new ArrayList<String>();
		for (String item : in) {
			// code reuse! just call the reverse method made earlier and determine if it
			// equals the same thing
			// if it is, add it to out
			if (item.toLowerCase().equals(Operations.reverseString(item.toLowerCase())))
				out.add(item);
		}
		return out;
	}*/

	// Q9 create an ArrayList which stores from 1 to 100 and prints all prime
	// numbers
	/*public static void getPrimeFromList(int start, int end) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = start; i < end; i++) {
			list.add(i);
		}

		System.out.println("List of prime numbers in range of " + start + " to " + end + ":");

		for (int item : list) {
			if (Operations.checkPrime(item))
				System.out.print(item + " ");
		}
	}

	public static boolean checkPrime(int in) {
		if (in < 2)
			return false;
		for (int i = 2; i <= in / 2; i++) {
			if (in % i == 0)
				return false;
		}
		return true;
	}*/

	// Q10 Get minimum of two numbers using ternary operator
	/*public static int getMinimum(int num1, int num2) {
		return num1 > num2 ? num2 : num1;
	}*/

	// Q11 Write a program that would access two floats in a different package
	/*public static void accessTwoFloats() {
		class InnerClass extends externalFloatPackage.ExternalFloats {

		}

		InnerClass newClass = new InnerClass();
		System.out.println(
				"Accessing a float in package externalFloatPackage named anotherFloat : " + newClass.externalFloat);
		System.out
				.println("Accessing a float in package externalFloatPackage named anotherFloatAgain via class method : "
						+ externalFloatPackage.ExternalFloats.getExternalFloat());
	}*/

	// Q12 Store numbers 1 to 100 in an array
	// Print all even using enhanced for loop
	/*public static void printOneToOneHundredEnhanced() {
		int[] intArray = new int[100];

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			intArray[i] = i + 1;
		}

		for (int in : intArray) {
			if ((in & 1) == 0) {
				System.out.println(in);
				list.add(in);
			}
		}

		System.out.println(list);
	}*/

	// Q13 Print a binary pyramid
	/*public static String printABinaryPyramid(int rows) {
		boolean oneOrZero = false;

		String out = "";
		
		for (int i = 0; i <= rows; i++) {
			for (int j = 0; j < i; j++) {
				out += (oneOrZero ? "1" : "0");
				System.out.print(oneOrZero ? "1" : "0");
				if (j + 1 < i) {
					System.out.print(" ");
					out += " ";
				}

				oneOrZero = !oneOrZero;
			}
			System.out.println();
			out += "\r\n";
		}
		return out;
	}*/

	// Q14 Multiple choice function
	// choose between 3 cases
	// case 1: get a square root of a number
	// case 2: display the dates
	// case 3: split and store a string in an array - string is "I am learning Core
	// Java"
	/*public static void doMultipleChoice() {

		System.out.print("Please choose from one of the following items: ");
		System.out.println("1. To get the square root of a number.");
		System.out.println("2. To display today's date.");
		System.out.println("3. To display a split String as an array.");
		switch (Integer.parseInt(App.con.next())) {
		case 1:
			System.out.print("Please enter an integer to get the square root of: ");
			double sq = Double.parseDouble(App.con.next());
			System.out.println("Square root of " + sq + " is: " + Math.sqrt(sq));
			break;
		case 2:
			System.out.println(new Date().toString());
			break;
		case 3:
			System.out.print(("I am learning Core Java".split("")));
			break;
		}

	}*/

	// Q15 Work with an implementation of a calculator interface
/*	public static void useCalculator(int a, int b) {
		Calculator calc = new Calculator();
		System.out.println(a + " + " + b + " is " + calc.addition(a, b));
		System.out.println(a + " - " + b + " is " + calc.subtract(a, b));
		System.out.println(a + " / " + b + " is " + calc.divide(a, b));
		System.out.println(a + " * " + b + " is " + calc.multiply(a, b));
	}*/

	// Q16 Use varargs from main()
	/*public static int countString(String[] args) {
		int total = 0;
		for (String s : args)
			total += s.length();

		return total;
	}*/

	// Q17 Calculate simple interest on principle
	/*public static double calculateInterest() {
		
		System.out.println("Please enter the amount of principle investment: ");
		double principle = App.con.nextDouble();
		System.out.println("Please enter the interest rate as a whole number: ");
		double rate = App.con.nextDouble()/100;
		System.out.println("Please enter the term of the loan in months ");
		int term = App.con.nextInt();
		
		
		
		return principle*(1 + rate * (term/12));
	}*/

	// Q18 Method test of abstract class implementation
/*	public static void performStringFun(String in) {
		StringHandler sth = new StringHandler();
		System.out.println(
				"Does string \"" + in + "\" have uppercase letters?" + (sth.checkForUpperCase(in) ? " Yes" : " No"));
		System.out.println("What does string \"" + in + "\" look like in all caps? " + sth.converToUpperCase(in));
		System.out.println("What is \"" + in + " plus 10?");
		sth.convertToIntegerAndAddTen(in);
	}*/

	// Q19 Create an ArrayList and insert 1-10.
	// Display the contents.
	// Add all even and display sum
	// Add all odd and display sum
	// remove the prime numbers and display remaining in list
/*	public static ArrayList<Integer> manipulateTen() {
		ArrayList<Integer> listOfTen = new ArrayList<Integer>();

		for (int i = 1; i <= 10; ++i) {
			listOfTen.add(i);
		}

		System.out.println("Contents of the ArrayList: " + listOfTen);

		int sumOfEven = 0;
		int sumOfOdd = 0;

		for (Integer item : listOfTen) {
			if (item % 2 == 0)
				sumOfEven += item;
			else
				sumOfOdd += item;
		}

		System.out.println("Sum of all odd integers in list :" + sumOfOdd);
		System.out.println("Sum of all even integers in list :" + sumOfEven);

		for (int i = 0; i < listOfTen.size(); i++) {
			if (!checkPrime(listOfTen.get(i))) {
				listOfTen.remove(listOfTen.get(i));
				i -= 1;
			}
		}
		return listOfTen;
	}*/

	// Q20 read a file and then neatly format its contents to the console
/*	public static boolean readAFile(File file) throws IOException {

		if (!file.exists())
			return false;

		App.con = new Scanner(file);
		// Scanner fin = new Scanner(fsin);

		while (App.con.hasNext()) {
			String t[] = App.con.next().split(":");
			System.out.println("Name: " + t[0] + " " + t[1]);
			System.out.println("Age: " + t[2]);
			System.out.println("State: " + t[3]);
		}

		App.con.close();

		return true;
	}*/
}
