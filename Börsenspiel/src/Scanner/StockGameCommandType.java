package Scanner;

public enum StockGameCommandType implements CommandTypeInfo {
	HELP         ("help", "  * list all commands", "help"),
	EXIT         ("exit", "  * exit program", "exit"),
	CREATEPLAYER ("crp",  "<name> * create a new player by name", "addPlayer", String.class), 
	BUYSHARE     ("bs",  "<playername> <sharename> <amount> * buy that amount of shares", "buyShares", String.class, String.class, int.class),
	SELLSHARE    ("ss",  "<playername> <sharename> <amount> * sell that amount of shares", "sellShares", String.class, String.class, int.class), 
	VALUE		 ("val", "<playername> * print the player information", "getPlayerValue", String.class),
	CHECK    	 ("check", "<playername> <sharename> * check if selling makes profit", "check", String.class, String.class),
	AGENT	     ("agent", "<playername> *  starts Agent", "startAgent", String.class),
	HISTORY		 ("history", "<playername> <param> * prints transaction history", "transactionHistoryToString", String.class, String.class);
	
	private StockGameCommandType(String name, String help, String methodName, Class<?>... args){
		this.name = name;
		this.help = help;
		this.methodName = methodName;
		this.args = args;
	}
	
	
	private String name;
	private String help;
	private String methodName;
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

	public String getMethodName() {
		return methodName;
	}

	
	
	
	
	
}
