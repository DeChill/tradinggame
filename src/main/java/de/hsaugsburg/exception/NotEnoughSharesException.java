package de.hsaugsburg.exception;

public class NotEnoughSharesException extends Exception{
	public NotEnoughSharesException(){
		super();
	}
	
	public NotEnoughSharesException(String s)
	{
		super(s);
	}
}
