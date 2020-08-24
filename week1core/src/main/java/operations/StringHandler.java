package operations;

public class StringHandler extends AbstractStringHandler {

	@Override
	public boolean checkForUpperCase(String in) {
		//check if there is an uppercase in a string.  if so, return true
		return in.matches(".*[A-Z].*");
	}

	//return a string in uppercase fashoin
	@Override
	public String converToUpperCase(String in) {
		return in.toUpperCase();
	}

	@Override
	//assignment said add 10 to string.  it didn't say what to pass in or parse for integers so instead we are just taking
	//the string, turning it into a char array, totaling the int values of the char, and adding ten.
	public void convertToIntegerAndAddTen(String in) {
		char[] sc = in.toCharArray();
		int total = 0;
		for(char c : sc) {
			total += (int) c;
		}
		System.out.println("Total of string plus 10 is : " + (total + 10));
	}

}
