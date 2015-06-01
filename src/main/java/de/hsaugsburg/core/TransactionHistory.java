package de.hsaugsburg.core;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TransactionHistory {

	public List<Transaction> Transactions = new LinkedList<Transaction>();
	
	@Override
	public String toString(){
		return Transactions.toString();
		
	}
	public void toFile(){
		final String tableprop = "style='border: 1px black solid; background-color: white' width='100%' cellspacing='1' cellpadding='2'";
		StringBuffer buffer = new StringBuffer();
		Iterator<Transaction> transactionIterator = Transactions.iterator();
		buffer.append("<table " + tableprop + ">");
		
		buffer.append("<tr>");
		buffer.append("<td> Datum </td>");
		buffer.append("<td> Share Name </td>");
		buffer.append("<td> Amount </td>");
		buffer.append("<td> Value </td>");
		buffer.append("</tr>");
		
		while(transactionIterator.hasNext()){
			Transaction transaction = transactionIterator.next();
			buffer.append("<tr>");
			buffer.append("<td>" + transaction.getDate() + "</td>");
			buffer.append("<td>" + transaction.getShareName() + "</td>");
			buffer.append("<td>" + transaction.getAmount() + "</td>");
			buffer.append("<td>" + transaction.getValue() + "</td>");
			buffer.append("</tr>");
		}
		
		buffer.append("</table>");
		
		String htmlTable = buffer.toString();
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("TransactionHistory.html"), "utf-8"))){
			try {
				writer.write(htmlTable);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
	
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