package Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import Exceptions.ParamErrorException;
import core.AccountManager;

public class StockGameCommandProcessor {

	private BufferedReader shellReader = new BufferedReader(new InputStreamReader(System.in));
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
			
			try{
			commandScanner.readCommand();
			}catch (ParamErrorException e){
				System.out.println("Fehler bei der eingabe. Bitte erneut versuchen!");
				
				// ZURÜCK IN DIE SCHLEIFE!!!!!!
				break;
			}

			CommandDescriptor commandDescriptor = new CommandDescriptor();

			commandScanner.inputLine2CommandDescriptor(commandDescriptor);

			Object[] params = commandDescriptor.getParams();

			StockGameCommandType commandType = (StockGameCommandType) commandDescriptor
					.getCommandType();

			switch (commandType) {
			case EXIT: {
				System.out.println("Good Bye!");
				return;
			}
			case HELP: {
				for (int i = 0; i < StockGameCommandType.values().length; i++) {
					System.out.println(StockGameCommandType.values()[i].getName() + StockGameCommandType.values()[i].getHelpText());
				}
				
				break;
			}
			case CREATEPLAYER: {
				accountManager.addPlayer((String) commandDescriptor.getParams()[0], 50000);
				break;
			}
			
			case BUYSHARE: {
				accountManager.buyShares((String)params[0], (String)params[1], (int)params[2]);
				break;
			}
			case SELLSHARE: {
				accountManager.sellShares((String)params[0], (String)params[1], (int)params[2]);
				break;
			}
			default: break;
			}
		}
	}

//	private void help() {
//
//	}
//
//	private void exit() {
//
//	}
//
//	private void createPlayer() {
//
//	}
//
//	private void buyShare() {
//
//	}
//
//	private void sellShare() {
//
//	}

}
