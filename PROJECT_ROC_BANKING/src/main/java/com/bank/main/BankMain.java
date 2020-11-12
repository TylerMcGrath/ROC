package com.bank.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.dao.BankDBDAO;
import com.bank.dao.impl.BankDBDAOImplementation;
import com.bank.exception.BusinessException;
import com.bank.menus.CustomerPortal;
import com.bank.menus.CustomerPortal.CustomerLogin;
import com.bank.models.Customer;
import com.bank.service.BankDBService;
import com.bank.service.impl.BankDBServiceImplementation;

public class BankMain {

	private static Logger log=Logger.getLogger(BankMain.class);

	public static void main(String[] args) {

		Scanner scanner =  new Scanner(System.in);
		BankDBDAO bankDAO = new BankDBDAOImplementation();
		BankDBService bankService = new BankDBServiceImplementation();

		log.info("=======================================================");
		log.info("Welcome to The 1st Bank of Mars! How may we help you?");
		log.info("=======================================================");

		int select = 0;

		do {
			log.debug("-top of main-");
			log.info("Please select user account type for login, or create a new customer account");
			log.info("---------------------------------------------------------------------------");
			log.info("1) Customer login");
			log.info("2) Employee login");
			log.info("3) Create New Customer Account");
			log.info("0) Exit application");
			log.info(" \n Please only enter valid selection (1-3, or 0 to exit)");

			try {
				select = Integer.parseInt(scanner.nextLine());
				log.debug("user input = " + select);

			} catch (NumberFormatException e) {
				log.debug(e);
				log.warn(select + " is invalid, Please select valid option \n");
			}

			switch(select) {
			case 1: // customer login
				int select1 = 0;
				Customer customer = CustomerLogin.login(log, bankService);
				if (customer != null) {
					do {
						log.debug("-successful login-");
						log.info("=======================================================");
						log.info("Welcome " + customer.getFirstname() + " " + customer.getLastname() + "!");
						log.info("What would you like to do?");
						log.info("=======================================================");
						log.info("1) View/Transact With My Accounts");
						log.info("2) Open New Savings Account");
						log.info("3) Open New Checking Account");
						log.info("4) View/Modify My Information");
						log.info("0) Logout");

						try {
							select1 = Integer.parseInt(scanner.nextLine());
							log.debug("user input = " + select1);

						} catch (NumberFormatException e) {
							log.debug(e);
							log.warn(select + " is invalid, Please select valid option \n");
						}

						switch(select1) {
						case 1: //view accounts
							log.info("This feature is still under construction! It'll be done soon(tm)\n");
							break;//view accounts

						case 2: //open savings
							log.info("This feature is still under construction! It'll be done soon(tm)\n");
							break;//open savings

						case 3: //open checking
							log.info("This feature is still under construction! It'll be done soon(tm)\n");
							break;//open checking

						case 4: //view info
							log.info("This feature is still under construction! It'll be done soon(tm)\n");
							break;//view info

						case 0: //logout
							log.info("Logging you out...\n");
							break;//logout

						default:
							log.warn("Invalid selection, please try again \n");
							break;
						}
					} while(select1 != 0);
					


			}

			break;//break for main menu case1

		case 2://employee login main menu
			log.info("This feature is still under construction! It'll be done soon(tm) \n");
			break;//employee login main menu

		case 3://create new customer account main
			log.info("This feature is still under construction! It'll be done soon(tm) \n");
			break;// new customer account main

		case 0://exit
			log.info("==============================================");
			log.info("Thank you for choosing Mars! Come again soon!");
			log.info("==============================================");
			break;//exit

		default:
			log.warn("Invalid selection, please try again \n");
			break;
		}
	} while (select != 0);

}

}
