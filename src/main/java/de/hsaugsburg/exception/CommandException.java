
package de.hsaugsburg.exception;

public class CommandException extends RuntimeException {
	
	public CommandException(){
		
	}
	
	public CommandException(String s){
		super(s);
	}

}
