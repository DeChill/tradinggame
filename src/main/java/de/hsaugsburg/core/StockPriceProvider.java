package de.hsaugsburg.core;


import java.util.*;

import de.hsaugsburg.exception.NoShareFoundException;


public abstract class StockPriceProvider implements StockPriceInfo{

	private UpdateTimer updateTimer = UpdateTimer.getInstance();
	private final SortedMap<String,Share> shares;
	
	
	public void startUpdate() {
	updateTimer.getTimer().scheduleAtFixedRate(new TimerTask() {

			public void run() {
				updateShareRates();
			}
			
		}, 2000, 1000);
	}
	
	
	
	
	
	
	public StockPriceProvider(Share[] shares){
		this.shares = new TreeMap<String,Share>();
		for (Share s: shares)addShare(s);
	}
	
	private void addShare(Share s) {
		shares.put(s.getName(), s);
		
	}

//	public  class NameComparator implements Comparator<Share> {
//		@Override
//		public int compare(Share a, Share b) {
//			return a.getName().compareToIgnoreCase(b.getName());
//		}
//	    
//	}
	
	@Override
	public boolean isShareListed(String shareName) {
		for( int i = 0; i < getAllSharesAsSnapShot().length; i++){ 
			if( shareName.equals(getAllSharesAsSnapShot()[i].getName())){
				return true;
			}
		}
		return false;
	}

	@Override
	public long getCurrentShareRate(String shareName) {
		return getShare(shareName).getPrice();
	}

	@Override
	public Share[] getAllSharesAsSnapShot() {
		Share [] temp = new Share [getShares().length];
		for(int i = 0; i < getShares().length; i++) {
			temp[i] = new Share(getShares()[i].getName(),getShares()[i].getPrice());
		}
		return temp; 
	}
	protected void updateShareRates(){
		for(Share s: shares.values()){
			updateShareRate(s);
		}
	}
	
	
	protected abstract void updateShareRate(Share share);
	
	public Share getShare(String name){
		Share s = shares.get(name);
		if(s!=null)return s;
		throw new NoShareFoundException();
	}
	
	public String toString(){
		String shareString = new String();
		for(int i = 0; i < getAllSharesAsSnapShot().length; i++) {
			shareString = shareString + getAllSharesAsSnapShot()[i].toString() + "; ";
		}
		return shareString;
	}
	
	public Share[] getShares() {
		Share[] temp = new Share[shares.values().size()];
		System.arraycopy(shares.values().toArray(), 0, temp, 0, shares.values().size());
		return temp;
		
	}

}
