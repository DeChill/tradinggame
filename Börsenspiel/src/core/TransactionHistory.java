package core;

import java.util.LinkedList;
import java.util.List;

public class TransactionHistory {

	public List<Transaction> Transactions = new LinkedList<Transaction>();
	
	@Override
	public String toString(){
		return Transactions.toString();
		
	}

	

	public static class Transaction {
		public Transaction(Types type, long value, int amount, String notes){
			this.type = type;
			this.value = value;
			this.amount = amount;
			this.notes  = notes;
		}
		public enum Types {
			BUY, SELL
		};

		private Types type;
		private long value;
		private int amount;
		private String notes;

		public Types getType() {
			return type;
		}

		public long getValue() {
			return value;
		}

		public int getAmount() {
			return amount;
		}

		public String getNotes() {
			return notes;
		}
		
		@Override
		public String toString(){
			return type.toString() + " " + amount + " " + notes + " Value: " + value; 
		}
		

	}
}
