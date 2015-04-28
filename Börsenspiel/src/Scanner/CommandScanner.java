package Scanner;

import java.io.BufferedReader;
import java.io.IOException;

import Exceptions.ParamErrorException;

public class CommandScanner {

	private CommandDescriptor commandDescriptor;
	private BufferedReader shellReader;
	private StockGameCommandType[] values;
	private CommandTypeInfo command;
	String[] sarr = null;
	Object[] params = null;

	public CommandScanner(StockGameCommandType[] values,
			BufferedReader shellReader) {
		this.shellReader = shellReader;
		this.values = values;
	}

	public void inputLine2CommandDescriptor(CommandDescriptor commandDescriptor) {
		commandDescriptor.setCommandTypeInfo(command);
		commandDescriptor.setParams(params);

	}

	public void readCommand() {
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
						params[i] = sarr[i+1];
					} else if (command.getParamTypes()[i] == int.class) {
						if (Integer.parseInt(sarr[i+1]) <= 0 ){ 
							throw new ParamErrorException() ;
						}
						params[i] = Integer.parseInt(sarr[i+1]);
					}

				}
				for (int j = 0; j < params.length; j++) {
					System.out.println(params[j]);
				}

			}
		}
		// TODO Exception

	}

}
