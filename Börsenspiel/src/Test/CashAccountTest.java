package Test;
import static org.junit.Assert.*;
import org.junit.Test;
import Exceptions.NotEnoughMoneyException;
import core.CashAccount;


public class CashAccountTest {
	
	private final CashAccount cashacc = new CashAccount("Test1", 10000);
	private final CashAccount newcash = new CashAccount("Test2", 15000);
	
	@Test
	public void testAddMoney() {
		cashacc.addMoney(5000);
		assertEquals("10000 + 5000" , newcash.getValue(), cashacc.getValue());
	}
	
	@Test
	public void testDecMoney() {
		
		newcash.decMoney(5000);
		assertEquals("Name : 10000 - 5000" , newcash.getValue() , cashacc.getValue());
	}
}
