package com.bank.menus;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.exception.BusinessException;
import com.bank.models.Account;
import com.bank.models.Customer;
import com.bank.models.Employee;
import com.bank.models.Transaction;
import com.bank.service.BankDBService;

public class EmployeePortal {

	public EmployeePortal() {
		
	}
	
	public static class EmployeeActions{
		
		public static Employee login(Logger log, BankDBService bankService, Scanner scanner) {

			Employee employee = null;
			log.info("Please Enter User Name and Password");
			log.info("==================================");
			log.info("User Name: ");
			String username = scanner.nextLine();
			log.debug("user input = " + username);
			log.info("Password: ");
			String password = scanner.nextLine();
			log.debug("user input = " + password);

			try {
				employee = bankService.employeeLogin(username, password);
			} catch (BusinessException e) {
				log.warn(e.getMessage());
			}

			return employee;

		}
		
		public static List<Account> custAccounts(Logger log, BankDBService bankService, Scanner scanner) {
			List<Account> accounts = null;
			String username = null;
			log.debug("-getting accounts-");

			log.info("Please Enter username to see user accounts");
			username = scanner.nextLine();
			try {
				accounts = bankService.getCustAccounts(username);
			} catch (BusinessException e) {
				log.warn(e.getMessage());
			}

			return accounts;
		}
		
		public static List<Transaction> transactionList(Logger log, BankDBService bankService, Scanner scanner) {
			List<Transaction> txnList = null;
			String username = null;
			log.debug("-getting accounts-");

			try {
				txnList = bankService.getAllTransactions();
			} catch (BusinessException e) {
				log.warn(e.getMessage());
			}

			return txnList;
		}
		
	}
	
}
