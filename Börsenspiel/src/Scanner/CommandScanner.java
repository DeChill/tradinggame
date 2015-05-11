package Scanner;

import java.io.BufferedReader;
import java.io.IOException;

import Exceptions.CommandException;
import Exceptions.ParamErrorException;

public class CommandScanner {

	private CommandDescriptor commandDescriptor;
	private BufferedReader shellReader;
	private CommandTypeInfo[] values;
	private CommandTypeInfo command;
	private String[] sarr = null;
	private Object[] params = null;

	public CommandScanner(CommandTypeInfo[] values,
			BufferedReader shellReader) {
		this.shellReader = shellReader;
		this.values = values;
	}

	public void inputLine2CommandDescriptor(CommandDescriptor commandDescriptor) {
		try {
			readCommand();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out
					.println("Fehler bei der Eingabe: Weitere Parameter erwartet!");
			throw new CommandException();

		} catch (NumberFormatException e) {
			
			System.out.println("Fehler bei der Eingabe: Bitte nur positive ganzzahlige Ziffern  eingeben!");
			throw new CommandException();

		} catch (ParamErrorException e) {
			System.out
					.println("Fehler bei der eingabe. Bitte erneut versuchen!");
			throw new CommandException();
		}
		commandDescriptor.setCommandTypeInfo(command);
		commandDescriptor.setParams(params);

	}

	public void readCommand() throws ArrayIndexOutOfBoundsException {
		String s = "";
		try {
			s = shellReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sarr = s.split(" ");
		for (StockGameCommandType temp : StockGameCommandType.values()) {
			if (sarr[0].equalsIgnoreCase(temp.getName())) {

				command = temp;

				params = new Object[command.getParamTypes().length];
				for (int i = 0; i < params.length; i++) {
					if (command.getParamTypes()[i] == String.class) {
						params[i] = sarr[i + 1];
					} else if (command.getParamTypes()[i] == int.class) {
						if (Integer.parseInt(sarr[i + 1]) <= 0) {
							throw new ParamErrorException();
						}
						params[i] = Integer.parseInt(sarr[i + 1]);
					}

				}

			}
		}
		if (command == null) throw new ParamErrorException();

	}

}
