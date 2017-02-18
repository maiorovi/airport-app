package exceptions;

public class PassengerBadNameException extends RuntimeException {

	public PassengerBadNameException() {
		super();
	}

	public PassengerBadNameException(String msg) {
		super(msg);
	}
}
