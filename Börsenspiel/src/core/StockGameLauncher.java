package core;
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
        
     
	}
	
}
