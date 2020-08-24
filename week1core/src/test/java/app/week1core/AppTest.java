package app.week1core;

import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import operations.Employee;
import operations.StringHandler;
import questionset.*;


class AppTest {

	// test for Q1
	@Test
	public void testBubbleSort() {
		Q1Solution q = new Q1Solution();
		Assertions.assertArrayEquals(new int[] { 0, 1, 2, 3, 3, 4, 5, 6, 7, 8, 9 },
				q.doBubbleSort(new int[] { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 }));
	}

	// test for Q2

	@Test
	public void testFibonacci() {
		Q2Solution q = new Q2Solution();
		Assertions.assertEquals(75025, q.doFibonacci(25));
	}

	// Test for Q3 - ensure a reversed string is returned that matches
	@Test
	public void testReverseString() {
		// String str1 = "noitaidaR";
		Q3Solution q = new Q3Solution();
		Assertions.assertEquals("noitaidaR", q.reverseString("Radiation"));
	}

	// test for Q4
	@Test
	public void testFactorial() {
		Q4Solution q = new Q4Solution();
		Assertions.assertEquals(120, q.getFactorial(5));
	}

	// test for Q5
	@Test
	public void testSubstring() {
		Q5Solution q = new Q5Solution();
		Assertions.assertEquals("Lorem", q.newSubstring("Lorem ipsum dolar", 5));
	}

	// test for Q6
	@Test
	public void testIsEven() {
		Q6Solution q = new Q6Solution();
		Assertions.assertEquals(false, q.isEven(7));
	}

	// test for Q7
	@Test
	public void testEmployeeSort() {
		Q7Solution q = new Q7Solution();
		Employee e1 = new Employee("Johnny Silvis", "Human Resources", 45);
		Employee e2 = new Employee("Mackenzie Juelson", "Clerical", 31);
		ArrayList<Employee> es = new ArrayList<Employee>();
		es.add(e1);
		es.add(e2);

		Assertions.assertEquals("Mackenzie Juelson",
				q.compareEmployees(e1, e2).get(0).getName());

	}

	// test for Q8
	@Test
	public void testPalindrome() {
		ArrayList<String> check = new ArrayList<String>();
		ArrayList<String> checkAgainst = new ArrayList<String>();
		check.add("dad");
		check.add("bob");
		check.add("abrerba");
		checkAgainst.add("boba");
		checkAgainst.add("dad");
		checkAgainst.add("zilly");
		checkAgainst.add("bob");
		checkAgainst.add("abrerba");
		Q8Solution q = new Q8Solution();
		Assertions.assertEquals(check, q.getListOfPalindromes(checkAgainst));
	}

	// test for Q9
	@Test
	public void testOneToOneHundredList() {
		Q9Solution q = new Q9Solution();
		//just ensuring that we're not modifying our list
		Assertions.assertEquals(1, q.getPrimeFromList(1, 10).get(0));
	}

	// test for Q10
	@Test
	public void testGetMinimum() {
		Q10Solution q = new Q10Solution();
		Assertions.assertEquals(3, q.getMinimum(17, 3));
	}

	// test for Q11
	@Test
	public void testExternalFloats() {
		Q11Solution q = new Q11Solution();
		Assertions.assertEquals(13.4f, q.accessTwoFloats());
	}

	// test for Q12
	@Test
	public void testListOfOneHundred() {
		Q12Solution q = new Q12Solution();
		Assertions.assertEquals(8, q.printOneToOneHundredEnhanced()[3]);
	}

	// test for Q13
	@Test
	public void testBinaryTreePrint() {
		Q13Solution q = new Q13Solution();
		Assertions.assertEquals("\r\n0\r\n1 0\r\n", q.printABinaryPyramid(2));
	}
	
	// test for Q14
	@Test
	public void testQuestion14Solution() { //just checking a valid input is made
		Q14Solution q = new Q14Solution();
		Assertions.assertEquals(true, q.doMultipleChoice(2));
	}
	// test for Q15
	@Test
	public void testQuestion15Solution() {
		Q15Solution q = new Q15Solution();	
		Assertions.assertArrayEquals(new double[] {8.0,12.0}, q.useCalculator(6, 2));
	}
	// test for Q16
	@Test
	public void testQuestion16Solution() {
		String[] testArr = new String[] {"test", "test", "test", "test"};
		Q16Solution q = new Q16Solution(testArr);
		Assertions.assertEquals(16, q.countString(testArr));
	}
	// test for Q17
	@Test
	public void testQuestion17Solution() {
		Q17Solution q = new Q17Solution();		
		Assertions.assertEquals(1100, q.calculateInterest(1000.0,5.0,24.0));
	}
	// test for Q18
	@Test
	public void testQuestion18Solution() {
		Q18Solution q = new Q18Solution();
		Assertions.assertEquals(true, new StringHandler().checkForUpperCase("THIS IS A STRING"));
	}
	// test for Q19
	@Test
	public void testQuestion19Solution() {
		Q19Solution q = new Q19Solution();
		ArrayList<Integer> testBack = new ArrayList<Integer>();
		testBack.add(1);
		testBack.add(4);
		testBack.add(6);
		testBack.add(8);
		testBack.add(9);
		testBack.add(10);
		Assertions.assertEquals(testBack.toString(), q.manipulateTen().toString());
	}
	// test for Q20
	@Test
	public void testFileReader() {
		Q20Solution q = new Q20Solution();
		Assertions.assertEquals(false, q.readAFile(new File("invalid.file")));
	}

}
