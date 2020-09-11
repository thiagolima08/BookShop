package bookshop.exception;

public class ValidationException extends Exception {
	private static final long serialVersionUID = 1L;

	public ValidationException() {
		super("erro de validação");
	}

	public ValidationException(String message) {
		super(message);
	}
}
