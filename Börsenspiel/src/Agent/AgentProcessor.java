package Agent;

import java.util.TimerTask;

import Exceptions.NotEnoughMoneyException;
import Exceptions.NotEnoughSharesException;
import core.*;

public class AgentProcessor {
	private String playerName;
	private AccountManager accountManager;
	private UpdateTimer updateTimer = UpdateTimer.getInstance();
	private Player player;
	int c = 0;

	public AgentProcessor(String playerName, AccountManager accountManager) {
		this.playerName = playerName;
		this.accountManager = accountManager;
		this.player = accountManager.getPlayer(playerName);
	}

	public void startUpdate() {
		updateTimer.getTimer().scheduleAtFixedRate(new TimerTask() {

			public void run(){
				sellShares();
				buyShares();
			}

		}, 2000, 1000);
	}
	public void sellShares() {
		ShareDepositAccount sharesAcc = player.getPlayerShares();
		ShareItem [] shareItems = sharesAcc.getShareItems();
		if(shareItems == null)
			return;
		for( int i = 0; i < shareItems.length; i++) {
			if ((accountManager.check(playerName, shareItems [i].getName())) > 1000 ){
				try {
					accountManager.sellShares(playerName, shareItems [i].getName(), shareItems[i].getAmmount());
				} catch (NotEnoughSharesException e) {
					System.out.println("Not enough shares for that transaction");
				}
			}
		}
	}
	public void buyShares() {
		Share [] shares = accountManager.getAllShares();
		if(c > shares.length-1) c = 0;
		try{
		accountManager.buyShares(player.getName(), shares [c++].getName(), 5);
		}catch(NotEnoughMoneyException e){
			sellAllShares();
		}
	}

	private void sellAllShares() {
		ShareDepositAccount sharesAcc = player.getPlayerShares();
		ShareItem [] shareItems = sharesAcc.getShareItems();
		if(shareItems == null)
			return;
		for( int i = 0; i < shareItems.length; i++) {
			
				try {
					accountManager.sellShares(playerName, shareItems [i].getName(), shareItems[i].getAmmount());
				} catch (NotEnoughSharesException e) {
					System.out.println("Not enough shares for that transaction");
				}
			
		}
		
	}

	public String getRandomOperation() {
		int a = ((int) (Math.round(Math.random()) * 2 - 1));
		if (a == 1)
			return "bs";
		else
			return "ss";
	}
}
