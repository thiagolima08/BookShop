package bookshop.exception;

public class DuplicatedResourceException extends ValidationException {
	private static final long serialVersionUID = 1L;

	public DuplicatedResourceException() {
		super("recurso já previamente cadastrado");
	}

	public DuplicatedResourceException(String resource) {
		super(String.format("%s já previamente cadastrado", resource));
	}
}
