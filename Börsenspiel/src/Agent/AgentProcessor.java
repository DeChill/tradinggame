package Agent;

import java.util.TimerTask;

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
		updateTimer.timer.scheduleAtFixedRate(new TimerTask() {

			public void run(){
				sellShares();
				buyShares();
				System.out.println("Fred : " + accountManager.getPlayer("Fred").toString());
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
				accountManager.sellShares(playerName, shareItems [i].getName(), shareItems[i].getAmmount());
			}
		}
	}
	public void buyShares() {
		Share [] shares = accountManager.getAllShares();
		if(c > shares.length-1) c = 0;
		accountManager.buyShares(player.getName(), shares [c++].getName(), 5);
	}

	public String getRandomOperation() {
		int a = ((int) (Math.round(Math.random()) * 2 - 1));
		if (a == 1)
			return "bs";
		else
			return "ss";
	}
}
