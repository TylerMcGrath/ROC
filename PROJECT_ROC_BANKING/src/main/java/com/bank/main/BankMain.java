package com.bank.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.dao.BankDBDAO;
import com.bank.dao.impl.BankDBDAOImplementation;
import com.bank.exception.BusinessException;
import com.bank.menus.CustomerPortal;
import com.bank.menus.CustomerPortal.CustomerActions;
import com.bank.menus.EmployeePortal.EmployeeActions;
import com.bank.models.Account;
import com.bank.models.Customer;
import com.bank.models.Employee;
import com.bank.models.Transaction;
import com.bank.service.BankDBService;
import com.bank.service.impl.BankDBServiceImplementation;

public class BankMain {

	private static Logger log=Logger.getLogger(BankMain.class);

	public static void main(String[] args) throws BusinessException {

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
				Customer customer = CustomerActions.login(log, bankService, scanner);
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
						log.info("1) Manage My Accounts");
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
									accountsList = CustomerActions.custAccounts(log, customer, bankService);
									for (Account a:accountsList) {
										log.info(a); }

									log.info("=======================================================");
									log.info("How would you like to manage your accounts?");
									log.info("======================================================= ");
									log.info("1) Make a deposit");
									log.info("2) Make a withdrawl");
									log.info("3) Transfer between accounts");
									log.info("4) View account transactions");
									log.info("5) View pending transfers");
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
										try {
											CustomerActions.deposit(log, customer, bankService, scanner);
											//log.info("Deposit Successful!");
										}catch(BusinessException e) {
											log.warn(e.getMessage());
										}
										break;

										/////////////////////
										///	END DEPOSIT	////
										//////////////////

										///////////////////////////////////////////////////////////////////////
										/////	MAIN --> CUSTOMER MAIN --> MANAGE ACCOUNTS --> WITHDRAWL 	////
										//////////////////////////////////////////////////////////////////////		

									case 2://withdrawl
										try {
											CustomerActions.withdrawl(log, customer, bankService, scanner);
											//log.info("Withdrawl Successful!");
										}catch(BusinessException e) {
											log.warn(e.getMessage());
										}										
										break;

										////////////////////////
										///	END WITHDRAWL	////
										///////////////////////

										///////////////////////////////////////////////////////////////////////
										/////	MAIN --> CUSTOMER MAIN --> MANAGE ACCOUNTS --> TRANSFER 	////
										//////////////////////////////////////////////////////////////////////			

									case 3://tranfer
										try {
											CustomerActions.transferMoney(log, customer, bankService, scanner);
										}catch(BusinessException e) {
											log.warn(e.getMessage());
										}
										break;

										////////////////////////
										///	END TRANSFER	////
										///////////////////////

										///////////////////////////////////////////////////////////////////////
										/////	MAIN --> CUSTOMER MAIN --> MANAGE ACCOUNTS --> TRANSACTIONS  ////
										//////////////////////////////////////////////////////////////////////	

									case 4://view txn
										log.info("This feature is still under construction! It'll be done soon(tm)\n");
										break;

										////////////////////////
										///	END TRANSACTIONS////
										///////////////////////

										///////////////////////////////////////////////////////////////////////
										/////	MAIN --> CUSTOMER MAIN --> MANAGE ACCOUNTS --> PENDING  ////
										//////////////////////////////////////////////////////////////////////	

									case 5://view pending
										log.info("This feature is still under construction! It'll be done soon(tm)\n");
										break;

										////////////////////////
										///	END PENDING////
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
							double openBalance = 0;
							log.info("Good choice! Enter opening balance for your new savings account: ");
							try {
								openBalance = Double.parseDouble(scanner.nextLine());
								if(openBalance > 0) {
									CustomerActions.openSavings(log, customer, bankService, openBalance);
									log.info("Congratulations! Your Savings account has successfully been created! Time to watch your money grow!");
								} else {
									log.warn("You can't put negative money in your account. Nice try.");
								}
							} catch (NumberFormatException e) {
								log.warn("Please enter a value greater than 0");
							}

							break;//end open savings

							/////////////////////////////////////////
							/////	END OPEN SAVINGS 	/////////
							//////////////////////////////////

							//////////////////////////////////////////////////////////
							/////	MAIN --> CUSTOMER MAIN --> OPEN CHECKING	////
							//////////////////////////////////////////////////////

						case 3: //open checking
							log.info("Good choice! Enter opening balance for your new savings account: ");
							try {
								openBalance = Double.parseDouble(scanner.nextLine());
								if(openBalance > 0) {
									CustomerActions.openChecking(log, customer, bankService, openBalance);
									log.info("Congratulations! Your Checking account has successfully been created! Time to spend with ease!");
								} else {
									log.warn("You can't put negative money in your account. Nice try.");
								}
							} catch (NumberFormatException e) {
								log.warn("Please enter a value greater than 0");
							}
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
				int select2 = 0;
				Employee employee = EmployeeActions.login(log, bankService, scanner);
				if (employee != null) {

					/////////////////////////////////////////
					/////	MAIN --> EMPLOYEE MAIN MENU	/////////
					/////////////////////////////////////

					do {
						log.debug("-successful login-");
						log.info("=======================================================");
						log.info("Welcome " + employee.getFirstname() + " " + employee.getLastname() + "!");
						log.info("What would you like to do?");
						log.info("=======================================================");
						log.info("1) View Customer Accounts");
						log.info("2) View Transaction Log");
						log.info("3) Manage Pending Accounts");
						log.info("4) Manage My Information");
						log.info("0) Logout");

						try {
							select2 = Integer.parseInt(scanner.nextLine());
							log.debug("user input = " + select2);

						} catch (NumberFormatException e) {
							log.debug(e.getMessage());
							log.warn(select2 + " is invalid, Please select valid option \n");
						}

						switch(select2) { 

						case 1://view customer accounts
							
							List<Account> accountsList = EmployeeActions.custAccounts(log, bankService, scanner);
							for (Account a:accountsList) {
								log.info(a); }
							
							break;

						case 2://view transaction log
							
							List<Transaction> txnList = EmployeeActions.transactionList(log, bankService, scanner);
							for (Transaction t:txnList) {
								log.info(t); }
							
							break;

						case 3:// manage pending accounts
							log.info("This feature is still under construction! It'll be done soon(tm)\n");

							break;

						case 4://manage personal information
							log.info("This feature is still under construction! It'll be done soon(tm)\n");

							break;

						case 0:
							log.info("Logging you out...\n");

							break;

						default:
							log.warn("Invalid selection, please try again \n");
							break;
						}
					} while (select2 != 0);
					
				}break;//end employee login main menu
				//////////////////////////////////////////////
				///// MAIN <--- END OF EMPLOYEE MAIN MENU	///
				/////////////////////////////////////////////

				////////////////////////////////////////////
				/////	MAIN --> CREATE NEW CUSTOMER	////
				///////////////////////////////////////////

			case 3://create new customer account main

				CustomerActions.createCustomer(log, bankService, scanner);

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
