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

		public static boolean deposit(Logger log, Customer customer, BankDBService bankService, Scanner scanner) throws BusinessException {
			
			int accountID = 0;
			double amount = 0;
			
			log.info("Please Enter account id for deposit");
			try {
				accountID = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				throw new BusinessException("Invalid accountID");
			}
			
			log.info("Please Enter amount for deposit");
			try {
				amount = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				throw new BusinessException("Invalid transfer amount");
			}
			
			try {
			bankService.deposit(accountID, amount);
			}catch(BusinessException e) {
				log.warn(e.getMessage());
			}
			return true;
		}
		
		public static boolean withdrawl(Logger log, Customer customer, BankDBService bankService, Scanner scanner) throws BusinessException {
			
			int accountID = 0;
			double amount = 0;
			
			log.info("Please Enter account id for withdrawl");
			try {
				accountID = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				throw new BusinessException("Invalid accountID");
			}

			log.info("Please Enter amount for withdrawl");
			try {
				amount = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				throw new BusinessException("Invalid transfer amount");
			}
			
			try {
			bankService.withdrawl(accountID, amount);
			}catch(BusinessException e) {
				log.warn(e.getMessage());
			}
			return true;
	
		}
		
		public static boolean transferMoney(Logger log, Customer customer, BankDBService bankService, Scanner scanner) throws BusinessException {
			int fromAccountID = 0;
			int toAccountID = 0;
			double amount = 0;

			log.info("Enter account number money will be transferred FROM: ");

			try {
				fromAccountID = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				throw new BusinessException("Invalid accountID");
			}

			log.info("Enter account number money will be transferred TO: ");
			
			try {
				toAccountID = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				throw new BusinessException("Invalid accountID");
			}

			log.info("Enter amount of money for transfer");
			
			try {
				amount = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				throw new BusinessException("Invalid transfer amount");
			}

			try {
				bankService.transfer(fromAccountID, toAccountID, amount);
			} catch(BusinessException e){
				log.warn(e.getMessage());
			}

			return true;

		}

		public static void createCustomer(Logger log, BankDBService bankService, Scanner scanner) throws BusinessException{
			log.info("Thank you for choosing to create an account with us!");
			log.info("What's your first name?");
			String firstname = scanner.nextLine();
			log.info("What's your last name?");
			String lastname = scanner.nextLine();
			log.info("Pick a username (it must be unique)");
			String username = scanner.nextLine();
			log.info("Lastly, decide on a password");
			String password = scanner.nextLine();
			
			try {
			bankService.customerCreate(firstname, lastname, username, password);
			log.info("Welcome to the family "+ firstname + "!");
			} catch(BusinessException e) {
				log.warn(e.getMessage());
			}

		}
	}
}