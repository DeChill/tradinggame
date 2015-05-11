package Test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.Share;
import core.ShareItem;

public class ShareItemTest {
	
	private final long buyprice = 20000;
	private final Share share = new Share("Zweite", 4000);
	private final ShareItem step1 = new ShareItem("Test1", share , 2);
	private final ShareItem step2 = new ShareItem("Test2", share, 5);
	
	@Test
	public void addAmountTest() {
		step1.addAmount(3);
		assertEquals("2 + 3 = 5" , step1.getAmmount(), step2.getAmmount());
	}
	
	@Test 
	public void addBuypriceTest() {
		step1.addAmount(3);
		assertEquals("(2*4000)+(3*4000) = 20000", step1.getValue(), buyprice);

		
		
	} 
	
}
