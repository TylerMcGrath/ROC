package com.bank.service.impl;

import com.bank.dao.BankDBDAO;
import com.bank.dao.impl.BankDBDAOImplementation;
import com.bank.service.BankDBService;

public class BankDBServiceImplementation implements BankDBService {
	
	private BankDBDAO DAO = new BankDBDAOImplementation();
	
}
