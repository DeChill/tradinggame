package de.hsaugsburg.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.hsaugsburg.exception.ParamErrorException;
import de.hsaugsburg.exception.PlayerNotFoundException;

public class MyInvocationHandler implements InvocationHandler {
	private AccountManager acc;
	private Logger logger = Logger.getLogger(MyInvocationHandler.class.getName());
	
	
	
	MyInvocationHandler(AccountManager accountManager){
		this.acc = accountManager;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws NoSuchMethodException{
		
		Object result = null;
		try{
		result = method.invoke(acc, args);
		} catch (NullPointerException e) {
			logExc(e);
		} catch (SecurityException e) {
			logExc(e);
		} catch (ParamErrorException e) {
			System.out.println("Fehler bei der Eingabe!");
		} catch (PlayerNotFoundException e) {
			System.out.println("Spieler nicht gefunden!");
		} catch (IllegalAccessException e) {
			logExc(e);
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Arguments!");
		} catch (InvocationTargetException e) {
			System.out
					.println("No such method! enter -help- for more information");
		} catch (Exception e) {
			logExc(e);
		}
		
		log(args, method, result);
		
		return result;
	}
	
	private void log(Object[] args, Method method, Object result) {
		
		logger.setLevel(Level.FINE);
		String s = args[0].toString() + " calls method " + method.getName();
		if (result != null){
			s = s + " ergebnis = " + result;
		}
		logger.log(Level.FINE, s, args);
	}
	
	private void logExc(Exception e){
		logger.setLevel(Level.SEVERE);
		logger.severe(e.getMessage());
	}

	

}
