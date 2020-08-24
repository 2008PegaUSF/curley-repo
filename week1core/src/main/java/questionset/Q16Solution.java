package questionset;

import operations.Question;

public class Q16Solution implements Question {

	private String[] args;
	
	// Q16 Use varargs from main()
	public int countString(String[] args) {
		//returning all amount of chopped up strings
		int total = 0;
		for (String s : args)
			total += s.length();

		return total;
	}

	public Q16Solution(String[] args) {
		this.args=args;
	}

	//working around an interface
	public void performTask(String[] args) {
		System.out.println("Printing total of characters supplied from the command line...");
		System.out.println("Total is " + countString(args));
		
	}

	//just call the overloaded version
	@Override
	public void performTask() {
		performTask(args);
		
	}

}
