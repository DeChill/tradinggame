package de.hsaugsburg.core;


public interface StockPriceInfo {
	public boolean isShareListed(String shareName);
	public long getCurrentShareRate(String shareName);
	public Share[] getAllSharesAsSnapShot();
}
