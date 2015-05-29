package de.hsaugsburg.exception;

public class ParamErrorException extends RuntimeException {
	public ParamErrorException(){
		super();
	}
	
	public ParamErrorException(String s)
	{
		super(s);
	}
}
