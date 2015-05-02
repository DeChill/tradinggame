package core;

import Exceptions.NotEnoughSharesException;

public class ShareDepositAccount extends Asset {

	public ShareDepositAccount(String name) {
		super(name);

	}

	ShareItem[] shares = new ShareItem[0];

	public void addShareItem(ShareItem item) {
		ShareItem[] temp = new ShareItem[shares.length + 1];
		for (int i = 0; i < shares.length; i++) {
			temp[i] = shares[i];
		}
		temp[temp.length - 1] = item;

		this.shares = temp;
	}
	
	public ShareItem findShareItem(String shareName){
		for(int i = 0; i < shares.length; i++) {
			if (shareName.equals(shares[i].getName())) {
				return shares[i];
			}
		}
		return null;
	}
	public String toString() {

		String temp = shares[0].toString();
		for (int i = 1; i < shares.length; i++) {
			temp = temp + ", " + shares[i].toString();
		}
		temp = temp + ". Gesammtwert: " + getValue();
		return temp;
	}

	public long getValue() {
		long value = 0;
		for (int i = 0; i < shares.length; i++) {
			value = value + shares[i].getValue();
		}
		return value;
	}

	public void buyShares(Share share, int amount) {

		for (int i = 0; i < shares.length; i++) {
			if (share.equals(shares[i].getShare())) { // test if player already
														// has shares of that
														// type
				shares[i].addAmount(amount);// shares to player shareitem array
				shares[i].addBuyprice(share.getPrice() * amount);
				return;
			}
		}
		ShareItem item = new ShareItem(share.getName(), share, amount); // make
																		// new
																		// entry
																		// in
																		// shareitem
																		// array
		addShareItem(item);

	}

	public void sellShares(Share share, int amount)
			throws NotEnoughSharesException {
		boolean successful = false;
		for (int i = 0; i < shares.length; i++) {
			if (share.equals(shares[i].getShare())) {
				if (shares[i].getAmmount() < amount) { // test if player has
														// enough shares of that
														// type
					throw new NotEnoughSharesException();
				}
				shares[i].setAmount(shares[i] // decrease share items
						.getAmmount() - amount);
				shares[i].addBuyprice(share.getPrice() * amount*(-1));

				successful = true;

			}

		}
		if (!successful) {
			throw new NotEnoughSharesException();
		}
	}
	
	public ShareItem [] getShareItems() {
		return shares;
	}
}
