package core;


import java.util.*;

import Exceptions.NoShareFoundException;


public abstract class StockPriceProvider implements StockPriceInfo{

	private UpdateTimer updateTimer = UpdateTimer.getInstance();
	
	
	
	public void startUpdate() {
	updateTimer.getTimer().scheduleAtFixedRate(new TimerTask() {

			public void run() {
				updateShareRates();
			}
			
		}, 2000, 1000);
	}
	
	Share bmw = new Share("BMW", 11600);
	Share aud = new Share("Audi", 7500);
	Share ope = new Share("Opel", 3600);
	Share dai = new Share("Daimler", 9150);
	Share toy = new Share ("Toyota" , 7000);
	
	private Share[] shares = {bmw, aud, ope, dai, toy};
	private List<Share> shareList = Arrays.asList(shares);
	
	public  class NameComparator implements Comparator<Share> {
		@Override
		public int compare(Share a, Share b) {
			return a.getName().compareToIgnoreCase(b.getName());
		}
	    
	}
	
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
		Share [] temp = new Share [getShares().size()];
		for(int i = 0; i < getShares().size(); i++) {
			temp[i] = new Share(getShares().get(i).getName(),getShares().get(i).getPrice());
		}
		return temp; 
	}
	protected void updateShareRates(){
		for(int i = 0; i<shares.length; i++){
			updateShareRate(shares[i]);
		}
	}
	
	
	protected abstract void updateShareRate(Share share);
	
	public Share getShare(String name){
		for( int i = 0; i < getShares().size(); i++){ 
			if( name.equals(getShares().get(i).getName())){
				return getShares().get(i);
			}
		}
		throw new NoShareFoundException();
	}
	
	public String toString(){
		String shareString = new String();
		for(int i = 0; i < getAllSharesAsSnapShot().length; i++) {
			shareString = shareString + getAllSharesAsSnapShot()[i].toString() + "; ";
		}
		return shareString;
	}
	
	public List<Share> getShares() {
		
		Collections.sort(shareList, new NameComparator());
		return shareList;
		
	}

}
