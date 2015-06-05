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

	public void process() {
		CommandScanner commandScanner = new CommandScanner(
				StockGameCommandType.values(), shellReader);

		while (true) { // the loop over all commands with one input line for
						// every command

			CommandDescriptor commandDescriptor = new CommandDescriptor();

			try {
				commandScanner.inputLine2CommandDescriptor(commandDescriptor);
			} catch (CommandException e) {
				continue;
			}

			Object[] params = commandDescriptor.getParams();

			StockGameCommandType commandType = (StockGameCommandType) commandDescriptor
					.getCommandType();

			switch (commandType) {
			case EXIT: {
				System.out.println("Bye!");
				exit();
				continue;
			}
			case HELP: {
				help();
				continue;
			}
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
		

			if (s != null)System.out.println(s);
		}

	}

	public void help() {

		for (int i = 0; i < StockGameCommandType.values().length; i++) {
			System.out
					.println((StockGameCommandType.values()[i].getName() + StockGameCommandType
							.values()[i].getHelpText()));

		}

	}

	public void exit() {
		System.exit(0);
	}
}
