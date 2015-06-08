package de.hsaugsburg.core;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


public class ShareItem extends Asset {
	
	private Share share;
	private int amount;
	private long buyprice;
	ResourceBundle language;
	
	

	public ShareItem(String name, Share share, int amount){
		super(name);
		this.share = share;
		this.amount = amount;
		this.buyprice = share.getPrice()*amount;
		Properties sysProps = System.getProperties();
		this.language = (ResourceBundle.getBundle("l10n.LanguageBundle",
				new Locale(sysProps.getProperty("locale"))));
		
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
		return amount + " " + language.getString("sharesof") + " " + getName() + " " + language.getString("each") + " " + share.getPrice() + ". " + language.getString("value")+ " " + getValue();
	}
	
	public Share getShare(){
		return share;
	}

}
