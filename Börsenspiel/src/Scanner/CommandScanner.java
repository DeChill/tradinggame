package Scanner;

import java.io.BufferedReader;
import java.io.IOException;

public class CommandScanner {
	
	private CommandDescriptor commandDescriptor;
	private BufferedReader shellReader;
	private StockGameCommandType [] values;
	
	public CommandScanner(StockGameCommandType[] values,	BufferedReader shellReader) {
		this.shellReader = shellReader;
		this.values = values;
	}

	public void inputLine2CommandDescriptor(CommandDescriptor commandDescriptor) {
		commandDescriptor.setParams(values);
		commandDescriptor.setCommandTypeInfo(getCommandTypeInfo());
		
	}

	private StockGameCommandType getCommandTypeInfo ()  {
		String s = ""; 
		try {
		s = shellReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		for(StockGameCommandType info : StockGameCommandType.values()){
			System.out.println("bin ich hier neu " + info.getName());
			if(s.equalsIgnoreCase(info.getName())){  
			System.out.println("gefunden");	
				return info;
			}
		}
		System.out.println("hier schon");
		return null;
		
	/*	switch (s) {
		
		case "help" : 
			break;
		case "exit" :
			break;
		case "crp"  :
			break;
		case "bs"	:
			break;
		case "ss"   :
			break;
		}
		*/
	}

}
