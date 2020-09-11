package bookshop.exception;

public class RequiredException extends ValidationException {
	private static final long serialVersionUID = 1L;

	public RequiredException() {
		super("campo obrigatório");
	}

	public RequiredException(String field) {
		super(String.format("%s é um campo obrigatório", field));
	}
}
