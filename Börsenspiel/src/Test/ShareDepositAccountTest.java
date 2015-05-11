package Test;
import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.NotEnoughSharesException;
import core.Share;
import core.ShareDepositAccount;
import core.ShareItem;

public class ShareDepositAccountTest {
	
	private final long buyprice = 20000;
	private final Share neu1 = new Share("Test1", 4000);
	private final Share neu2 = new Share("Test2", 5000);
	private final ShareItem item1 = new ShareItem("Test1", neu1, 2 );
	private final ShareItem item2 = new ShareItem("Test2", neu2, 5);
	private final ShareDepositAccount acc1 = new ShareDepositAccount("Person1");
	private final ShareDepositAccount acc2 = new ShareDepositAccount("Person2");
	
	@Test 
	public void addShareItemTest() {
		acc1.addShareItem(item1);
		acc1.addShareItem(item2);
		assertTrue("check wether objects get added to the account successfully", acc1.getShareItems()[0].equals(item1) && acc1.getShareItems()[1].equals(item2));
		acc2.addShareItem(item1);
		acc2.addShareItem(item1);
		assertEquals("wether acc1. check if same share-type gets added to same share item", acc2.getShareItems()[0].getAmmount(), 2);
		
	}
	
	@Test
	public void findShareItemTest() {
		acc1.addShareItem(item1);
		assertEquals("checks wher the share item is located", acc1.findShareItem("Test1"), item1);
	}
	
	@Test
	public void getValueTest() {
		acc1.addShareItem(item2);
		assertEquals("get the value of your account", acc1.getValue(), item2.getValue());
	}
	
	@Test
	public void buySharesTest() {
		acc1.buyShares(neu1, 2);
		assertEquals("checks buying, name of the shares" ,  acc1.getShareItems()[0].getName(), item1.getName());
		assertEquals("checks buying, value of the shares" ,  acc1.getShareItems()[0].getValue(), item1.getValue());
		assertEquals("checks buying, ammont of the shares" ,  acc1.getShareItems()[0].getAmmount(), item1.getAmmount());
	}
	
	@Test
	public void sellSharesTest() throws NotEnoughSharesException {
		acc1.addShareItem(item1);
		acc1.sellShares(neu1 ,  1);
		assertEquals("checks selling, of share", acc1.getShareItems()[0].getAmmount(), 1);
		
	}
	
	
}
