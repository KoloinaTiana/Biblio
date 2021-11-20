public class IncorrectPasswordException extends Exception {
	
	private static final long serialVersionUID = 6274008294754985624L;

	public IncorrectPasswordException() {
		super();
	}
	
	public IncorrectPasswordException(String message) {
		super(message);
	}
}
