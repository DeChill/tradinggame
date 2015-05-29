package de.hsaugsburg.exception;
public class PlayerNameAlreadyExistsException extends RuntimeException {
	public PlayerNameAlreadyExistsException() {

		super();
	}

	public PlayerNameAlreadyExistsException(String s) {
		super(s);
	}
}
