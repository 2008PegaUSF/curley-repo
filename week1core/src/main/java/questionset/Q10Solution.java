package questionset;

import app.week1core.App;
import operations.Question;

public class Q10Solution implements Question {
	// Q10 Get minimum of two numbers using ternary operator
	public int getMinimum(int num1, int num2) {
		return num1 > num2 ? num2 : num1;
	}

	@Override
	public void performTask() {
		int n1 = App.rando.nextInt();
		int n2 = App.rando.nextInt();
		System.out.println("Returning which is less: " + n1 + " or " + n2);
		System.out.println(getMinimum(n1,n2) + " is the lesser of two");
		
	}

}
