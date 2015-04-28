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
        
     /*   
        acc.addPlayer("Timo", 10000000);
        acc.buyShares("Timo", "BMW", 50);
        acc.buyShares("Timo", "Audi", 50);
        acc.buyShares("Timo", "BMW",  20);
        acc.sellShares("Timo", "BMW" , 30);
        acc.sellShares("Timo", "Audi", 5);
        System.out.println(acc.getPlayer("Timo").toString());
        Thread.sleep(10000);
        
        System.out.println(acc.getPlayer("Timo").toString());
        /*
//         viewerDemo.update(stockPriceProvider);
        System.out.println(acc.getPlayer("Timo").toString());
        acc.addPlayer("Florian",  2000000);
        acc.buyShares("Florian",  "Audi", 30);
        System.out.println(acc.getPlayer("Florian").toString());
        Thread.sleep(10000);
        System.out.println(acc.getPlayer("Florian").toString());
        */
	}
	
}
