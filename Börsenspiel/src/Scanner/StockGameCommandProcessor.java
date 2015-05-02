package Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Exceptions.ParamErrorException;
import Exceptions.PlayerNotFoundException;
import core.AccountManager;

public class StockGameCommandProcessor {

	private BufferedReader shellReader = new BufferedReader(
			new InputStreamReader(System.in));
	private PrintWriter shellWriter = new PrintWriter(System.out);
	private AccountManager accountManager;

	public StockGameCommandProcessor(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public void process() {
		CommandScanner commandScanner = new CommandScanner(
				StockGameCommandType.values(), shellReader);

		while (true) { // the loop over all commands with one input line for
						// every command

			try {
				commandScanner.readCommand();
			} catch (ParamErrorException e) {
				System.out
						.println("Fehler bei der eingabe. Bitte erneut versuchen!");
				continue;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out
						.println("Fehler bei der Eingabe: Weitere Parameter erwartet!");
				continue;
			} catch (NumberFormatException e) {
				System.out
						.println("Fehler bei der Eingabe: Bitte nur positive ganzzahlige Ziffern  eingeben!");
				continue;
			}

			CommandDescriptor commandDescriptor = new CommandDescriptor();

			commandScanner.inputLine2CommandDescriptor(commandDescriptor);

			Object[] params = commandDescriptor.getParams();

			StockGameCommandType commandType = (StockGameCommandType) commandDescriptor
					.getCommandType();

			// try {
			// if (commandType == null)
			// continue;
			// switch (commandType) {
			// case EXIT: {
			// System.out.println("Good Bye!");
			// return;
			// }
			// case HELP: {
			// for (int i = 0; i < StockGameCommandType.values().length; i++) {
			// System.out.println(StockGameCommandType.values()[i]
			// .getName()
			// + StockGameCommandType.values()[i]
			// .getHelpText());
			// }
			//
			// break;
			// }
			// case CREATEPLAYER: {
//			 if (((String) params[0]).length() >= 16
//			 || (!((String) params[0]).matches("[a-zA-Z1-9]*"))) {
//			 throw new ParamErrorException();
//			 }
			// accountManager.addPlayer(
			// (String) commandDescriptor.getParams()[0], 500000);
			// break;
			//
			// }
			//
			// case BUYSHARE: {
			// accountManager.buyShares((String) params[0],
			// (String) params[1], (int) params[2]);
			// break;
			// }
			// case SELLSHARE: {
			// accountManager.sellShares((String) params[0],
			// (String) params[1], (int) params[2]);
			// break;
			// }
			// case VALUE: {
			// System.out
			// .println("Spielerwert: "
			// + accountManager
			// .getPlayerValue((String) params[0]));
			// break;
			// }
			// default:
			// break;
			// }
			// } catch (ParamErrorException e) {
			// System.out.println("Fehler bei der Eingabe!");
			//
			// } catch (PlayerNotFoundException e) {
			// System.out.println("Spieler nicht gefunden!");
			// }
			Class<?> c;
			try {
				c = AccountManager.class;
				Method method = c.getDeclaredMethod(
						commandType.getMethodName(),
						commandType.getParamTypes());
				Object s = method.invoke(accountManager, params);
//				if (s != null)
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				System.out.println("No such method! enter -help- for more information");
			} catch (Exception e){
				System.out.println("ERROR!!!");
			}

		}

	}
}
