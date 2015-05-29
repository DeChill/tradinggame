package de.hsaugsburg.core;
import java.util.Timer;
import java.util.TimerTask;

import de.hsaugsburg.view.StockPriceViewer;

public class UpdateTimer {

		private Timer timer = new Timer();
		
	private static UpdateTimer instance;

	public static UpdateTimer getInstance() {
		if (UpdateTimer.instance == null) {
			UpdateTimer.instance = new UpdateTimer();
		}
		return UpdateTimer.instance;
	}
	
	public Timer getTimer(){
		return this.timer;
	}
}
