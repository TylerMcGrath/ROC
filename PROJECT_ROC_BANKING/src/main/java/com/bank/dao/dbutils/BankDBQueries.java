package com.bank.dao.dbutils;

public class BankDBQueries {
	
	public static final String CUSTOMERLOGIN = "select firstname, lastname, username, passwrd from bank.customers "
			+ "where username = ? and passwrd = ?";
	//if matching username/passwrd isn't returned, login is incorrect
	public static final String LOGINCHECK = "select firstname, lastname, username, passwrd from bank.customers "
			+ "where username = ?";
	public static final String BALANCECHECK = "select balance from bank.customers where accountid = ?";
			
	public static final String EMPLOYEELOGIN = "select firstname, lastname, username, passwrd from bank.employees "
			+ "where username = ? and passwrd = ?";
	//Use CUSTOMERLOGIN to check for existing username before creating a new one
	public static final String CUSTOMERCREATE = "insert into bank.customers (firstname, lastname, username, passwrd) "
			+ "values (?, ?, ?, ?)";
	
	public static final String OPENACCOUNT = "insert into bank.accounts (username, balance, accounttype, approved)"
			+ "values (?, ?, ?, false)";
	public static final String DEPOSIT = "update bank.accounts set balance=balance+? where accountid=?;"
			+ "insert into bank.transactions (accountid, txnamount, approved, timedate, txntype) "
			+ "values (?, ?, false, current_timestamp, 'DEPOSIT')";
	public static final String WITHDRAWL = "update bank.accounts set balance=balance-? where accountid=?;"
			+ "insert into bank.transactions (accountid, txnamount, approved, timedate, txntype) "
			+ "values (?, ?, false, current_timestamp, 'WITHRDAWL')";
	public static final String TRANSFER = "update bank.accounts set balance=balance+? where accountid=?;"
			+ "insert into bank.transactions (accountid, txnamount, approved, timedate, txntype) "
			+ "values (?, ?, false, current_timestamp, 'TRANSFERTO');"
			+ "update bank.accounts set balance=balance-? where accountid=?;"
			+ "insert into bank.transactions (accountid, txnamount, approved, timedate, txntype)"
			+ "values (?, ?, false, current_timestamp, 'TRANSFERFROM')";
	
	public static final String GETALLCUSTOMERS = "select username, firstname, lastname from bank.customers";
	public static final String GETALLTRANSACTIONS = "select transactionid, accountid, txnamount, timedate, txntype, approved from bank.transactions";
	public static final String GETACCNTTRANSACTIONS = "select transactionid, accountid, txnamount, timedate, txntype, approved from bank.transactions "
			+ "where accountid = ?"; //transactions for a single account
	public static final String GETCUSTTRANSACTIONS = "select transactionid, accountid, txnamount, timedate, txntype, approved from bank.transactions "
			+ "where username = ?"; // all transactions from a user
	public static final String GETCUSTACCOUNTS = "select accountid, username, balance, accounttype, approved from bank.accounts "
			+ "where username=?"; // view account details, if not approved can't interact
	public static final String GETACCOUNT = "select accountid, balance, accounttype, approved from bank.accounts where accountid = ?";
	public static final String GETACCOUNTUNAPPROVED = "select accountid, username, balance, accounttype, approved from bank.accounts "
			+ "where approved = false"; //see unapproved accounts for
	
	public static final String APPROVEACCOUNT = "update bank.accounts set approved = true where accountid = ?";
	public static final String DENYACCOUNT = "delete from bank.accounts where accountid = ?";

}
