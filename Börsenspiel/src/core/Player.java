package core;

import view.PlayerViewer;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotEnoughSharesException;

public class Player {

	private final String name;

	private ShareDepositAccount playerShares;
	private CashAccount playerCash;

	public Player(String name, long money) {
		this.name = name;

		playerCash = new CashAccount(name, money);
		playerShares = new ShareDepositAccount(name);
		PlayerViewer pv = new PlayerViewer(this);
		pv.startUpdate();

	}
	public CashAccount getPlayerCash() {
		return playerCash;
	}
	
	public ShareDepositAccount getPlayerShares() {
		return playerShares;
	}
	
	public String getName() {

		return name;
	}

	public String toString() {
		return "Spielername: " + getName() + ", Kontostand: "
				+ playerCash.getValue() + ", Aktien: "
				+ playerShares.toString();
	}

	public void buyPlayerShares(Share share, int amount)
			throws NotEnoughMoneyException {

		if (share.getPrice() * amount > playerCash.getValue()) // test if enough
																// money is
																// available
			throw new NotEnoughMoneyException();

		playerShares.buyShares(share, amount);

		playerCash.decMoney(share.getPrice() * amount);

	}

	public void sellPlayerShares(Share share, int amount)
			throws NotEnoughSharesException {

		playerCash.addMoney(share.getPrice() * amount);

		playerShares.sellShares(share, amount);

	}
	public ShareDepositAccount getShareDespositAccount() {
		return playerShares;
	}

}
