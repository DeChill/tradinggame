package core;

import Agent.AgentProcessor;
import Logging.*;
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


public class AccountManagerImpl implements AccountManager {

	private UpdateTimer timer = UpdateTimer.getInstance();
	private StockPriceProvider stockPriceProvider;
	private Logger logger = Logger.getLogger("test");

	public AccountManagerImpl(StockPriceProvider stockPriceProvider) {
		this.stockPriceProvider = stockPriceProvider;
	}

	Player[] players = new Player[0];

	private void addPlayerToArray(Player player) {
		Player[] temp = new Player[players.length + 1];
		for (int i = 0; i < players.length; i++) {
			temp[i] = players[i];
		}
		temp[temp.length - 1] = player;

		this.players = temp;
	}

	public Player getPlayer(String name) {
		for (int i = 0; i < players.length; i++) {
			if (name.equals(players[i].getName())) {
				return players[i];
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
			addPlayerToArray(player);
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
	public Share[] getAllShares() {
		return stockPriceProvider.getShares();
	}

	@Override
	public void startAgent(String playerName) {
		AgentProcessor agent = new AgentProcessor(playerName, this);
		agent.startUpdate();

	}
	
	 

	 

	

}
