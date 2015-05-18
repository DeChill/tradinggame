package Scanner;

public class CommandDescriptor {

	private Object [] params;
	private CommandTypeInfo commandType;
	
	public Object[] getParams() {
		return params;
	}

	public CommandTypeInfo getCommandType() {
		return commandType;
	}
	
	public void setParams( Object [] params) {
		this.params = params;
	}
	
	public void setCommandTypeInfo (CommandTypeInfo commandType) {

		this.commandType = commandType;
	}

}
