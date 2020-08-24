package questionset;

import operations.Question;

public class Q5Solution implements Question {

	// Q5 Substring method without use of String.substring
	// takes input string and just adds to a stringbuilder the string up to index
	public String newSubstring(String in, int idx) {
		char[] s = in.toCharArray();
		s = java.util.Arrays.copyOfRange(s, 0, idx);
		return String.copyValueOf(s);
		/*
		 * StringBuilder sb = new StringBuilder(); for (int i = 0; i < idx; i++) {
		 * sb.append(in.charAt(i)); } return sb.toString();
		 */

	}

	@Override
	public void performTask() {
		System.out.println("Getting substring of \"Test String\" to index 4");
		System.out.println("Result: " + newSubstring("Test String", 4));
		
	}
}
