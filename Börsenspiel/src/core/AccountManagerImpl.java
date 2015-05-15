package core;

import Agent.AgentProcessor;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Exceptions.NoShareFoundException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotEnoughSharesException;
import Exceptions.ParamErrorException;
import Exceptions.PlayerNameAlreadyExistsException;
import Exceptions.PlayerNotFoundException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import core.TransactionHistory.Transaction;

public class AccountManagerImpl implements AccountManager {

	private UpdateTimer timer = UpdateTimer.getInstance();
	private StockPriceProvider stockPriceProvider;
	private Logger logger = Logger.getLogger("test");
	private Set<Player> players;

	public AccountManagerImpl(StockPriceProvider stockPriceProvider) {
		this.stockPriceProvider = stockPriceProvider;
		players = new HashSet<Player>();
	}

	


	private void addPlayerToList(Player player) {
		players.add(player);
	}

	public Player getPlayer(String name) {

		  for (Player n : players) {

			if (n.getName().equals(name)) {
				return n;

			}
		}
		throw new PlayerNotFoundException();
	}

	private void testForPlayer(String name) {
		try {
			getPlayer(name);
			throw new PlayerNameAlreadyExistsException();

		} catch (PlayerNotFoundException e) {

		}

	}

	@Override
	public void addPlayer(String name) {

		try {
			testForPlayer(name);
			if (name.length() >= 16 || (!name.matches("[a-zA-Z1-9]*"))) {
				throw new ParamErrorException();
			}

			Player player = new Player(name, 500000);
			addPlayerToList(player);
		} catch (PlayerNameAlreadyExistsException e) {
			System.out.println("Player already exists!");
		}
	}

	@Override
	public void buyShares(String playerName, String shareName, int amount)
			throws NotEnoughMoneyException {
		// try {

		getPlayer(playerName).buyPlayerShares(
				stockPriceProvider.getShare(shareName), amount);

		// } catch (NotEnoughMoneyException e) {
		// System.out.println("Not enough money for that transaction");
		// } catch (NoShareFoundException e) {
		// System.out.println("No share with that name was found!");
		// } catch (PlayerNotFoundException e) {
		// System.out.println("Fehler bei der Eingabe!");
		// }

	}

	@Override
	public void sellShares(String playerName, String shareName, int amount)
			throws NotEnoughSharesException {
		// try {
		getPlayer(playerName).sellPlayerShares(
				stockPriceProvider.getShare(shareName), amount);
		// } catch (NotEnoughSharesException e) {
		// System.out.println("Not enough shares for that transaction");
		// } catch (NoShareFoundException e) {
		// System.out.println("No share with that name was found!");
		// }
	}

	@Override
	public long getValue(Asset asset) {
		return asset.getValue();
	}

	public long check(String playerName, String shareName) {
		return (stockPriceProvider.getCurrentShareRate(shareName) * getPlayer(
				playerName).getShareDespositAccount().findShareItem(shareName)
				.getAmmount())
				- (getPlayer(playerName).getShareDespositAccount()
						.findShareItem(shareName).getBuyprice());
	}

	@Override
	public long getPlayerValue(String name) {
		return (getPlayer(name).getPlayerCash().getValue() + getPlayer(name)
				.getPlayerShares().getValue());
	}

	@Override
	public String getShares() {
		return stockPriceProvider.toString();
	}

	@Override
	public List<Share> getAllShares() {
		return stockPriceProvider.getShares();
	}

	@Override
	public void startAgent(String playerName) {
		AgentProcessor agent = new AgentProcessor(playerName, this);
		agent.startUpdate();

	}

	@Override
	public String transactionHistoryToString(String playerName, String param) {
		List<Transaction> temp = getPlayer(playerName).getTransactionHistory().Transactions;
		StringBuffer buffer = new StringBuffer();
		if (param.equals("all")) {
			return getPlayer(playerName).getTransactionHistory().toString();
		}
		for (Transaction n : temp)
			if (n.getNotes().equals(param)) {
				buffer.append(n.toString());
			}
		return buffer.toString();
	}

}
