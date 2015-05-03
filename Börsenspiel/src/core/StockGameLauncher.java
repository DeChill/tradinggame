package core;
import Agent.AgentProcessor;
import Scanner.StockGameCommandProcessor;
import view.StockPriceViewer;


public class StockGameLauncher {
	public static void main(String[] args) throws InterruptedException{
		
		StockPriceProvider stockPriceProvider = new RandomStockPriceProvider();
		
		StockPriceViewer stockPriceViewer = new StockPriceViewer();	
        
		AccountManagerImpl acc = new AccountManagerImpl(stockPriceProvider);
        
		UpdateTimer updateTimer = UpdateTimer.getInstance();
		stockPriceProvider.startUpdate();
		stockPriceViewer.startUpdate(stockPriceProvider);
		
		StockGameCommandProcessor processor = new StockGameCommandProcessor(acc);
		processor.process();
		
//		acc.addPlayer("Fred");
//		AgentProcessor agent = new AgentProcessor("Fred", acc);
//        agent.startUpdate();
        
     
	}
	
	
}
