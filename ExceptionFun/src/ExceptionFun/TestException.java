package ExceptionFun;

public class TestException {
	public static void main(String[] args) {
		try {
			String test = "This is a test.";
			String fun = "There is fun in here.";
			System.out.println(FunException.checkForFun(test));
			System.out.println(FunException.checkForFun(fun));
		} catch (FunException e) {
			e.printStackTrace();
		} finally {
			System.out.println("The end");
		}
	}
}
