package com.bank.service.impl;

import java.util.List;

import com.bank.dao.BankDBDAO;
import com.bank.dao.impl.BankDBDAOImplementation;
import com.bank.exception.BusinessException;
import com.bank.models.Account;
import com.bank.models.Customer;
import com.bank.models.Employee;
import com.bank.models.Transaction;
import com.bank.service.BankDBService;

public class BankDBServiceImplementation implements BankDBService {
	
	private BankDBDAO DAO = new BankDBDAOImplementation();

	@Override
	public Customer customerLogin(String username, String password) throws BusinessException {
		
		Customer customer = DAO.customerLogin(username, password);
		
		return customer;
	}

	@Override
	public Employee employeeLogin(String username, String password) throws BusinessException {
		
		Employee employee = DAO.employeeLogin(username, password);
		
		return employee;
	}

	@Override
	public void customerCreate(String firstname, String lastname, String username, String password)
			throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openAccount(String usernamne, String accountType, double openingBalance) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deposit(int accountID, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(int accountID, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transfer(int fromAccountID, int toAccountID, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Transaction> getAllTransactions() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> pendingApproval() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void approveAccount(int accountID) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void denyAccount(int accountID) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> getAllCustomers() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getCustAccounts(String username) throws BusinessException {
		
		List<Account> accountsList = null;
		//System.out.println(username);
		accountsList = DAO.getCustAccounts(username);
		return accountsList;
	}

	@Override
	public List<Account> getAccount(int accountID) throws BusinessException {


		
		return null;
	}

	@Override
	public List<Transaction> getAccntTransactions(int accountID) throws BusinessException {

		

		return null;
	}

	@Override
	public List<Transaction> getCustTransactions(String username) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
