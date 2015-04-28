package Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
				System.out.println(commandType.getHelpText());
				break;
			}
			case CREATEPLAYER: {
				String s = ""; 
				try {
				s = shellReader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}	
				accountManager.addPlayer(s, 20000000);
				break;
			}
			case BUYSHARE: {
				accountManager.buyShares((String) params[0],
						(String) params[1], (int) params[2]);
				break;
			}
			case SELLSHARE: {
				accountManager.sellShares((String) params[0],
						(String) params[1], (int) params[2]);
				break;
			}
			default: break;
			}
		}
	}

	private void help() {

	}

	private void exit() {

	}

	private void createPlayer() {

	}

	private void buyShare() {

	}

	private void sellShare() {

	}

}
