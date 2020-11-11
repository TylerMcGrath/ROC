package com.bank.models;

public class Account {
	
	private int accountID;
	private String ownerUsername;
	private double balance;
	private String accountType;
	private Boolean accountApproved;
	
	public Account(int accountID, String ownerUsername, double balance, String accountType, Boolean accountApproved) {
		super();
		this.accountID = accountID;
		this.ownerUsername = ownerUsername;
		this.balance = balance;
		this.accountType = accountType;
		this.accountApproved = accountApproved;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Boolean getAccountApproved() {
		return accountApproved;
	}

	public void setAccountApproved(Boolean accountApproved) {
		this.accountApproved = accountApproved;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", ownerUsername=" + ownerUsername + ", balance=" + balance
				+ ", accountType=" + accountType + ", accountApproved=" + accountApproved + "]";
	}
	
	
}
