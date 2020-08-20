package ExceptionFun;

public class FunException extends Exception {
	
	public FunException(String message) {
		super(message);
	}
	
	public static String checkForFun(String in) throws FunException {
		int index = in.toLowerCase().indexOf("fun");
		if(in.toLowerCase().indexOf("fun") != -1)
			throw new FunException("Fun found at index  " + index + " in string: \"" + in + "\"");
		else
			return "No fun in string: \"" + in + "\"";
	}

}
