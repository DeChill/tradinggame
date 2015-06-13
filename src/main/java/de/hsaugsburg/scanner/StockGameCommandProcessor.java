package de.hsaugsburg.scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.hsaugsburg.core.AccountManager;
import de.hsaugsburg.exception.CommandException;
import de.hsaugsburg.exception.ParamErrorException;
import de.hsaugsburg.exception.PlayerNotFoundException;

public class StockGameCommandProcessor {

	private Logger logger = Logger.getLogger(StockGameCommandProcessor.class
			.getName());
	private BufferedReader shellReader = new BufferedReader(
			new InputStreamReader(System.in));
	// private PrintWriter shellWriter = new PrintWriter(System.out);
	private AccountManager accountManager;

	public StockGameCommandProcessor(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public String process(String string) {
		CommandScanner commandScanner = new CommandScanner(
				StockGameCommandType.values(), shellReader);

//		while (true) { // the loop over all commands with one input line for
//						// every command

			CommandDescriptor commandDescriptor = new CommandDescriptor();

			try {
				commandScanner.inputLine2CommandDescriptor(commandDescriptor, string);
			} catch (CommandException e) {
//				continue;
			}

			Object[] params = commandDescriptor.getParams();

			StockGameCommandType commandType = (StockGameCommandType) commandDescriptor
					.getCommandType();

			switch (commandType) {
			case EXIT: {
//				return "bye";
				exit();
				break;
//				continue;
			}
			case HELP: {
				return help();
//				break;
//				continue;
			}
			default:
				break;
			}

			Class<?> c;
			c = AccountManager.class;
			Object s = null;
			Method method = null;
			try {
				method = c.getDeclaredMethod(commandType.getMethodName(),
						commandType.getParamTypes());
			} catch (NoSuchMethodException e) {
				System.out.println("No such method");
				e.printStackTrace();
			} catch (SecurityException e) {
				System.out.println("BEEP BEEP SECURITY EXECPTION DETECTED BEEP BEEP");
				e.printStackTrace();
			}
			try{
			s = method.invoke(accountManager, params);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
		

			if (s != null)return (String)s;
			return "";
		}

//	}

	public String help() {
			StringBuffer s = new StringBuffer();
		for (int i = 0; i < StockGameCommandType.values().length; i++) {
			s.append((StockGameCommandType.values()[i].getName() + StockGameCommandType
							.values()[i].getHelpText()) + "\n\r");

		}
		return s.toString();

	}

	public void exit() {
		System.exit(0);
	}
}
