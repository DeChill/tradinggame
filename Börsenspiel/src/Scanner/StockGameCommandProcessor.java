package Scanner;

import java.io.BufferedReader;
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
			}
			case HELP: {
			}
			case CREATEPLAYER: {

			}

			}
		}
	}
}
