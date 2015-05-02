package core;

import java.util.TimerTask;

import Exceptions.NoShareFoundException;


public abstract class StockPriceProvider implements StockPriceInfo{

	UpdateTimer updateTimer = UpdateTimer.getInstance();
	
	
	public void startUpdate() {
	updateTimer.timer.scheduleAtFixedRate(new TimerTask() {

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
		for(int i = 0; i<shares.length; i++){
			updateShareRate(shares[i]);
		}
	}
	
	
	protected abstract void updateShareRate(Share share);
	
	public Share getShare(String name){
		for( int i = 0; i < getShares().length; i++){ 
			if( name.equals(getShares()[i].getName())){
				return getShares()[i];
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
	
	public Share [] getShares() {
		return shares;
		
	}

}
