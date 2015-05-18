package core;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TransactionHistory {

	public List<Transaction> Transactions = new LinkedList<Transaction>();
	
	@Override
	public String toString(){
		return Transactions.toString();
		
	}

	

	public static class Transaction {
		public Transaction(Types type, long value, int amount, String shareName){
			this.type = type;
			this.value = value;
			this.amount = amount;
			this.shareName  = shareName;
			this.date = new Date();
		}
		public enum Types {
			BUY, SELL
		};

		private Types type;
		private long value;
		private int amount;
		private String shareName;
		private Date date;

		public Types getType() {
			return type;
		}

		public long getValue() {
			return value;
		}

		public int getAmount() {
			return amount;
		}

		public String getShareName() {
			return shareName;
		}
		
		@Override
		public String toString(){
			return type.toString() + " " + amount + " " + shareName + " Value: " + value + " (" + date.toString() + ")"; 
		}

		public Date getDate() {
			return date;
		}
		

	}
}
