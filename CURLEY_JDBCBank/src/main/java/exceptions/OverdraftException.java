package exceptions;

public class OverdraftException extends Exception {


	private static final long serialVersionUID = 1L;

	public OverdraftException() {
		super();
	}
	
	@Override
	public String getMessage() {
		return "Accounts cannot be overdrafted here.";
	}
}
