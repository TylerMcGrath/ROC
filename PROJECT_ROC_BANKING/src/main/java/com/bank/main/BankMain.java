package com.bank.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.dao.BankDBDAO;
import com.bank.dao.impl.BankDBDAOImplementation;
import com.bank.exception.BusinessException;
import com.bank.menus.CustomerPortal;
import com.bank.menus.CustomerPortal.CustomerActions;
import com.bank.models.Account;
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

		///////////////////////
		/// 	MAIN MENU	///
		//////////////////////
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
				log.debug(e.getMessage());
				log.warn(select + " is invalid, Please select valid option \n");
			}

			switch(select) {
			case 1: // customer login
				int select1 = 0;
				Customer customer = CustomerActions.login(log, bankService);
				if (customer != null) {
					
					/////////////////////////////////////////
					/////	MAIN --> CUSTOMER MAIN MENU	/////////
					/////////////////////////////////////
					
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
							log.debug(e.getMessage());
							log.warn(select1 + " is invalid, Please select valid option \n");
						}

						switch(select1) { 

						//////////////////////////////////////////////////////////
						/////	MAIN --> CUSTOMER MAIN --> MANAGE ACCOUNTS 	////
						//////////////////////////////////////////////////////
						
						case 1: //view and manage accounts
							int select1_1 = 0;
							List<Account> accountsList = CustomerActions.custAccounts(log, customer, bankService);
							if (accountsList != null && accountsList.size() > 0) {
								
								do {//manage accounts
									log.info("=======================================================");
									log.info("Here are your accounts, "+customer.getFirstname());
									log.info("======================================================= \n");

									for (Account a:accountsList) {
										log.info(a); }

									log.info("=======================================================");
									log.info("How would you like to manage your accounts?");
									log.info("======================================================= ");
									log.info("1) Make a deposit");
									log.info("2) Make a withdrawl");
									log.info("3) Transfer between your accounts");
									log.info("4) View pending transfers");
									log.info("0) Main Menu");

									try {
										select1_1 = Integer.parseInt(scanner.nextLine());
										log.debug("user input = " + select1_1);

									} catch (NumberFormatException e) {
										log.debug(e);
										log.warn(select1_1 + " is invalid, Please select valid option \n");
									}

									switch (select1_1) { //after view account
									
									////////////////////////////////////////////////////////////////////
									/////	MAIN --> CUSTOMER MAIN --> MANAGE ACCOUNTS --> DEPOSIT 	////
									///////////////////////////////////////////////////////////////////
									
									case 1://deposit
										log.info("This feature is still under construction! It'll be done soon(tm)\n");
										break;
									
									/////////////////////
									///	END DEPOSIT	////
									//////////////////
										
									///////////////////////////////////////////////////////////////////////
									/////	MAIN --> CUSTOMER MAIN --> MANAGE ACCOUNTS --> WITHDRAWL 	////
									//////////////////////////////////////////////////////////////////////		
										
									case 2://withdrawl
										log.info("This feature is still under construction! It'll be done soon(tm)\n");
										break;
									
									////////////////////////
									///	END WITHDRAWL	////
									///////////////////////
										
									///////////////////////////////////////////////////////////////////////
									/////	MAIN --> CUSTOMER MAIN --> MANAGE ACCOUNTS --> TRANSFER 	////
									//////////////////////////////////////////////////////////////////////			
										
									case 3://tranfer
										log.info("This feature is still under construction! It'll be done soon(tm)\n");
										break;
									
									////////////////////////
									///	END TRANSFER	////
									///////////////////////
										
									///////////////////////////////////////////////////////////////////////
									/////	MAIN --> CUSTOMER MAIN --> MANAGE ACCOUNTS --> PENDING  	////
									//////////////////////////////////////////////////////////////////////	
										
									case 4://view pending
										log.info("This feature is still under construction! It'll be done soon(tm)\n");
										break;
										
									////////////////////////
									///	END PENDING	////
									///////////////////////
										
									case 0:
										log.info("Taking you back to the main menu...\n");
										break;

									
									}

								}while (select1_1 != 0);

							}

							break;//end view accounts

							/////////////////////////////////////////////////////////
							/////	CUSTOMER MAIN <--	END	MANAGE ACCOUNTS 	/////////
							/////////////////////////////////////////////////////////
							
							//////////////////////////////////////////////////////////
							/////	MAIN --> CUSTOMER MAIN --> OPEN SAVINGS	////
							//////////////////////////////////////////////////////
							
						case 2: //open savings
							log.info("This feature is still under construction! It'll be done soon(tm)\n");
							break;//end open savings

							/////////////////////////////////////////
							/////	END OPEN SAVINGS 	/////////
							//////////////////////////////////
							
							//////////////////////////////////////////////////////////
							/////	MAIN --> CUSTOMER MAIN --> OPEN CHECKING	////
							//////////////////////////////////////////////////////
							
						case 3: //open checking
							log.info("This feature is still under construction! It'll be done soon(tm)\n");
							break;//end open checking
							
							/////////////////////////////////////////
							/////	END OPEN CHECKING 	/////////
							//////////////////////////////////

							/////////////////////////////////////////////////////////////
							/////	MAIN --> CUSTOMER MAIN --> VIEW CUSTOMER INFO	////
							/////////////////////////////////////////////////////////
							
						case 4: //view info
							log.info("This feature is still under construction! It'll be done soon(tm)\n");
							break;//end view info
							
							/////////////////////////////////////////
							/////	END VIEW INFO 	/////////
							//////////////////////////////////


						case 0: //logout
							log.info("Logging you out...\n");
							break;//end logout


						default:
							log.warn("Invalid selection, please try again \n");
							break;
						}
					} while(select1 != 0);

					//////////////////////////////////////////////
					///// MAIN <--- END OF CUSTOMER MAIN MENU	///
					/////////////////////////////////////////////
				}

				break;//break for main menu case1

				////////////////////////////////////////
				/////	MAIN --> EMPLOYEE MAIN MENU	////
				///////////////////////////////////////
				
			case 2://employee login main menu
				log.info("This feature is still under construction! It'll be done soon(tm) \n");
				break;//end employee login main menu
				
				//////////////////////////////////////////////
				///// MAIN <--- END OF EMPLOYEE MAIN MENU	///
				/////////////////////////////////////////////

				////////////////////////////////////////////
				/////	MAIN --> CREATE NEW CUSTOMER	////
				///////////////////////////////////////////
				
			case 3://create new customer account main
				log.info("This feature is still under construction! It'll be done soon(tm) \n");
				break;// end new customer account main

				//////////////////////////////////////////////
				/////	MAIN <-- END CREATE NEW CUSTOMER	////
				////////////////////////////////////////////
				
			case 0://exit
				log.info("==============================================");
				log.info("Thank you for choosing Mars! Come again soon!");
				log.info("==============================================");
				break;// end exit

			default:
				log.warn("Invalid selection, please try again \n");
				break;
			}
		} while (select != 0);

	}

}
