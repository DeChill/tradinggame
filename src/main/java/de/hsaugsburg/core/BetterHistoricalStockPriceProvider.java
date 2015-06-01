package de.hsaugsburg.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class BetterHistoricalStockPriceProvider extends StockPriceProvider {
	private Deque<String> audiShareRates;
	private Deque<String> bmwShareRates;
	private Deque<String> toyotaShareRates;
	private Deque<String> daimlerShareRates;

	BetterHistoricalStockPriceProvider(Share[] shares) {
		super(shares);
		audiShareRates = new ArrayDeque<String>();
		bmwShareRates = new ArrayDeque<String>();
		toyotaShareRates = new ArrayDeque<String>();
		daimlerShareRates = new ArrayDeque<String>();
		
		BufferedReader audiReader;
		try {
			audiReader = new BufferedReader(new FileReader(
					"shareprices/audi.csv"));
			while (audiReader.ready()) {
				String line = audiReader.readLine();
				if (line != null && !line.contains("Date"))
					audiShareRates.push(line);
			}
			BufferedReader bmwReader = new BufferedReader(new FileReader(
					"shareprices/bmw.csv"));
			while (bmwReader.ready()) {
				String line = bmwReader.readLine();
				if (line != null && !line.contains("Date"))
					bmwShareRates.push(line);
			}
			BufferedReader toyotaReader = new BufferedReader(new FileReader(
					"shareprices/toyota.csv"));
			while (toyotaReader.ready()) {
				String line = toyotaReader.readLine();
				if (line != null && !line.contains("Date"))
					toyotaShareRates.push(line);
			}
			BufferedReader daimlerReader = new BufferedReader(
					new FileReader(
							"shareprices/daimler.csv"));
			while (daimlerReader.ready()) {
				String line = daimlerReader.readLine();
				if (line != null && !line.contains("Date"))
					daimlerShareRates.push(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void updateShareRate(Share share) {
		Deque<String> deque = null;
		switch (share.getName()) {
		case ("Audi"):
			deque = audiShareRates;
			break;
		case ("BMW"):
			deque = bmwShareRates;
			break;
		case ("Daimler"):
			deque = daimlerShareRates;
			break;
		case ("Toyota"):
			deque = toyotaShareRates;
			break;
		}
		String line = "";
		if (deque.peek()!= null){
			line = deque.pop();
		}
		String[] shareArr = line.split(",");
		share.setPrice((long) (Float.parseFloat(shareArr[4]) * 100));

	}

}
