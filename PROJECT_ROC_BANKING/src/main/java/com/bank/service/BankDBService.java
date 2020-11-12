package com.bank.service;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.models.Account;
import com.bank.models.Customer;
import com.bank.models.Employee;
import com.bank.models.Transaction;

public interface BankDBService {
	
	//Login&Register
	public Customer customerLogin(String username, String password) throws BusinessException; //returns customer info from table for validation
	public Employee employeeLogin(String username, String password) throws BusinessException; //returns employee info from table for validation
	public void customerCreate(String firstname, String lastname, String username, String password) throws BusinessException; //adds new entry to customer table
	
	//Customer operations
	public void openAccount(String usernamne, String accountType, double openingBalance) throws BusinessException; //adds account to accounts table approved = false
	public void deposit(int accountID, double amount) throws BusinessException; //add money to existing account
	public void withdrawl(int accountID, double amount) throws BusinessException; //remove money from existing account
	public void transfer(int fromAccountID, int toAccountID, double amount) throws BusinessException; //transfer money between 2 accounts
	
	//Employee operations
	public List<Transaction> getAllTransactions() throws BusinessException; //view entire transactions table
	public List<Account> pendingApproval() throws BusinessException; //view unapproved accounts
	public void approveAccount(int accountID) throws BusinessException;//approve accounts
	public void denyAccount(int accountID) throws BusinessException; //deny (delete) account
	public List<Customer> getAllCustomers() throws BusinessException;
	
	//shared operations
	public List<Account> getCustAccounts(String username) throws BusinessException; //displays all accounts for username
	public List<Account> getAccount(int accountID) throws BusinessException; //displays all accounts for username
	public List<Transaction> getAccntTransactions(int accountID) throws BusinessException; //displays all transactions for an account
	public List<Transaction> getCustTransactions(String username) throws BusinessException; //displays transactions for user

	
}
