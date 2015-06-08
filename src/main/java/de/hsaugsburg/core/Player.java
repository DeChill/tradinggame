package de.hsaugsburg.core;

import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import de.hsaugsburg.core.TransactionHistory.Transaction;
import de.hsaugsburg.core.TransactionHistory.Transaction.Types;
import de.hsaugsburg.exception.NotEnoughMoneyException;
import de.hsaugsburg.exception.NotEnoughSharesException;
import de.hsaugsburg.view.PlayerViewer;

public class Player {

	private final String name;

	private ShareDepositAccount playerShares;
	private CashAccount playerCash;
	private TransactionHistory transactionHistory;
	private ResourceBundle language;

	public Player(String name, long money) {
		this.name = name;

		playerCash = new CashAccount(name, money);
		playerShares = new ShareDepositAccount(name);

		this.language = (ResourceBundle.getBundle("l10n.LanguageBundle",
				getLocale()));

		transactionHistory = new TransactionHistory(this);
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

		playerCash.decMoney(share.getPrice() * amount);

		playerShares.buyShares(share, amount);

		transactionHistory.Transactions.add(new Transaction(Types.BUY, (share
				.getPrice() * amount), amount, share.getName()));

	}

	public void sellPlayerShares(Share share, int amount)
			throws NotEnoughSharesException {

		playerCash.addMoney(share.getPrice() * amount);

		playerShares.sellShares(share, amount);

		transactionHistory.Transactions.add(new Transaction(Types.SELL, (share
				.getPrice() * amount), amount, share.getName()));

	}

	public ShareDepositAccount getShareDespositAccount() {
		return playerShares;
	}

	public TransactionHistory getTransactionHistory() {
		return transactionHistory;
	}

	public Locale getLocale() {


		 Properties sysProps = System.getProperties();
		return new Locale(sysProps.getProperty("locale"));
	}

	public ResourceBundle getLanguage() {
		return language;
	}
}
