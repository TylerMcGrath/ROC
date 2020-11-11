package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bank.dao.BankDBDAO;
import com.bank.dao.dbutils.BankDBQueries;
import com.bank.dao.dbutils.PostgreSQLConnection;
import com.bank.exception.BusinessException;
import com.bank.models.Account;
import com.bank.models.Customer;
import com.bank.models.Employee;
import com.bank.models.Transactions;

public class BankDBDAOImplementation implements BankDBDAO {

	@Override
	public Customer customerLogin(String username, String password) throws BusinessException {
		
		Customer customer = null;
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.CUSTOMERLOGIN;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				customer = new Customer(resultSet.getString("firstname"), resultSet.getString("lastname"), 
						resultSet.getString("username"), resultSet.getString("passwrd"));
				System.out.println(customer);
			}
			else {
				throw new BusinessException("Invalid Credentials. Please try again, or try creating a new account.");
			}				
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
		}		
		return customer;
	}

	@Override
	public Employee employeeLogin(String username, String password) throws BusinessException {
		
		Employee employee = null;
		
		try (Connection connection = PostgreSQLConnection.getConnection()) {
			
			String sql = BankDBQueries.EMPLOYEELOGIN;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
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

				check.setString(1, username);

				add.setString(1, firstname);
				add.setString(2, lastname);
				add.setString(3, username);
				add.setString(4, password);
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
	public void openAccount(String accountType, double openingBalance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deposit(int accountID, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(int accountID, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transfer(int fromAccountID, int toAccountID, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Transactions> getTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> pendingApproval() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void approveAccount(int accountID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void denyAccount(int accountID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Account> getAccounts(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transactions> getTransactions(int accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transactions> getTransactions(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
