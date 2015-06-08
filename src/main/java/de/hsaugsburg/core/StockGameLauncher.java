package de.hsaugsburg.core;
import java.io.FileNotFoundException;

import javafx.application.Application;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import de.hsaugsburg.GUI.GUI;
import de.hsaugsburg.agent.AgentProcessor;
import de.hsaugsburg.exception.NotEnoughMoneyException;
import de.hsaugsburg.scanner.StockGameCommandProcessor;
import de.hsaugsburg.view.PlayerViewer;
import de.hsaugsburg.view.StockPriceViewer;


public class StockGameLauncher {
	public static void main(String[] args) throws NotEnoughMoneyException{
		Share bmw = new Share("BMW", 11600);
		Share aud = new Share("Audi", 7500);
		Share dai = new Share("Daimler", 9150);
		Share toy = new Share ("Toyota" , 7000);
		Share[] shares = {bmw,aud,dai,toy};
		
		Properties sysProps = System.getProperties();
		sysProps.setProperty("locale", "de");
//		sysProps.setProperty("locale", "en");
		
		
		Properties prop = new Properties();
 
		FileReader reader = null;
		try {
			reader = new FileReader("config.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			try {
				prop.load(reader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		StockPriceProvider stockPriceProvider = new BetterHistoricalStockPriceProvider(shares);
		
		StockPriceViewer stockPriceViewer = new StockPriceViewer();	
        
		AccountManagerImpl acc = new AccountManagerImpl(stockPriceProvider);
		
		Logger.getLogger(StockGameLauncher.class.getName());
		
		InvocationHandler handler = new MyInvocationHandler(acc);
		AccountManager proxy = (AccountManager) Proxy.newProxyInstance(
		                            AccountManager.class.getClassLoader(),
		                            new Class[] { AccountManager.class },
		                            handler);
	    
        
		UpdateTimer updateTimer = UpdateTimer.getInstance();
		stockPriceProvider.startUpdate();
		stockPriceViewer.startUpdate(stockPriceProvider);
		
//		GUI gui = new GUI();
//		gui.launch();
//		
		
		StockGameCommandProcessor processor = new StockGameCommandProcessor(proxy);
		processor.process();
		
		
		
		
        
     
	}
	
	
}
