package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.NotEnoughMoneyException;
import Exceptions.NotEnoughSharesException;
import core.CashAccount;
import core.Player;
import core.Share;
import core.ShareDepositAccount;

public class PlayerTest {
			
	private Player player;
	Share aktie1 = null;
	Share aktie2 = null;
	
	@Before 
	public void setUp() {
		player = new Player("Sepp", 15000);
		aktie1 = new Share ("Aktie1", 100);
		aktie2 = new Share ("Aktie2", 1000);
	}
	
	@Test
	public void buyPlayerSharesTest() throws NotEnoughMoneyException{
		player.buyPlayerShares(aktie2, 5);
		player.getShareDespositAccount().buyShares(aktie1, 5);
		assertEquals("checking the amount of playerShares", player.getPlayerShares().findShareItem("Aktie1").getAmmount(), 5 );
		assertEquals("checking the value of playerCash", player.getPlayerCash().getValue(), 10000);
	}
	@Test 
	public void sellPlayerSharesTest() throws NotEnoughSharesException, NotEnoughMoneyException{
		player.buyPlayerShares(aktie2, 10);
		player.sellPlayerShares(aktie2, 5);
		assertEquals("checking the amount of shares after the selling of the  shares", player.getPlayerShares().findShareItem("Aktie2").getAmmount(), 5);
		assertEquals("checking the value of playerCash after selling of shares" , player.getPlayerCash().getValue(), 10000);
		
	}
	
	@Test (expected = NotEnoughMoneyException.class) 
	public void buyPlayerSharesTestUnsuccessufl() throws NotEnoughMoneyException {
		player.buyPlayerShares(aktie2, 100);
		
	}
	@Test (expected = NotEnoughSharesException.class)
	public void sellPlayerSharesTestUnsuccessfulOne() throws NotEnoughSharesException, NotEnoughMoneyException {
		player.buyPlayerShares(aktie2, 10);
		player.sellPlayerShares(aktie2, 1000);
		
	}
	
}
