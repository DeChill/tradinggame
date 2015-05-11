package Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Exceptions.CommandException;
import Exceptions.ParamErrorException;
import Exceptions.PlayerNotFoundException;
import core.AccountManager;

public class StockGameCommandProcessor {

	private BufferedReader shellReader = new BufferedReader(
			new InputStreamReader(System.in));
//	private PrintWriter shellWriter = new PrintWriter(System.out);
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
			
			try{
			commandScanner.inputLine2CommandDescriptor(commandDescriptor);
			}catch (CommandException e){
				continue;
			}

			Object[] params = commandDescriptor.getParams();

			StockGameCommandType commandType = (StockGameCommandType) commandDescriptor
					.getCommandType();

			
			switch (commandType) {
			case EXIT: {
				System.out.println("Good Bye!");
				exit();
				continue;
			}
			case HELP: {
				help();
				continue;
			}
			}

			Class<?> c;
			try {
				c = AccountManager.class;
				Method method = c.getDeclaredMethod(
						commandType.getMethodName(),
						commandType.getParamTypes());
				Object s = method.invoke(accountManager, params);
				
				
				if (s != null)
					System.out.println(s);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParamErrorException e) {
				System.out.println("Fehler bei der Eingabe!");
			} catch (PlayerNotFoundException e) {
				System.out.println("Spieler nicht gefunden!");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.out.println("Illegal Arguments!");
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				System.out
						.println("No such method! enter -help- for more information");
			} catch (Exception e) {
				System.out.println("ERROR!!!");
			}

		}

	}

	public void help() {

		for (int i = 0; i < StockGameCommandType.values().length; i++) {
					System.out.println((StockGameCommandType.values()[i].getName() + StockGameCommandType
							.values()[i].getHelpText()));

		}

	}

	public void exit() {
		System.exit(0);
	}
}
