package operations;

public class StringHandler extends AbstractStringHandler {

	@Override
	public boolean checkForUpperCase(String in) {
		
		return in.matches(".*[A-Z].*");
	}

	@Override
	public String converToUpperCase(String in) {
		// TODO Auto-generated method stub
		return in.toUpperCase();
	}

	@Override
	public void convertToIntegerAndAddTen(String in) {
		char[] sc = in.toCharArray();
		int total = 0;
		for(char c : sc) {
			total += (int) c;
		}
		System.out.println("Total of string plus 10 is : " + (total + 10));
	}

}
