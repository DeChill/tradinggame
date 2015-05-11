package core;

public class CashAccount extends Asset {

	private long balance;

	public CashAccount(String name, long balance) {
		super(name);
		this.balance = balance;

	}

	public long getValue() {
		return balance;
	}
	
	public void addMoney(long money){
		balance += money;
	}
	
	public void decMoney(long money) {
		balance -= money;
	}
	
	public String toString(){
		return String.valueOf(balance);
	}
}
