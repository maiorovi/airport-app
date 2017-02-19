package exceptions;

public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}

	public EntityNotFoundException(String message) {
		super(message);
	}
}
