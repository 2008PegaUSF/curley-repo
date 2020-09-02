package exceptions;

public class InvalidAmountException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAmountException() {
		super();
	}
	
	@Override
	public String getMessage() {
		return "Negative amounts cannot be entered.";
	}
}
