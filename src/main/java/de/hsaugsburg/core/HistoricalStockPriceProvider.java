package de.hsaugsburg.core;

import java.io.*;
import java.util.*;

public class HistoricalStockPriceProvider extends StockPriceProvider {

	BufferedReader audiReader;
	BufferedReader toyotaReader;
	BufferedReader bmwReader;
	BufferedReader daimlerReader;

	public HistoricalStockPriceProvider(Share[] shares) {
		super(shares);
		try {
			audiReader = new BufferedReader(new FileReader(
					"/Users/Timo/workspace/tradinggame/shareprices/audi.csv"));
			bmwReader = new BufferedReader(new FileReader(
					"/Users/Timo/workspace/tradinggame/shareprices/bmw.csv"));
			toyotaReader = new BufferedReader(new FileReader(
					"/Users/Timo/workspace/tradinggame/shareprices/toyota.csv"));
			daimlerReader = new BufferedReader(
					new FileReader(
							"/Users/Timo/workspace/tradinggame/shareprices/daimler.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	protected void updateShareRate(Share share) {
		BufferedReader reader = null;
		switch (share.getName()) {
		case ("Audi"):
			reader = audiReader;
			break;
		case ("BMW"):
			reader = bmwReader;
			break;
		case ("Daimler"):
			reader = daimlerReader;
			break;
		case ("Toyota"):
			reader = toyotaReader;
			break;
		}

		String line = "";
		String cvsSplitBy = ",";

		try {

			if (reader.readLine() != null
					&& !reader.readLine().contains("Date"))
				;
			line = reader.readLine();
			String[] shareArr = line.split(cvsSplitBy);
			share.setPrice((long) (Float.parseFloat(shareArr[4]) * 100));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
