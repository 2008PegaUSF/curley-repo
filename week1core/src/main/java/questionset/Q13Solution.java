package questionset;

import operations.Question;

public class Q13Solution implements Question {
	// Q13 Print a binary pyramid
	public String printABinaryPyramid(int rows) {
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
	}

	@Override
	public void performTask() {

		System.out.println("Printing a binary pyramid to 4 rows...");
		printABinaryPyramid(4);
		
	}
}
