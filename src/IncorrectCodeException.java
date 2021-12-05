public class IncorrectCodeException extends Exception {

    private static final long serialVersionUID = 6274008294754985624L;

    public IncorrectCodeException() {
        super();
    }

    public IncorrectCodeException(String message) {
        super("Code de verification incorrect");
    }
}