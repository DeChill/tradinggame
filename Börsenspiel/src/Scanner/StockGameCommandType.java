package Scanner;

public enum StockGameCommandType implements CommandTypeInfo {
	HELP         ("help", "  * list all commands"),
	EXIT         ("exit", "  * exit program"),
	CREATEPLAYER ("crp",  "<name> * create a new player by name", String.class), 
	BUYSHARE     ("bs",  "<playername> <sharename> <amount> * buy that amount of shares", String.class, String.class, int.class),
	SELLSHARE    ("ss",  "<playername> <sharename> <amount> * sell that amount of shares", String.class, String.class, int.class), 
	VALUE		 ("val", "<playername> * print the player information", String.class);
	
	private StockGameCommandType(String name, String help, Class<?>... args){
		this.name = name;
		this.help = help;
		this.args = args;
	}
	
	
	private String name;
	private String help;
	private Class<?>[] args;
	
	@Override
	public String getName(){
		return name;
	}

	@Override
	public String getHelpText() {
		return help;
		
	}

	@Override
	public Class<?>[] getParamTypes() {
		return args;
	}
	
	
	
	
}
