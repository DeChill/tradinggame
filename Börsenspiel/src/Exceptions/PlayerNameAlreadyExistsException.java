package Exceptions;
public class PlayerNameAlreadyExistsException extends RuntimeException {
	public PlayerNameAlreadyExistsException() {

		super();
	}

	public PlayerNameAlreadyExistsException(String s) {
		super(s);
	}
}
