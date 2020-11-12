package com.bank.menus;

import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.bank.dao.BankDBDAO;
import com.bank.exception.BusinessException;
import com.bank.models.Account;
import com.bank.models.Customer;
import com.bank.service.BankDBService;

import jdk.internal.org.jline.utils.Log;

public class CustomerPortal {

	public CustomerPortal() {

	}

	public static class CustomerActions {

		public static Customer login(Logger log, BankDBService bankService, Scanner scanner) {

			Customer customer = null;
			log.info("Please Enter User Name and Password");
			log.info("==================================");
			log.info("User Name: ");
			String username = scanner.nextLine();
			log.debug("user input = " + username);
			log.info("Password: ");
			String password = scanner.nextLine();
			log.debug("user input = " + password);

			try {
				customer = bankService.customerLogin(username, password);
			} catch (BusinessException e) {
				log.warn(e.getMessage());
			}

			return customer;

		}

		public static List<Account> custAccounts(Logger log, Customer customer, BankDBService bankService) {
			List<Account> accounts = null;
			log.debug("-getting accounts-");

			try {
				accounts = bankService.getCustAccounts(customer.getUsername());
			} catch (BusinessException e) {
				log.warn(e.getMessage());
			}

			return accounts;
		}

		public static boolean openSavings(Logger log, Customer customer, BankDBService bankService, double openBalance) {

			try {
				bankService.openAccount(customer.getUsername(), "SAVINGS", openBalance);

			} catch (BusinessException e) {
				log.warn(e.getMessage());
				return false;
			}
			return true;
		}

		public static boolean openChecking(Logger log, Customer customer, BankDBService bankService, double openBalance) {

			try {
				bankService.openAccount(customer.getUsername(), "CHECKING", openBalance);

			} catch (BusinessException e) {
				log.warn(e.getMessage());
				return false;
			}
			return true;
		}

		public static boolean transferMoney(Logger log, Customer customer, BankDBService bankService, Scanner scanner) {
			int fromAccountID = 0;
			int toAccountID = 0;
			double amount = 0;

			log.info("Enter account number money will be transferred from: ");

			try {
				fromAccountID = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				log.warn("Invalid accountID");
				return false;
			}

			try {
				toAccountID = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				log.warn("Invalid accountID");
				return false;
			}

			try {
				toAccountID = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				log.warn("Invalid accountID");
				return false;
			}

			try {
				bankService.transfer(fromAccountID, toAccountID, amount);
			} catch(BusinessException e){
				log.warn(e.getMessage());
				return false;
			}

			return true;

		}

	}
}