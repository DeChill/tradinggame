package core;

import java.util.*;

public class RandomStockPriceProvider extends StockPriceProvider{
	
	private int randomSign(){
		return ((int) (Math.round(Math.random()) * 2 -1));
		
	}
	
	protected void updateShareRate(Share share){
	long a = (share.getPrice() + randomSign() * ((long) (share.getPrice() *(Math.random()*0.1))));
	if (a>0) share.setPrice(a);
	}

//	@Override
//	public void startUpdate() {
//	}
}
