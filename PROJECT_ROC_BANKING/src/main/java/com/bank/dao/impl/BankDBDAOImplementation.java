package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao.BankDBDAO;
import com.bank.dao.dbutils.BankDBQueries;
import com.bank.dao.dbutils.PostgreSQLConnection;
import com.bank.exception.BusinessException;
import com.bank.models.Account;
import com.bank.models.Customer;
import com.bank.models.Employee;
import com.bank.models.Transaction;

public class BankDBDAOImplementation implements BankDBDAO {

	@Override
	public Customer customerLogin(String username, String password) throws BusinessException {
		
		Customer customer = null;
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.CUSTOMERLOGIN;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				customer = new Customer(resultSet.getString("firstname"), resultSet.getString("lastname"), 
						resultSet.getString("username"), resultSet.getString("passwrd"));
				//System.out.println(customer);
			}
			else {
				throw new BusinessException("Invalid Credentials. Please try again, or try creating a new account.");
			}				
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
			//System.out.println("SQL" +e.getMessage());;
		}
		return customer;
	}

	@Override
	public Employee employeeLogin(String username, String password) throws BusinessException {
		
		Employee employee = null;
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.EMPLOYEELOGIN;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "admin");
			preparedStatement.setString(2, "admin");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				employee = new Employee(resultSet.getString("firstname"), resultSet.getString("lastname"), 
						resultSet.getString("username"), resultSet.getString("passwrd"));
			}
			else {
				throw new BusinessException("Invalid Credentials... Please try again.");
			}	
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		}
		return employee;
	}

	@Override
	public void customerCreate(String firstname, String lastname, String username, String password) throws BusinessException {

		Customer customer = null;
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
				String sqlcheck = BankDBQueries.LOGINCHECK;
				PreparedStatement check = connection.prepareStatement(sqlcheck);
				String sqladd = BankDBQueries.CUSTOMERCREATE;
				PreparedStatement add = connection.prepareStatement(sqladd);
								
				add.setString(1, firstname);
				add.setString(2, lastname);
				add.setString(3, username);
				add.setString(4, password);
				
				check.setString(1, username);
				ResultSet rs = check.executeQuery();
				
				if (rs.next()) {
					throw new BusinessException("Username is taken. Please select another.");
				}
				else {
					add.executeUpdate();
				}	
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		}		
	}

	@Override
	public void openAccount(String username, String accountType, double openingBalance) throws BusinessException {
		
		//Account account = null;
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
				String sql = BankDBQueries.OPENACCOUNT;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setString(1, username);
				preparedStatement.setDouble(2, openingBalance);
				preparedStatement.setString(3, accountType);
				preparedStatement.setDouble(4, openingBalance);
				
				preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		}		
	}

	@Override
	public void deposit(int accountID, double amount) throws BusinessException {
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {

				String sql = BankDBQueries.DEPOSIT;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				
				preparedStatement.setDouble(1, amount);
				preparedStatement.setInt(2, accountID);
				preparedStatement.setInt(3, accountID);
				preparedStatement.setDouble(4, amount);
				
				preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		}		
	}

	@Override
	public void withdrawl(int accountID, double amount) throws BusinessException {
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.WITHDRAWL;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String sqlcheck = BankDBQueries.BALANCECHECK;
			PreparedStatement check = connection.prepareStatement(sqlcheck);
			
			check.setInt(1, accountID);
			ResultSet rs = check.executeQuery();
			rs.next();
			if(rs.getDouble("balance") < amount) {
				throw new BusinessException("Can't perform withrawl. Insufficient balance.");
			}
			else {
			preparedStatement.setDouble(1, amount);
			preparedStatement.setInt(2, accountID);
			preparedStatement.setInt(3, accountID);
			preparedStatement.setDouble(4, amount);
			preparedStatement.executeUpdate();
			}

	} catch (ClassNotFoundException | SQLException e) {
		//throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		System.out.println(e.getMessage());
	}	
		
	}

	@Override
	public void transfer(int fromAccountID, int toAccountID, double amount) throws BusinessException {
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.TRANSFER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			String sqlcheck = BankDBQueries.BALANCECHECK;
			PreparedStatement check = connection.prepareStatement(sqlcheck);
			check.setInt(1, fromAccountID);
			ResultSet rs = check.executeQuery();
			rs.next();
			if(rs.getDouble("balance") < amount) {
				throw new BusinessException("Can't perform transfer. Insufficient balance for withdrawl in account number" + fromAccountID);
			}
			
			preparedStatement.setDouble(1, amount);
			preparedStatement.setInt(2, toAccountID);
			preparedStatement.setInt(3, toAccountID);
			preparedStatement.setDouble(4, amount);
			preparedStatement.setDouble(5, amount);
			preparedStatement.setInt(6, fromAccountID);
			preparedStatement.setInt(7, fromAccountID);
			preparedStatement.setDouble(8, amount);
			
			preparedStatement.executeUpdate();

	} catch (ClassNotFoundException | SQLException e) {
		//throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		System.out.println(e);
	}	
		
		
	}

	@Override
	public List<Transaction> getAllTransactions() throws BusinessException {

		List<Transaction> txnList = new ArrayList<>();
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.GETALLTRANSACTIONS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Transaction transaction = new Transaction(resultSet.getInt("transactionid"), resultSet.getInt("accountid"),
						resultSet.getDouble("txnamount"), resultSet.getTimestamp("timedate"), resultSet.getString("txntype"),
						resultSet.getBoolean("approved"));
				txnList.add(transaction);
			}
			
			if(txnList.size()==0)
			{
				throw new BusinessException("No Transaction Records Available");
			}
	} catch (ClassNotFoundException | SQLException e) {
		throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
	}	
		return txnList;
	}

	@Override
	public List<Account> pendingApproval() throws BusinessException {

		List<Account> accntList = new ArrayList<>();

		try (Connection connection = PostgreSQLConnection.getConnection()) {

			String sql = BankDBQueries.GETACCOUNTUNAPPROVED;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {

				Account account = new Account(resultSet.getInt("accountid"), resultSet.getString("username"), 
						resultSet.getDouble("balance"), resultSet.getString("accnttype"), resultSet.getBoolean("approved"));
				accntList.add(account);
			}

			if(accntList.size()==0)
			{
				throw new BusinessException("No Account Records Available");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		}	

		return accntList;
	}

	@Override
	public void approveAccount(int accountID) throws BusinessException {
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.APPROVEACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountID);
			preparedStatement.executeUpdate();

	} catch (ClassNotFoundException | SQLException e) {
		throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
	}	
		
	}

	@Override
	public void denyAccount(int accountID) throws BusinessException {

		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.DENYACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountID);
			preparedStatement.executeUpdate();

	} catch (ClassNotFoundException | SQLException e) {
		throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
	}
		
	}

	@Override
	public List<Account> getCustAccounts(String username) throws BusinessException {
		
		List<Account> accntList = new ArrayList<>();
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.GETCUSTACCOUNTS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Account account = new Account(resultSet.getInt("accountid"), resultSet.getString("username"), 
						resultSet.getDouble("balance"), resultSet.getString("accounttype"), resultSet.getBoolean("approved"));
				accntList.add(account);
			}
			
			if(accntList.size()==0)
			{
				throw new BusinessException("No Account Records Available");
			}
	} catch (ClassNotFoundException | SQLException e) {
		//throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		System.out.println(e.getMessage());;
	}
		return accntList;
	}

	@Override
	public List<Account> getAccount(int accountID) throws BusinessException {

		List<Account> accntList = new ArrayList<>();
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.GETACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountID);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Account account = new Account(resultSet.getInt("accountid"), resultSet.getString("username"), 
						resultSet.getDouble("balance"), resultSet.getString("accnttype"), resultSet.getBoolean("approved"));
				accntList.add(account);
			}
			
			if(accntList.size()==0)
			{
				throw new BusinessException("No Account Records Available");
			}
	} catch (ClassNotFoundException | SQLException e) {
		throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
	}	
		
		return accntList;
	}

	@Override
	public List<Transaction> getAccntTransactions(int accountID) throws BusinessException {
		
		List<Transaction> txnList = new ArrayList<>();
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.GETACCNTTRANSACTIONS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountID);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Transaction transaction = new Transaction(resultSet.getInt("transactionid"), resultSet.getInt("accountid"),
						resultSet.getDouble("txnamount"), resultSet.getTimestamp("timedate"), resultSet.getString("txntype"),
						resultSet.getBoolean("approved"));
				txnList.add(transaction);
			}
			
			if(txnList.size()==0)
			{
				throw new BusinessException("No Transaction Records Available");
			}
	} catch (ClassNotFoundException | SQLException e) {
		throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
	}	
		return txnList;
	}

	@Override
	public List<Transaction> getCustTransactions(String username) throws BusinessException {
		
		List<Transaction> txnList = new ArrayList<>();
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.GETCUSTTRANSACTIONS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Transaction transaction = new Transaction(resultSet.getInt("transactionid"), resultSet.getInt("accountid"),
						resultSet.getDouble("txnamount"), resultSet.getTimestamp("timedate"), resultSet.getString("txntype"),
						resultSet.getBoolean("approved"));
				txnList.add(transaction);
			}
			
			if(txnList.size()==0)
			{
				throw new BusinessException("No Transaction Records Available");
			}
	} catch (ClassNotFoundException | SQLException e) {
		throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
	}	
		return txnList;
	}

	
	@Override
	public List<Customer> getAllCustomers() throws BusinessException {
		
		List<Customer> custList = new ArrayList<>();
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.GETALLCUSTOMERS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Customer customers = new Customer(resultSet.getString("firstname"), resultSet.getString("lastname"),
						resultSet.getString("username"), null);
				custList.add(customers);
			}
			
			if(custList.size()==0)
			{
				throw new BusinessException("No Customer Records Available");
			}
	} catch (ClassNotFoundException | SQLException e) {
		throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
	}	
		return custList;

	}




	
}
