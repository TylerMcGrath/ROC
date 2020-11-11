package com.bank.dao;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.models.*;


public interface BankDBDAO {
	
	//Login&Register
	public Customer customerLogin(String username, String password) throws BusinessException; //returns customer info from table for validation
	public Employee employeeLogin(String username, String password) throws BusinessException; //returns employee info from table for validation
	public void customerCreate(String firstname, String lastname, String username, String password) throws BusinessException; //adds new entry to customer table
	
	//Customer operations
	public void openAccount(String accountType, double openingBalance); //adds account to accounts table approved = false
	public void deposit(int accountID, double amount); //add money to existing account
	public void withdraw(int accountID, double amount); //remove money from existing account
	public void transfer(int fromAccountID, int toAccountID, double amount); //transfer money between 2 accounts
	
	//Employee operations
	public List<Transactions> getTransactions(); //view entire transactions table
	public List<Account> pendingApproval(); //view unapproved accounts
	public void approveAccount(int accountID);//approve accounts
	public void denyAccount(int accountID); //deny (delete) account
	
	//shared operations
	public List<Account> getAccounts(String username); //displays all accounts for username
	public List<Transactions> getTransactions(int accountID); //displays all transactions for an account
	public List<Transactions> getTransactions(String username); //displays transactions for user

}
