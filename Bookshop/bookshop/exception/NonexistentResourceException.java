package bookshop.exception;

public class NonexistentResourceException extends ValidationException {
	private static final long serialVersionUID = 1L;

	public NonexistentResourceException() {
		super("recurso não existe");
	}

	public NonexistentResourceException(String resource) {
		super(String.format("%s não existe", resource));
	}
}
