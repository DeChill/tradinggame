package de.hsaugsburg.core;


public class ShareItem extends Asset {
	
	private Share share;
	private int amount;
	private long buyprice;
	

	public ShareItem(String name, Share share, int amount){
		super(name);
		this.share = share;
		this.amount = amount;
		this.buyprice = share.getPrice()*amount;
		
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void addAmount( int amount) {
		this.amount += amount;
	}
	
	public int getAmmount(){
		return amount;
	}
	
	public void addBuyprice(long buyprice){
		this.buyprice += buyprice;
	}
	
	public long getBuyprice() {
		return buyprice;
	}
	public long getValue(){
		return share.getPrice() * amount;
	}
	
	public String toString(){
		return amount + " Aktien von " + getName() + " zu je " + share.getPrice() + ". Wert: " + getValue();
	}
	
	public Share getShare(){
		return share;
	}

}
