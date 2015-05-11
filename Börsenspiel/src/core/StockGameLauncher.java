package core;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import Agent.AgentProcessor;
import Logging.*;
import Exceptions.NotEnoughMoneyException;
import Scanner.StockGameCommandProcessor;
import view.PlayerViewer;
import view.StockPriceViewer;


public class StockGameLauncher {
	public static void main(String[] args) throws NotEnoughMoneyException{
		
		StockPriceProvider stockPriceProvider = new RandomStockPriceProvider();
		
		StockPriceViewer stockPriceViewer = new StockPriceViewer();	
        
		AccountManagerImpl acc = new AccountManagerImpl(stockPriceProvider);
		
		SimpleLogger Logger = new SimpleLogger();
		
		InvocationHandler handler = new MyInvocationHandler(acc);
		AccountManager proxy = (AccountManager) Proxy.newProxyInstance(
		                            AccountManager.class.getClassLoader(),
		                            new Class[] { AccountManager.class },
		                            handler);
	    
        
		UpdateTimer updateTimer = UpdateTimer.getInstance();
		stockPriceProvider.startUpdate();
		stockPriceViewer.startUpdate(stockPriceProvider);
		
		StockGameCommandProcessor processor = new StockGameCommandProcessor(proxy);
		processor.process();
		
		
		
//		acc.addPlayer("fred");
//		acc.buyShares("fred", "Audi", 10);
//		acc.buyShares("fred", "BMW", 10);
//		PlayerViewer pv = new PlayerViewer(acc.getPlayer("fred"));
//		pv.startUpdate();
		
        
     
	}
	
	
}
