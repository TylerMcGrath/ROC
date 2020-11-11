package com.bank.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.exception.BusinessException;
import com.bank.service.BankDBService;

public class BankMain {

	private static Logger log=Logger.getLogger(BankMain.class);
		
	public static void main(String[] args) {

		Scanner scanner =  new Scanner(System.in);
		log.info("Welcome to Tyler's Banking Application");
		log.info("-----------------------------------------------");
		
		BankDBService bankDBservice = new BankDBService();
		
		int select = 0;
		
		do {
			log.info("Please select user account type for login, or create a new customer account");
			log.info("==================================");
			log.info("1) Customer login");
			log.info("2) Employee login");
			log.info("3) Create New Customer Account");
			log.info("0) Exit application");
			log.info("Please only enter valid selection (1-3, or 0 to exit)");
			
			try {
				select = Integer.parseInt(scanner.nextLine());
				log.debug("user input = " + select);
				
			} catch (NumberFormatException e) {
				log.warn(select + " is invalid, Please select valid option");
			}
			
			switch(select) {
			case 1:
				
					//check customer table for matching entry
					log.info("Please Enter User Name and Password");
					log.info("==================================");
					log.info("User Name: ");
					String username = scanner.nextLine();
					log.debug("user input = " + username);
					log.info("Password: ");
					String password = scanner.nextLine();
					log.debug("user input = " + password);
					
					try {
						//select username, password from customers
					} catch (BusinessException e) {
						// TODO: handle exception
					}
				
				
			
			
			}
		} while (select != 0);
		

	}

}
