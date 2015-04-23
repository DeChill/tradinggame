package core;

import Exceptions.NoShareFoundException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotEnoughSharesException;
import Exceptions.PlayerNameAlreadyExistsException;

public class AccountManagerImpl implements AccountManager {

	private UpdateTimer timer = UpdateTimer.getInstance();
	private StockPriceProvider stockPriceProvider;

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
		return null;
	}

	@Override
	public void addPlayer(String name, long money) {
		try {
			if (getPlayer(name) != null)
				throw new PlayerNameAlreadyExistsException();
			Player player = new Player(name, money);
			addPlayerToArray(player);
		} catch (PlayerNameAlreadyExistsException e) {
			System.out.println("Player already exists!");
		}
	}

	@Override
	public void buyShares(String playerName, String shareName, int amount) {
		try {
			
			getPlayer(playerName).buyPlayerShares(
					stockPriceProvider.getShare(shareName), amount);

		} catch (NotEnoughMoneyException e) {
			System.out.println("Not enough money for that transaction");
		} catch (NoShareFoundException e) {
			System.out.println("No share with that name was found!");
		}

	}

	@Override
	public void sellShares(String playerName, String shareName, int amount) {
		try {
			getPlayer(playerName).sellPlayerShares(
					stockPriceProvider.getShare(shareName), amount);
		} catch (NotEnoughSharesException e) {
			System.out.println("Not enough shares for that transaction");
		} catch (NoShareFoundException e) {
			System.out.println("No share with that name was found!");
		}
	}

	@Override
	public long getValue(Asset asset) {
		return asset.getValue();
	}

	@Override
	public long getPlayerValue(String name) {
		return (getPlayer(name).getPlayerCash().getValue() + getPlayer(name).getPlayerShares()
				.getValue());
	}
}
