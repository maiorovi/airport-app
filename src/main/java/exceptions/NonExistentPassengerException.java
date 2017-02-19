package exceptions;

public class NonExistentPassengerException extends RuntimeException {
	public NonExistentPassengerException() {
		super();
	}

	public NonExistentPassengerException(Throwable cause) {
		super(cause);
	}

	public NonExistentPassengerException(String message) {
		super(message);
	}
}
