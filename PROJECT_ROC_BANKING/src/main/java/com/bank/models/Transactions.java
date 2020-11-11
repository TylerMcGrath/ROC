package com.bank.models;

import java.sql.Timestamp;

public class Transactions {
	
	private int transactionID;
	private int accountID;
	private double txnAmount;
	private String txnType;
	private Timestamp timestamp;
	private boolean txnApproved;
	
	public Transactions(int transactionID, int accountID, double txnAmount, String txnType, Timestamp timestamp,
			boolean txnApproved) {
		super();
		this.transactionID = transactionID;
		this.accountID = accountID;
		this.txnAmount = txnAmount;
		this.txnType = txnType;
		this.timestamp = timestamp;
		this.txnApproved = txnApproved;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public double getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(double txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isTxnApproved() {
		return txnApproved;
	}

	public void setTxnApproved(boolean txnApproved) {
		this.txnApproved = txnApproved;
	}

	@Override
	public String toString() {
		return "Transactions [transactionID=" + transactionID + ", accountID=" + accountID + ", txnAmount=" + txnAmount
				+ ", txnType=" + txnType + ", timestamp=" + timestamp + ", txnApproved=" + txnApproved + "]";
	}
	
	
	
}
